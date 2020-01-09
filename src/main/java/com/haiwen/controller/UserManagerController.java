package com.haiwen.controller;

import com.haiwen.pojo.PageInfo;
import com.haiwen.service.UserManaerService;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserManagerController {
    @Resource
    private UserManaerService userManaerServiceImpl;
    //跳转到系统管理的用户管理界面
    @RequestMapping("user_manager")
    public String userManager(){
        return "user_manager";
    }
    //查找员工信息
    @RequestMapping("user_select")
    public String selectUserInfo(String username,Integer userRole, Model model, @RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize, @RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        PageInfo pageInfo = userManaerServiceImpl.selectUser(username, userRole, pageSize, pageNumber);
        //把每页的用户信息存入model对象中，model对象在request域中可见
        model.addAttribute("page",pageInfo);
        return "user_manager";
    }
    //删除一个员工信息
    @RequestMapping("user_delete_one")
    @ResponseBody
    public Map<String,Object> deleteOneUserInfo(Long userId,String username,Integer role, @RequestParam(value = "pageSize",defaultValue = "8")Integer pageSize, @RequestParam(value = "pageNumber",defaultValue = "1")Integer pageNumber){
        Integer row=0;
        Map<String,Object> map = new HashMap<>();
        map.put("row",row);
        //若为超级管理员，该账户不可以被删除
        if(role>=4){
            return map;
        }else{
            row = userManaerServiceImpl.deleteOneUser(userId);
            map.put("row",row);
            Logger logger = Logger.getLogger(UserManagerController.class);
            if(row>0){
                //删除成功
                logger.info("删除用户成功，删除用户姓名:"+username);
            }else{
                //出错了
                logger.info("删除用户失败");
            }
            return map;
        }
    }
    //更改用户权限
    @RequestMapping("alter_role")
    @ResponseBody
    public Map<String,Object> alterRole(Long role,Long id,String username,Long alterRole){
        Integer row = 0;
        Map<String,Object> map = new HashMap<>();
        map.put("row",row);
        //若为超级管理员，该账户不可以被更改
        if(role>=4){
            return map;
        }else{
            row = userManaerServiceImpl.updateUserRole(alterRole, id);
            Logger logger = Logger.getLogger(UserManagerController.class);
            map.put("row",row);
            if(row>0){
                logger.info("更改用户权限成功，用户"+username+"权限变更为"+alterRole);
            }else{
                logger.info("更改用户权限失败");
            }
            return map;
        }
    }
    //重置密码
    @RequestMapping("alter_password")
    @ResponseBody
    public Map<String, Object> alterPassord(Long id, String username, String alterPsd) {
        Integer row = 0;
        Map<String, Object> map = new HashMap<>();
        row = userManaerServiceImpl.updateUserPassword(id, alterPsd);
        Logger logger = Logger.getLogger(UserManagerController.class);
        map.put("row", row);
        if (row > 0) {
            logger.info("用户" + username + "重置密码为:" + alterPsd);
        } else {
            logger.info("重置密码失败");
        }
        return map;
    }
}
