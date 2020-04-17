package com.three.bean;

import java.util.Date;

public class employee {
    private Integer user_id;
    private Integer employee_depart_id;
    private Integer employee_job_id;
    private String employee_name;
    private String employee_crad_id;
    private String employee_address;
    private String employee_post_code;
    private String employee_tel;
    private String employee_phone;
    private String employee_qq;
    private String employee_email;
    private Integer employee_sex;
    private String employee_party;
    private Date employee_bir;
    private String employee_race;
    private String employee_edu;
    private String employee_spe;
    private String employee_hobby;
    private String employee_remark;
    private Date create_time;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getEmployee_depart_id() {
        return employee_depart_id;
    }

    public void setEmployee_depart_id(Integer employee_depart_id) {
        this.employee_depart_id = employee_depart_id;
    }

    public Integer getEmployee_job_id() {
        return employee_job_id;
    }

    public void setEmployee_job_id(Integer employee_job_id) {
        this.employee_job_id = employee_job_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_crad_id() {
        return employee_crad_id;
    }

    public void setEmployee_crad_id(String employee_crad_id) {
        this.employee_crad_id = employee_crad_id;
    }

    public String getEmployee_address() {
        return employee_address;
    }

    public void setEmployee_address(String employee_address) {
        this.employee_address = employee_address;
    }

    public String getEmployee_post_code() {
        return employee_post_code;
    }

    public void setEmployee_post_code(String employee_post_code) {
        this.employee_post_code = employee_post_code;
    }

    public String getEmployee_tel() {
        return employee_tel;
    }

    public void setEmployee_tel(String employee_tel) {
        this.employee_tel = employee_tel;
    }

    public String getEmployee_phone() {
        return employee_phone;
    }

    public void setEmployee_phone(String employee_phone) {
        this.employee_phone = employee_phone;
    }

    public String getEmployee_qq() {
        return employee_qq;
    }

    public void setEmployee_qq(String employee_qq) {
        this.employee_qq = employee_qq;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }

    public Integer getEmployee_sex() {
        return employee_sex;
    }

    public void setEmployee_sex(Integer employee_sex) {
        this.employee_sex = employee_sex;
    }

    public String getEmployee_party() {
        return employee_party;
    }

    public void setEmployee_party(String employee_party) {
        this.employee_party = employee_party;
    }

    public Date getEmployee_bir() {
        return employee_bir;
    }

    public void setEmployee_bir(Date employee_bir) {
        this.employee_bir = employee_bir;
    }

    public String getEmployee_race() {
        return employee_race;
    }

    public void setEmployee_race(String employee_race) {
        this.employee_race = employee_race;
    }

    public String getEmployee_edu() {
        return employee_edu;
    }

    public void setEmployee_edu(String employee_edu) {
        this.employee_edu = employee_edu;
    }

    public String getEmployee_spe() {
        return employee_spe;
    }

    public void setEmployee_spe(String employee_spe) {
        this.employee_spe = employee_spe;
    }

    public String getEmployee_hobby() {
        return employee_hobby;
    }

    public void setEmployee_hobby(String employee_hobby) {
        this.employee_hobby = employee_hobby;
    }

    public String getEmployee_remark() {
        return employee_remark;
    }

    public void setEmployee_remark(String employee_remark) {
        this.employee_remark = employee_remark;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "employee{" +
                "user_id=" + user_id +
                ", employee_depart_id=" + employee_depart_id +
                ", employee_job_id=" + employee_job_id +
                ", employee_name='" + employee_name + '\'' +
                ", employee_crad_id='" + employee_crad_id + '\'' +
                ", employee_address='" + employee_address + '\'' +
                ", employee_post_code='" + employee_post_code + '\'' +
                ", employee_tel='" + employee_tel + '\'' +
                ", employee_phone='" + employee_phone + '\'' +
                ", employee_qq='" + employee_qq + '\'' +
                ", employee_email='" + employee_email + '\'' +
                ", employee_sex=" + employee_sex +
                ", employee_party='" + employee_party + '\'' +
                ", employee_bir=" + employee_bir +
                ", employee_race='" + employee_race + '\'' +
                ", employee_edu='" + employee_edu + '\'' +
                ", employee_spe='" + employee_spe + '\'' +
                ", employee_hobby='" + employee_hobby + '\'' +
                ", employee_remark='" + employee_remark + '\'' +
                ", create_time=" + create_time +
                '}';
    }
}
