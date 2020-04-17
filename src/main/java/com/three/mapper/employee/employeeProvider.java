package com.three.mapper.employee;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class employeeProvider {
    public String selectByManyMessage(Map map){
        SQL sql = new SQL();
        sql.SELECT("*").FROM("employee");
        if (!map.get("employeeName").equals(""))
            sql.WHERE(" employee_name like " + "'%"+map.get("employeeName")+"%'");
        if (!map.get("employeeCardId").equals(""))
            sql.WHERE(" employee_crad_id like " + "'%"+map.get("employeeCardId")+"%'");
        if ((Integer)map.get("job_id")!=0)
            sql.WHERE(" employee_job_id= " + map.get("job_id"));
        if ((Integer)map.get("sex")!=0)
            sql.WHERE(" employee_sex= " + map.get("sex"));
        if ((Integer)map.get("dept_id")!=0)
            sql.WHERE(" employee_depart_id= " + map.get("dept_id"));
        if (!map.get("phone").equals(""))
            sql.WHERE(" employee_phone like " + "'%"+map.get("phone")+"%'");
        sql.ORDER_BY("user_id");
        sql.OFFSET((Integer) map.get("start"));
        sql.LIMIT((Integer) map.get("row"));
        return sql.toString();
    }

    public String getTotalByManyMessage(Map map){
        SQL sql = new SQL();
        sql.SELECT("count(user_id)").FROM("employee");
        if (!map.get("employeeName").equals(""))
            sql.WHERE(" employee_name like " + "'%"+map.get("employeeName")+"%'");
        if (!map.get("employeeCardId").equals(""))
            sql.WHERE(" employee_crad_id like " + "'%"+map.get("employeeCardId")+"%'");
        if ((Integer)map.get("job_id")!=0)
            sql.WHERE(" employee_job_id= " + map.get("job_id"));
        if ((Integer)map.get("sex")!=0)
            sql.WHERE(" employee_sex= " + map.get("sex"));
        if ((Integer)map.get("dept_id")!=0)
            sql.WHERE(" employee_depart_id= " + map.get("dept_id"));
        if (!map.get("phone").equals(""))
            sql.WHERE(" employee_phone like " + "'%"+map.get("phone")+"%'");
        return sql.toString();
    }


//            (@Param("employeeName") String employeeName,
//                               @Param("employeeCardId") String employeeCardId,
//                               @Param("job_id") Integer job_id,
//                               @Param("sex") Integer sex,
//                               @Param("phone") String phone,
//                               @Param("dept_id") Integer dept_id);
}
