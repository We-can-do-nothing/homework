package com.three.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class addUtils {
    public void addCookie(HttpServletResponse response, String cookieName, String cookieValue){
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(1000);
        response.addCookie(cookie);
    }
}
