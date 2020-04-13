package com.three.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class userControl {
    @GetMapping("user/user.html")
    public String user(ModelMap map){
        return "user/user";
    }

    @GetMapping("user/showAddUser.html")
    public String showAddUser(ModelMap map){
        return "user/showAddUser";
    }

    @GetMapping("user/showUpdateUser.html")
    public String showUpdateUser(ModelMap map){
        return "user/showUpdateUser";
    }
}
