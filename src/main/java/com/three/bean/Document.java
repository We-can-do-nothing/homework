package com.three.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class Document implements Serializable {
    private Integer Document_id;
    private String Document_title;
    private String Document_filename;
    private String Document_remark;
    private String Document_create_time;
    private Integer Document_user_id;

    public Document(Integer document_id, String document_title, String document_filename, String document_remark, String document_create_time, Integer document_user_id){
        Document_id = document_id;
        Document_title = document_title;
        Document_filename = document_filename;
        Document_remark = document_remark;
        Document_create_time = document_create_time;
        Document_user_id = document_user_id;
    }

    public Integer getDocument_id(){ return Document_id;}
    public void getDocument_id(Integer document_id){ this.Document_id = document_id;}
    public String getDocument_title(){ return Document_title;}
    public void getDocument_title(String document_title){ this.Document_title = document_title;}
    public String getDocument_filename(){ return Document_filename;}
    public void getDocument_filename(String document_filename){ this.Document_filename = document_filename;}
    public String getDocument_remark(){ return Document_remark;}
    public void getDocument_remark(String document_remark){ this.Document_remark = document_remark;}
    public String getDocument_create_time(){ return Document_create_time;}
    public void getDocument_create_time(String document_create_time){ this.Document_create_time = document_create_time;}
    public Integer getDocument_user_id(){ return Document_user_id;}
    public void getDocument_user_id(Integer document_user_id){ this.Document_user_id = document_user_id;}

    @Override
    public String toString() {
        return "Document{" +
                "Document_id=" + Document_id +
                ", Document_title=" + Document_title +
                ", Document_filename='" + Document_filename + '\'' +
                ", Document_remark='" + Document_remark + '\'' +
                ", Document_create_time=" + Document_create_time +
                ", Document_user_id=" + Document_user_id +
                '}';
    }
}
