package com.three.service.impl;

import com.three.bean.User;
import com.three.mapper.UserMapperInterface;
import com.three.service.loginServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginService implements loginServiceInterface {

    @Autowired
    UserMapperInterface userMapper;

    @Override
    public User findByName(String name) {
        if (userMapper == null)
            System.out.println("null");
        return userMapper.findByName(name);
    }
}
