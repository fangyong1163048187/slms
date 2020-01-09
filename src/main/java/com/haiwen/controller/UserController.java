package com.haiwen.controller;

import com.haiwen.pojo.User;
import com.haiwen.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UserController {
    @Resource
    private UserService userServiceImpl;
    /*跳转到注册页面*/
    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    /*跳转到登录页面*/
    @RequestMapping("/login")
    /*7天免登陆*/
    public String login(HttpServletRequest req){
        //获取所有cookie
        Cookie[] cookies = req.getCookies();
        //若存在cookie
        if(cookies!=null){
            String uid = "";
            for(Cookie cookie:cookies){
                //若存在名为uid的cookie,获取它的值
                if("uid".equals(cookie.getName())){
                    uid = cookie.getValue();
                }
            }
            //若存在名为uid的cookie
            if(!"".equals(uid)){
                //检查数据库是否存在该用户
                User user = userServiceImpl.selectRememberPsd(Long.parseLong(uid));
                //数据库中存在该用户
                if(user!=null){
                    //把用户数据放在httpsession中,然后跳转到登录界面
                    HttpSession session = req.getSession();
                    session.setAttribute("user",user);
                    return "login";
                }else{
                    //若该用户已销户，即数据库中没有改用户数据，则直接返回登录界面
                    return "login";
                }
            }else{
                //若不存在名为uid的cookie，直接跳转到登录界面
                return "login";
            }
        }else{
            //若不存在cookie，直接跳转到登录界面
            return "login";
        }
    }
    /*提交用户注册数据*/
    @RequestMapping("/registersubmit")
    public String registerSubmit(MultipartFile file, User user, HttpServletRequest req) {
        //获取用户上传的文件名
        String originalFilename = file.getOriginalFilename();
        //获取文件名后缀
        String filename_suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //获取唯一标识符,保证文件保存的时候不重名
        String uuid = UUID.randomUUID().toString();
        //给用户上传的文件指定一个全新的文件名
        String filename = uuid + filename_suffix;
        //把用户上传的文件保存到指定位置
        String path = req.getServletContext().getRealPath("files") + "/" + filename;
        try {
            //利用编写好的IO工具类FileUtils将文件保存到指定位置
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //把新的文件名写入数据库
        user.setPhoto(filename);
        //默认用户权限为0
        user.setRole(0);
        int row = userServiceImpl.insertRegister(user);
        //注册成功跳转到登录界面，失败则跳往错误页面
        if (row > 0) {
            return "register_success";
        } else {
            return "register_err";
        }
    }

    //检查用户名是否重复
    @RequestMapping("/register_check_username")
    @ResponseBody
    public User registerCheckUsername(String username){
        User user = userServiceImpl.selectUserNameIsUnique(username);
        //把user对象转化为json字符串,若user对象为null,转化为json字符串时为""
        return user;
    }

    //检查用户名和密码是否正确
    @RequestMapping("/login_ck_name_psd")
    @ResponseBody
    public User loginCheck(String username,String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userServiceImpl.selectCheckLogin(user);
    }

    //检查用户名和密码匹配后，用户登录入口
    @RequestMapping("/login_submit")
    public String loginSubmit(String username, String password, String rem_psd, HttpServletResponse resp,HttpServletRequest req){
        //把用户信息封装在user对象中
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //用户登录成功，创建一个httpsession用于保存用户信息
        HttpSession session = req.getSession();
        session.setAttribute("user",userServiceImpl.selectCheckLogin(user));
        //若用户选择记住密码，则创建一个cookie，
        // 7天之内用户可以不用输入密码，直接登录
        if(Boolean.parseBoolean(rem_psd)==true){
            // 把用户的id保存在cookie中作为唯一标识
            Cookie cookie = new Cookie("uid",Long.toString(userServiceImpl.selectCheckLogin(user).getId()));
            //设置cookie的最大时效为7天
            cookie.setMaxAge(7*24*3600);
            //setpath表示只有该请求才能获取cookie
            cookie.setPath("/login");
            //把cookie加到响应
            resp.addCookie(cookie);
        }
        return "main";
    }

    //显示用户头像
    @RequestMapping("get_user_photo")
    public void showUserPhoto(HttpServletRequest req,HttpServletResponse resp,HttpSession session){
        //设置响应类型
        resp.setContentType("image/jpeg;image/png;image/gif");
        //获取用户
        User user = (User) session.getAttribute("user");
        //获取用户头像路径
        String path = req.getServletContext().getRealPath("files")+"/"+user.getPhoto();
        File file = new File(path);
        try {
            //用外部工具类将文件转换为二进制字节流数组
            byte[] bytes = FileUtils.readFileToByteArray(file);
            //重置响应流
            resp.reset();
            //获取响应流
            ServletOutputStream out = resp.getOutputStream();
            //把图片写入响应流
            out.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //退出登录
    @RequestMapping("exit")
    public String exit(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }
    //跳转到个人信息页面
    @RequestMapping("userinfo")
    public String userInfo(){
        return "userinfo";
    }
    //跳转首页
    @RequestMapping("main")
    public String main(){
        return "main";
    }
    //跳转到角色管理界面，该界面只有超级管理员才可以操作
    @RequestMapping("role-info")
    public String roleInfo(){
        return "roleinfo";
    }
}
