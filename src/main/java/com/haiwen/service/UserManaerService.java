package com.haiwen.service;

import com.haiwen.pojo.PageInfo;

public interface UserManaerService {
    /**
     * 查找用户信息
     * @param username
     * @param role
     * @param pageSize
     * @param pageNumber
     * @return
     */
    PageInfo selectUser(String username, Integer role, Integer pageSize, Integer pageNumber);
    /**
     * 删除一个用户
     * @param userId
     * @return
     */
    Integer deleteOneUser(Long userId);

    /**
     * 更改用户权限
     * @param role
     * @param id
     * @return
     */
    Integer updateUserRole(Long role,Long id);

    /**
     * 重置密码为123456
     * @param id
     * @param password
     * @return
     */
    Integer updateUserPassword(Long id,String password);
}
