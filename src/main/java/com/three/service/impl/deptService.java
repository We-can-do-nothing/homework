package com.three.service.impl;

import com.three.bean.department;
import com.three.mapper.dept.deptMapperInterface;
import com.three.service.deptServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class deptService implements deptServiceInterface {

    @Autowired
    deptMapperInterface deptMapper;

    @Override
    public List<department> selectDeptAll() {
        return deptMapper.selectDeptAll();
    }

    @Override
    public void AddDept(String deptName, String remark) {
        deptMapper.AddDept(deptName, remark);
    }

    @Override
    public int getTotal() {
        return deptMapper.getTotal();
    }

    @Override
    public List<department> selectusersPage(int start, int row) {
        return deptMapper.selectusersPage(start, row);
    }

    @Override
    public department selectById(Integer id) {
        return  deptMapper.selectById(id);
    }

    @Override
    public void updateDept(String name, String remark, Integer id) {
        deptMapper.updateDept(name, remark, id);
    }

    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public List<department> selectByDeptName(String deptName, Integer start, Integer row) {
        return deptMapper.selectByDeptName(deptName, start, row);
    }

    @Override
    public int getTotalByDeptName(String deptName) {
        return deptMapper.getTotalByDeptName(deptName);
    }
}
