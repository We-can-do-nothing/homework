package com.three.service;

import com.three.bean.department;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface deptServiceInterface {
    List<department> selectDeptAll();

    void AddDept(String deptName, String remark);

    int getTotal();

    List<department> selectusersPage(int start, int row);

    department selectById(Integer id);

    void updateDept(String name, String remark, Integer id);

    void deleteById(Integer id);

    List<department> selectByDeptName(String deptName, Integer start, Integer row);

    int getTotalByDeptName(String deptName);
}
