package com.three.service.impl;

import com.three.bean.User;
import com.three.mapper.user.UserMapperInterface;
import com.three.service.loginServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginService implements loginServiceInterface {

    @Autowired
    UserMapperInterface userMapper;

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public void updatePasswordById(Integer id, String newPassword) {
        userMapper.updatePasswordById(id, newPassword);
    }

    @Override
    public User findById(int userId) {
        return userMapper.findById(userId);
    }
}
