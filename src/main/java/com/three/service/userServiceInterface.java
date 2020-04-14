package com.three.service;

import com.three.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface userServiceInterface {
    void insertUser(String name, String password, Integer status, String UserName);

    List<User> queryAllUser();

    List<User> selectusersPage(int startrow,int num);

    int getTotal();

    User findById(Integer id);

    void updateUserById(String username, Integer status, String loginName, Integer id);

    void deleteById(Integer id);
}
