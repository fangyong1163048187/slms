package com.haiwen.mapper;

import com.haiwen.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * 注册成功插入用户数据
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    User selectUserByName(@Param("username") String username);

    /**
     * 通过用户名和密码查找用户
     * @param user
     * @return
     */
    User selectUserByNameAndPsd(User user);

    /**
     * 通过用户ID查找用户
     * @param id
     * @return
     */
    User selectUserById(@Param("id") Long id);
}
