package com.three.otherTset;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class splitTest {
    public static void main(String[] args) throws ParseException {
        String str = "2020/4/16";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = sdf.parse(str);
        Date date = new Date(str);
        System.out.println(date);
    }
}
