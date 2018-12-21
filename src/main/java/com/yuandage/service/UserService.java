package com.yuandage.service;

import com.yuandage.entity.User;

public interface UserService {

    User findByName(String name);

    User findById(Integer id);

    String encryptPassword(String password);

    boolean signup(User user);
}
