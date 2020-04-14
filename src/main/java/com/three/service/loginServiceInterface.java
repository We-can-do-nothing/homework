package com.three.service;

import com.three.bean.User;
import org.springframework.stereotype.Service;

@Service
public interface loginServiceInterface {

    User findByName(String name);

    void updatePasswordById(Integer id,  String newPassword);

    User findById(int userId);
}
