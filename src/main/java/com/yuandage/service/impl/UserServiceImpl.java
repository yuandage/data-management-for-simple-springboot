package com.yuandage.service.impl;

import com.yuandage.entity.User;
import com.yuandage.mapper.UserMapper;
import com.yuandage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public String encryptPassword(String password) {
        return userMapper.encryptPassword(password);
    }

    @Override
    public boolean signup(User user) {
        return userMapper.signup(user);
    }
}
