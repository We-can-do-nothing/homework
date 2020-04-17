package com.three.service.impl;

import com.three.bean.Job;
import com.three.bean.department;
import com.three.bean.employee;
import com.three.mapper.employee.employeeMapperInterface;
import com.three.service.employeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class employeeService implements employeeServiceInterface {

    @Autowired
    employeeMapperInterface employeeMapper;

    @Override
    public List<department> selectDept() {
        return employeeMapper.selectDept();
    }

    @Override
    public List<Job> selectJob() {
        return employeeMapper.selectJob();
    }

    @Override
    public int getUserIdByUserName(String name) {
        return employeeMapper.getUserIdByUserName(name);
    }

    @Override
    public int getTotal() {
        return employeeMapper.getTotal();
    }

    @Override
    public employee selectById(Integer id) {
        return employeeMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        employeeMapper.deleteById(id);
    }

    @Override
    public List<employee> selectByManyMessage(String employeeName, String employeeCardId, Integer job_id, Integer sex, String phone, Integer dept_id, Integer start, Integer row) {
        return employeeMapper.selectByManyMessage(employeeName, employeeCardId, job_id, sex, phone, dept_id, start, row);
    }

    @Override
    public int getTotalByManyMessage(String employeeName, String employeeCardId, Integer job_id, Integer sex, String phone, Integer dept_id) {
        return employeeMapper.getTotalByManyMessage(employeeName, employeeCardId, job_id, sex, phone, dept_id);
    }

    @Override
    public List<employee> selectusersPage(Integer start, Integer row) {
        return employeeMapper.selectusersPage(start, row);
    }

    @Override
    public void addEmployee(Integer user_id, Integer depart_id, Integer job_id, String name, String crad_id, String address, String post_code, String tel, String phone, String qq, String email, Integer sex, String party, String bir, String race, String edu, String spe, String hobby, String remark) {
        employeeMapper.addEmployee(user_id, depart_id, job_id, name, crad_id, address, post_code, tel, phone, qq, email, sex, party, bir, race, edu, spe, hobby, remark);
    }
}
