package com.three.service.impl;

import com.three.bean.Job;
import com.three.mapper.job.jobMapperInterface;
import com.three.service.jobServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class jobService implements jobServiceInterface {

    @Autowired
    jobMapperInterface jobMapper;

    @Override
    public void addJob(String name, String remark) {
        jobMapper.addJob(name, remark);
    }

    @Override
    public int getTotal() {
        return jobMapper.getTotal();
    }

    @Override
    public List<Job> selectusersPage(int start, int row) {
        return jobMapper.selectusersPage(start, row);
    }

    @Override
    public Job selectById(Integer id) {
        return jobMapper.selectById(id);
    }

    @Override
    public void updateJob(Integer id, String name, String remark) {
        jobMapper.updateJob(id, name, remark);
    }

    @Override
    public void deleteById(Integer id) {
        jobMapper.deleteById(id);
    }

    @Override
    public List<Job> selectByJobName(String JobName, Integer start, Integer row) {
        return jobMapper.selectByJobName(JobName, start, row);
    }

    @Override
    public int getTotalByJobName(String JobName) {
        return jobMapper.getTotalByJobName(JobName);
    }
}
