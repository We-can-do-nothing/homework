package com.three.mapper.dept;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class DeptProvider {
    public String selectByDeptName(Map map){
        SQL sql = new SQL();
        sql.SELECT("*").FROM("department");
        if (map.get("deptName")!=null && !map.get("deptName").equals(""))
            sql.WHERE(" depart_name like " + "'%"+map.get("deptName")+"%'");
        sql.ORDER_BY("depart_id");
        sql.OFFSET((Integer)map.get("start"));
        sql.LIMIT((Integer) map.get("row"));
        return sql.toString();
    }

    public String getTotalByDeptName(Map map){
        SQL sql = new SQL();
        sql.SELECT("count(depart_id)").FROM("department");
        if (!map.get("deptName").equals(""))
            sql.WHERE(" depart_name like " + "'%"+map.get("deptName")+"%'");
        return sql.toString();
    }
}
