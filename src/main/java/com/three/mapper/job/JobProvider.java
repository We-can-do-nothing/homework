package com.three.mapper.job;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class JobProvider {
    public String selectByJobName(Map map){
        SQL sql = new SQL();
        sql.SELECT("*").FROM("Job");
        if (map.get("JobName")!=null)
            sql.WHERE(" Job_name like " + "'%"+map.get("JobName")+"%'");
        sql.ORDER_BY("Job_id");
        sql.OFFSET((Integer)map.get("start"));
        sql.LIMIT((Integer) map.get("row"));
        return sql.toString();
    }

    public String getTotalByJobName(Map map){
        SQL sql = new SQL();
        sql.SELECT("count(Job_id)").FROM("Job");
        if (!map.get("JobName").equals(""));
        sql.WHERE(" Job_name like " + "'%"+map.get("JobName")+"%'");
        return sql.toString();
    }
}
