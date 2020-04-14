package com.three.control;

import com.three.bean.User;
import com.three.common.Page;
import com.three.service.faceServiceInterface;
import com.three.service.impl.faceService;
import com.three.service.userServiceInterface;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class userControl {

    @Autowired
    private userServiceInterface UserService;

    @GetMapping("user/user.html")
    public String user(ModelMap map, HttpSession session, Integer cpage){
        //cpage当前页号
        if (cpage == null) {
            cpage = 1;
        }
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从startrow行开始，查询pageSize条记录
        List<User> rows = UserService.selectusersPage(startrow, pageSize);
        int total = UserService.getTotal();
        //根据页面属性生成页面对象
        Page<User> page = new Page<User>(total, pageSize, navigatePages, cpage, rows);
        //传递到前台页面
        map.addAttribute("page", page);
        return "user/user";
    }

    @GetMapping("user/showAddUser.html")
    public String showAddUser(ModelMap map){
        return "user/showAddUser";
    }

    @GetMapping("user/showUpdateUser.html")
    public String showUpdateUser(ModelMap map, Integer user_id){
        User user = UserService.findById(user_id);
        map.addAttribute("user", user);
        return "user/showUpdateUser";
    }

    @RequestMapping(value="/user/addUser", method = RequestMethod.POST)
    @ResponseBody
    public Object addUser(@RequestBody String userData){
        HashMap<String, String> map = new HashMap<>();
        String[] userDatas = userData.split(":");
        UserService.insertUser(userDatas[1], userDatas[2], Integer.parseInt(userDatas[3]), userDatas[0]);
        map.put("message", "success");
        return JSONObject.valueToString(map);
    }

    @RequestMapping(value="/user/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public Object updateUser(@RequestBody String userData){
        String[] userDatas = userData.split(":");
        UserService.updateUserById(userDatas[1], Integer.parseInt(userDatas[2]), userDatas[3], Integer.parseInt(userDatas[0]));
        HashMap<String,String> indMap = new HashMap<String,String>();
        indMap.put("message", "success");
        return JSONObject.valueToString(indMap);
    }

    @RequestMapping(value="/user/removeUser", method = RequestMethod.POST)
    @ResponseBody
    public Object removeUser(String ids){
        String[] idss = ids.split(",");
        for (String strNumber:idss) {
            UserService.deleteById(Integer.parseInt(strNumber));
        }
        return "success";
    }
}
