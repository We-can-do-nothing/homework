package com.three.control;

import com.three.service.faceServiceInterface;
import com.three.service.impl.faceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@Controller
public class mainControl {
//    人脸注册
    @GetMapping("user/face.html")
    public String userFace(Model map){
        return "user/face";
    }

//    修改密码
    @GetMapping("editPassword/editPassword.html")
    public String editPassword(ModelMap map){
        return "editPassword/editPassword";
    }

//    退出登陆
    @GetMapping("loginForm.html")
    public String loginOut(ModelMap map){
        return "loginForm";
    }

    @RequestMapping(value="/user/faceRegister", method = RequestMethod.POST)
    @ResponseBody
    public Object faceRegister(@RequestBody String img){
        faceServiceInterface service = new faceService();
        return service.faceRegister(img);
    }
}
