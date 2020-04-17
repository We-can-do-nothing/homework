//package com.three.mapper;
//
//import com.three.bean.User;
//import com.three.mapper.user.UserMapperInterface;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserMapperTest {
//
//    @Autowired
//    private UserMapperInterface userMapper;
//
//    @Test
//    void findByName() {
//        User user = userMapper.findByName("admin");
//        System.out.println(user.getPassword());
//    }
//}