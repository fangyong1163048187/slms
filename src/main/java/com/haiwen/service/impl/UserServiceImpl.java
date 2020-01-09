package com.haiwen.service.impl;

import com.haiwen.mapper.UserMapper;
import com.haiwen.pojo.User;
import com.haiwen.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public int insertRegister(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User selectUserNameIsUnique(String username) {
        User user = userMapper.selectUserByName(username);
        return user;
    }

    @Override
    public User selectCheckLogin(User user) {
        return userMapper.selectUserByNameAndPsd(user);
    }

    @Override
    public User selectRememberPsd(Long id) {
        User user = userMapper.selectUserById(id);
        return user;
    }
}
