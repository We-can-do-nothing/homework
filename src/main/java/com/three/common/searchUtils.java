package com.three.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class searchUtils {
    public String searchCookie(HttpServletRequest request, String searchName){
        Cookie[] cookies =  request.getCookies();//获取保存在request的所有cookie
        if(cookies != null){//判断cookies数组是否为空
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(searchName)) //通过for循环找到想要获取的值
                    return cookie.getValue();
            }
        }
        return "";
    }
}
