package com.three.mapper.dept;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class deptMapperInterfaceTest {

    @Autowired
    private deptMapperInterface dept;

    @Test
    void updateDept() {
        dept.updateDept("技术部", "码农", 1);
        System.out.println("success");
    }
}