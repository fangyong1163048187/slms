package com.haiwen.service.impl;

import com.haiwen.mapper.UserManagerMapper;
import com.haiwen.pojo.PageInfo;
import com.haiwen.pojo.User;
import com.haiwen.service.UserManaerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserManagerServiceImpl implements UserManaerService {
    @Resource
    private UserManagerMapper userManagerMapper;
    @Override
    public PageInfo selectUser(String username, Integer role, Integer pageSize, Integer pageNumber) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setUsername(username);
        pageInfo.setRole(role);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageStart((pageNumber-1)*pageSize);
        //查不到结果时，list<object>类型返回一个实例化对象，大小为0，即调用size时为0
        List<User> users = userManagerMapper.selectUserByNameAndRole(username, role, pageInfo);
        pageInfo.setList(users);
        //查找到的符合条件的用户总人数
        Integer countOfUser = userManagerMapper.selectCountOfUser(username,role);
        //计算总页数
        Integer pageTotal = ((countOfUser%pageSize)==0)?(countOfUser/pageSize):(countOfUser/pageSize+1);
        //当员工人数为0时，总页数为1
        if(countOfUser==0){
            pageTotal = 1;
        }
        //把用户总人数放入pageInfo对象
        pageInfo.setPageCountOfUser(countOfUser);
        //把总页数放入pageInfo对象
        pageInfo.setPageTotal(pageTotal);
        return pageInfo;
    }

    /**
     * 删除一个用户
     * @param userId
     * @return
     */
    @Override
    public Integer deleteOneUser(Long userId) {
        return userManagerMapper.deleteOneUserById(userId);
    }

    /**
     * 更改用户权限
     * @param role
     * @param id
     * @return
     */
    @Override
    public Integer updateUserRole(Long role, Long id) {
        return userManagerMapper.updateUserRole(role, id);
    }

    /**
     * 重置密码为123456
     * @param id
     * @param password
     * @return
     */
    @Override
    public Integer updateUserPassword(Long id, String password) {
        //防止前端传过来的密码被人更改为别的密码
        password="123456";
        return userManagerMapper.updateUserPassword(id,password);
    }
}
