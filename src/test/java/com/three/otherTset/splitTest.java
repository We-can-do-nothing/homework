package com.three.otherTset;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class splitTest {
    public static void main(String[] args) throws ParseException {

        Map<String, String > map = new HashMap<>();
        map.put("Jan", "01");
        map.put("Feb", "02");
        map.put("Mar", "03");
        map.put("Apr", "04");
        map.put("May", "05");
        map.put("Jun", "06");
        map.put("Jul", "07");
        map.put("Aug", "08");
        map.put("Sep", "09");
        map.put("Oct", "10");
        map.put("Nov", "11");
        map.put("Dec", "12");
        String str = "Mon Apr 13 19:03:11 GMT+08:00 2020";
        String[] strs = str.split(" ");
        String newStr = strs[5] + "-" + map.get(strs[1]) + "-" + strs[2];
        System.out.println(newStr);
    }
}
