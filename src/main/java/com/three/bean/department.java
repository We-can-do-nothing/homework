package com.three.bean;

public class department {
    private Integer depart_id;
    private String depart_name;
    private String depart_remark;

    public Integer getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(Integer depart_id) {
        this.depart_id = depart_id;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }

    public String getDepart_remark() {
        return depart_remark;
    }

    public void setDepart_remark(String depart_remark) {
        this.depart_remark = depart_remark;
    }

    @Override
    public String toString() {
        return "department{" +
                "depart_id=" + depart_id +
                ", depart_name='" + depart_name + '\'' +
                ", depart_remark='" + depart_remark + '\'' +
                '}';
    }
}
