package com.three.mapper.document;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class docProvider {
    public String selectByTitle(Map map){
        SQL sql = new SQL();
        sql.SELECT("*").FROM("Document");
        if (!map.get("title").equals(""))
            sql.WHERE(" document_title like " + "'%"+map.get("title")+"%'");
        sql.ORDER_BY("document_id");
        sql.OFFSET((Integer) map.get("start"));
        sql.LIMIT((Integer) map.get("row"));
        return sql.toString();
    }

    public String getTotalByTitle(Map map){
        SQL sql = new SQL();
        sql.SELECT("count(document_id)").FROM("Document");
        if (!map.get("title").equals(""))
            sql.WHERE(" document_title like " + "'%"+map.get("title")+"%'");
        return sql.toString();
    }
}
