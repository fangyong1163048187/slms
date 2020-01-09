package com.haiwen.interceptor;

import com.haiwen.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //登录验证
        HttpSession session = httpServletRequest.getSession();
        //获取当前用户
        User user = (User)session.getAttribute("user");
        //获取请求URI
        String uri = httpServletRequest.getRequestURI();
        if("/login_submit".equals(uri) || "/login_ck_name_psd".equals(uri) || "/register_check_username".equals(uri) || "/registersubmit".equals(uri) || "/login".equals(uri) || "/register".equals(uri) || uri.contains("/css/") || uri.contains("/files/") || uri.contains("/images/") || uri.contains("/js/")){
            //若当前请求是登录页面和注册页面发来的请求或者是静态资源，则放行
            return true;
        }else{
            if(user==null || user.getUsername()==null){
                //若用户不存在，跳转到登录页面
                httpServletResponse.sendRedirect("/login");
                return false;
            }else{
                //若用户存在，放行
                return true;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //记录运行过程中出现的所有异常，以便查找出错问题原因
    }
}
