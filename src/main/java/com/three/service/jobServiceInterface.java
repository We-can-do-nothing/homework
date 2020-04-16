package com.three.service;

import com.three.bean.Job;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface jobServiceInterface {
    void addJob(String name, String remark);

    int getTotal();

    List<Job> selectusersPage(int start, int row);

    Job selectById(Integer id);

    void updateJob(Integer id,String name, String remark);

    void deleteById(Integer id);

    List<Job> selectByJobName(String JobName, Integer start, Integer row);

    int getTotalByJobName(String JobName);
}
