package com.three.bean;

import java.io.Serializable;
import java.util.Date;

public class notice implements Serializable {
    private Integer notice_id;
    private String notice_title;
    private String notice_content;
    private Date notice_create_date;
    private Integer notice_user_id;

    public Integer getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(Integer notice_id) {
        this.notice_id = notice_id;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    public Date getNotice_create_date() {
        return notice_create_date;
    }

    public void setNotice_create_date(Date notice_create_date) {
        this.notice_create_date = notice_create_date;
    }

    public Integer getNotice_user_id() {
        return notice_user_id;
    }

    public void setNotice_user_id(Integer notice_user_id) {
        this.notice_user_id = notice_user_id;
    }

    @Override
    public String toString() {
        return "notice{" +
                "notice_id=" + notice_id +
                ", notice_title='" + notice_title + '\'' +
                ", notice_content='" + notice_content + '\'' +
                ", notice_create_date=" + notice_create_date +
                ", notice_user_id=" + notice_user_id +
                '}';
    }
}
