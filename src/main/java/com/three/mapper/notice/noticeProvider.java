package com.three.mapper.notice;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class noticeProvider {
    public String selectByTC(Map map){
        SQL sql = new SQL();
        sql.SELECT("*").FROM("notice");
        if(!map.get("title").equals(""))
            sql.WHERE(" notice_title like " + "'%"+map.get("title")+"%'");
        if(!map.get("content").equals(""))
            sql.WHERE(" notice_content like " + "'%"+map.get("content")+"%'");
        sql.ORDER_BY("notice_id");
        sql.OFFSET((Integer)map.get("start"));
        sql.LIMIT((Integer) map.get("row"));
        return sql.toString();
    }

    public String getTotalByTC(Map map){
        SQL sql = new SQL();
        sql.SELECT("count(notice_id)").FROM("notice");
        if(!map.get("title").equals(""))
            sql.WHERE(" notice_title like " + "'%"+map.get("title")+"%'");
        if(!map.get("content").equals(""))
            sql.WHERE(" notice_content like " + "'%"+map.get("content")+"%'");
        return sql.toString();
    }
}
