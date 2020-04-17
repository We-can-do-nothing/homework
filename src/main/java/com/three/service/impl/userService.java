package com.three.service.impl;

import com.three.bean.User;
import com.three.mapper.user.UserMapperInterface;
import com.three.service.userServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService implements userServiceInterface {

    @Autowired
    UserMapperInterface userMapper;

    @Override
    public void insertUser(String name, String password, Integer status, String UserName) {
        userMapper.insertUser(name, password, status, UserName);
    }

    @Override
    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    @Override
    public List<User> selectusersPage(int startrow, int num) {
        return userMapper.selectusersPage(startrow, num);
    }

    @Override
    public int getTotal() {
        return userMapper.getTotal();
    }

    @Override
    public int getTotalByLS(Integer status, String loginName) {
        return userMapper.getTotalByLS(status, loginName);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public void updateUserById(String username, Integer status, String loginName, Integer id) {
        userMapper.updateUserById(username, status, loginName, id);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public List<User> queryByUserNameAndStatus(String UserName, Integer status, int startrow, int num) {
        return userMapper.queryByUserNameAndStatus(UserName, status, startrow, num);
    }

    @Override
    public String findNameById(Integer user_id) {
        return userMapper.findNameById(user_id);
    }
}
