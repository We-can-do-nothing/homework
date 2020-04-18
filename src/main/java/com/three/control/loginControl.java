package com.three.control;

import com.three.bean.User;
import com.three.common.addUtils;
import com.three.service.faceServiceInterface;
import com.three.service.impl.faceService;
import com.three.service.loginServiceInterface;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class loginControl {

    addUtils addutils = new addUtils();

    @Autowired
    loginServiceInterface loginService;

    private static String LoginName;
    private static Integer id;

    @RequestMapping(value="/user/ajaxlogin", method = RequestMethod.POST)
    @ResponseBody
    public Object ajaxLogin(@RequestBody String name){
        User user = loginService.findByName(name);
        if (user!=null){
            LoginName = name;
            id = user.getUser_id();
            return user.getPassword();
        }else
            return "";
    }

    @GetMapping("/")
    public String index(ModelMap map) {
        // return模板文件的名称，对应src/main/resources/templates/loginForm.html
        return "loginForm";
    }

    @GetMapping("/main.html")
    public String mainIndex(ModelMap map, HttpServletResponse response){
        map.addAttribute("LoginName", LoginName);
        addutils.addCookie(response, "LoginName", LoginName);
        addutils.addCookie(response, "user_id", id.toString());
        map.addAttribute("user_id", id);
        return "main";
    }

    @GetMapping("face/face.html")
    public String face(ModelMap map){
        return "face/face";
    }


    @RequestMapping(value="/user/faceLogin", method = RequestMethod.POST)
    @ResponseBody
    public Object faceLogin(@RequestBody Map<String, Object> params) throws Exception {
        String base = (String) params.get("base");
        System.out.println(base);
        faceServiceInterface service = new faceService();
        int userId =  service.faceVerify(base);
        Map<String,Object> map = new HashMap<>();
        if (userId == -1)
            map.put("success",false);
        else{
            User user = loginService.findById(userId);
            LoginName = user.getLoginname();
            id = user.getUser_id();
            map.put("success",true);
        }
        return map;
    }
}
