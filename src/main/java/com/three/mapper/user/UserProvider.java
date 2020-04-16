package com.three.mapper.user;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class UserProvider {
    public String sqlUser(Map map){
        SQL sql = new SQL();
        sql.SELECT("*").FROM("User");
        if (!map.get("UserName").equals("")){
            String name = "'%"+map.get("UserName")+"%'";
            sql.WHERE("UserName like " + name);
        }
        if ((Integer)map.get("status")!=0){
            sql.WHERE(" Status= " + map.get("status"));
        }
        sql.ORDER_BY("user_id");
        sql.OFFSET((Integer) map.get("startrow"));
        sql.LIMIT((Integer) map.get("num"));
        return sql.toString();
    }

    public String sqlSearchByLS(Map map){
        SQL sql = new SQL();
        sql.SELECT("count(user_id)").FROM("User");
        if ((Integer)map.get("status")!=0){
            sql.WHERE(" Status= " + map.get("status"));
        }
        if (!map.get("UserName").equals("")){
            String name = "'%"+map.get("UserName")+"%'";
            sql.WHERE("UserName like " + name);
        }
        return sql.toString();
    }
}