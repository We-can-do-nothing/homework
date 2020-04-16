package com.three.bean;

public class Job {
    private Integer Job_id;
    private String Job_name;
    private String Job_remark;

    public Integer getJob_id() {
        return Job_id;
    }

    public void setJob_id(Integer job_id) {
        Job_id = job_id;
    }

    public String getJob_name() {
        return Job_name;
    }

    public void setJob_name(String job_name) {
        Job_name = job_name;
    }

    public String getJob_remark() {
        return Job_remark;
    }

    @Override
    public String toString() {
        return "Job{" +
                "Job_id=" + Job_id +
                ", Job_name='" + Job_name + '\'' +
                ", Job_remark='" + Job_remark + '\'' +
                '}';
    }

    public void setJob_remark(String job_remark) {
        Job_remark = job_remark;
    }
}
