package com.three.control;

import com.three.bean.User;
import com.three.bean.editPassword;
import com.three.common.searchUtils;
import com.three.service.faceServiceInterface;
import com.three.service.impl.faceService;
import com.three.service.loginServiceInterface;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Controller
public class mainControl {

    searchUtils utils = new searchUtils();

    @Autowired
    loginServiceInterface loginService;

    private static Integer id;

//    人脸注册
    @GetMapping("user/face.html")
    public String userFace(Model map){
        return "user/face";
    }

//    跳转到修改密码
    @GetMapping("editPassword/editPassword.html")
    public String editPassword(ModelMap map, Integer user_id){
        map.addAttribute("user_id", user_id);
        return "editPassword/editPassword";
    }

//    退出登陆
    @GetMapping("loginForm.html")
    public String loginOut(ModelMap map){
        return "loginForm";
    }

//    人脸注册
    @RequestMapping(value="/user/faceRegister", method = RequestMethod.POST)
    @ResponseBody
    public Object faceRegister(@RequestBody Map<String, Object> params, HttpServletRequest request){
        String base = (String) params.get("base");
        String cookieLoginName = utils.searchCookie(request, "LoginName");
        User user = loginService.findByName(cookieLoginName);
        // 人脸注册一定能找到user（已经登陆）,故不用判断，如果出错，那就是服务器挂了，错误最好不要捕捉
        faceServiceInterface service = new faceService();
        Map<String,Object> map = new HashMap<>();
        if(service.faceRegister(base, user.getUser_id()))
            map.put("success",true);
        else
            map.put("success",false);
        return map;
    }

    // 修改密码
    @RequestMapping(value="/user/editPassword", method = RequestMethod.POST)
    @ResponseBody
    public Object editPassword(@RequestBody editPassword passwords, HttpServletRequest request){
        User user = loginService.findById(passwords.getUser_id());
        HashMap<String,String> indMap = new HashMap<String,String>();
        if (passwords.getOldPassword().equals(user.getPassword())){
            loginService.updatePasswordById(passwords.getUser_id(), passwords.getNewPassword());
            indMap.put("message", "success");
        }else
            indMap.put("message", "fail");
        return JSONObject.valueToString(indMap);
    }
}
