package com.three.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class User implements Serializable {
    private Integer user_id;
    private Integer Job_id;
    private String Loginname;
    private String password;
    private Integer Status;
    private Date Createdate;
    private String UserName;
    private String Faceurl;
    private String Facepath;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getJob_id() {
        return Job_id;
    }

    public void setJob_id(Integer job_id) {
        Job_id = job_id;
    }

    public String getLoginname() {
        return Loginname;
    }

    public void setLoginname(String loginname) {
        Loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Date getCreatedate() {
        return Createdate;
    }

    public void setCreatedate(Date createdate) {
        Createdate = createdate;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFaceurl() {
        return Faceurl;
    }

    public void setFaceurl(String faceurl) {
        Faceurl = faceurl;
    }

    public String getFacepath() {
        return Facepath;
    }

    public void setFacepath(String facepath) {
        Facepath = facepath;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", Job_id=" + Job_id +
                ", Loginname='" + Loginname + '\'' +
                ", password='" + password + '\'' +
                ", Status=" + Status +
                ", Createdate=" + Createdate +
                ", UserName='" + UserName + '\'' +
                ", Faceurl='" + Faceurl + '\'' +
                ", Facepath='" + Facepath + '\'' +
                '}';
    }
}
