package com.three.control;

import com.three.bean.User;
import com.three.mapper.UserMapperInterface;
import com.three.service.faceServiceInterface;
import com.three.service.impl.faceService;
import com.three.service.impl.loginService;
import com.three.service.loginServiceInterface;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class loginControl {

    @Autowired
    loginServiceInterface loginService;

    @RequestMapping(value="/user/ajaxlogin", method = RequestMethod.POST)
    @ResponseBody
    public Object ajaxLogin(@RequestBody String name){
        User user = loginService.findByName(name);
        return user.getPassword();
    }

    @GetMapping("/")
    public String index(ModelMap map) {
        // return模板文件的名称，对应src/main/resources/templates/loginForm.html
        return "loginForm";
    }

    @GetMapping("/main.html")
    public String mainIndex(ModelMap map){
        return "main";
    }

    @GetMapping("face/face.html")
    public String face(ModelMap map){
        return "face/face";
    }

    @RequestMapping(value="/user/faceLogin", method = RequestMethod.POST)
    @ResponseBody
    public Object faceLogin(@RequestBody String img){
        faceServiceInterface service = new faceService();
        return service.faceVerify(img);
    }
}
