package com.three.service.impl;

import com.three.mapper.UserMapperInterface;
import com.three.service.userServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

public class userService implements userServiceInterface {

    @Autowired
    UserMapperInterface userMapper;

    @Override
    public void insertUser(String name, String password, Integer status, String UserName) {
        userMapper.insertUser(name, password, status, UserName);
    }
}
