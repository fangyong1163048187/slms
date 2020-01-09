package com.haiwen.mapper;

import com.haiwen.pojo.PageInfo;
import com.haiwen.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserManagerMapper {
    /**
     * 根据用户名和用户角色查找用户
     * @param username
     * @param role
     * @param pageInfo
     * @return
     */
    List<User> selectUserByNameAndRole(@Param("username")String username, @Param("role")Integer role, @Param("pageInfo")PageInfo pageInfo);

    /**
     * 查找符合条件的用户人数
     * @param username
     * @param role
     * @return
     */
    Integer selectCountOfUser(@Param("username")String username, @Param("role")Integer role);
    /**
     * 通过用户ID删除一个用户
     * @param userId
     * @return
     */
    Integer deleteOneUserById(Long userId);

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
