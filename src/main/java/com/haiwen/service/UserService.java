package com.haiwen.service;

import com.haiwen.pojo.User;

public interface UserService {
    /**
     * 注册成功时插入用户数据
     * @param user
     * @return
     */
    int insertRegister(User user);

    /**
     * 查找用户名是否重复
     * @param username
     * @return
     */
    User selectUserNameIsUnique(String username);

    /**
     * 检查用户名和密码是否正确
     * @param user
     * @return
     */
    User selectCheckLogin(User user);

    /**
     * 记住密码
     * 通过存在cookie的用户ID查找用户信息，实现免登陆
     * @param id
     * @return
     */
    User selectRememberPsd(Long id);
}
