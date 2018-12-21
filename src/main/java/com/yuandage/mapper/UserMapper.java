package com.yuandage.mapper;

import com.yuandage.entity.User;

public interface UserMapper {

    User findByName(String name);

    User findById(Integer id);

    String encryptPassword(String password);

    boolean signup(User user);
}
