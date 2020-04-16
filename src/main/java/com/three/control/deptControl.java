package com.three.control;

import com.three.bean.User;
import com.three.bean.department;
import com.three.common.Page;
import com.three.service.deptServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class deptControl {

    @Autowired
    private deptServiceInterface deptService;

    private boolean userFirst;
    private String deptName = "";

    @GetMapping("dept/dept.html")
    String getDeptURL(ModelMap map){
        userFirst = true;
        deptName = "";
        //cpage当前页号
        int cpage = 1;
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从startrow行开始，查询pageSize条记录
        List<department> rows = deptService.selectusersPage(startrow, pageSize);
        int total = deptService.getTotal();
        //根据页面属性生成页面对象
        Page<department> page = new Page<department>(total, pageSize, navigatePages, cpage, rows);
        //传递到前台页面
        map.addAttribute("page", page);
        map.addAttribute("url","showall");
        map.addAttribute("urlPara","cpage");
        return "dept/dept";
    }

    @RequestMapping(value="/dept/DeptSearch", method = RequestMethod.POST)
    ModelAndView deptSearch(Integer cpage, String deptName){
        if (cpage == null)
            cpage = 1;
        userFirst = false;
        this.deptName = deptName;
        ModelAndView modelAndView=new ModelAndView();
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从0行开始，查询pageSize条记录
        List<department> rows = deptService.selectByDeptName(deptName, startrow, pageSize);
        int total = deptService.getTotalByDeptName(deptName);
        //根据页面属性生成页面对象
        Page<department> page = new Page<department>(total, pageSize, navigatePages, cpage, rows);
        //传递到前台页面
        modelAndView.setViewName("dept/dept::pageHtml");
        modelAndView.addObject("page", page);
        modelAndView.addObject("url","showall");
        modelAndView.addObject("urlPara","cpage");
        return modelAndView;
    }

    @RequestMapping(value="/dept/showDeptPage", method = RequestMethod.POST)
    ModelAndView showDeptPage(Integer cpage){
        if (userFirst == false)
            return deptSearch(cpage, this.deptName);
        ModelAndView modelAndView=new ModelAndView();
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从startrow行开始，查询pageSize条记录
        List<department> rows = deptService.selectusersPage(startrow, pageSize);
        int total = deptService.getTotal();
        //根据页面属性生成页面对象
        Page<department> page = new Page<department>(total, pageSize, navigatePages, cpage, rows);
        //传递到前台页面
        modelAndView.setViewName("dept/dept::pageHtml");
        modelAndView.addObject("page", page);
        modelAndView.addObject("url","showall");
        modelAndView.addObject("urlPara","cpage");
        return modelAndView;
    }


    @GetMapping("dept/showAddDept.html")
    String getAddDeptURL(ModelMap map){
        return "dept/showAddDept";
    }

    @GetMapping("dept/showUpdateDept.html")
    String getUpdateDept(ModelMap map, Integer dept_id){
        department department1 = deptService.selectById(dept_id);
        map.addAttribute("dept", department1);
        return "dept/showUpdateDept";
    }

    @RequestMapping(value="dept/updateDept", method = RequestMethod.POST)
    @ResponseBody
    String deptUpdate(ModelMap map, Integer depart_id, String depart_name, String depart_remark){
        deptService.updateDept(depart_name, depart_remark, depart_id);
        return "success";
    }

    @RequestMapping(value="/dept/removeDept", method = RequestMethod.POST)
    @ResponseBody
    public String removeDept(String ids){
        String[] idss = ids.split(",");
        for (String strNumber:idss) {
            deptService.deleteById(Integer.parseInt(strNumber));
        }
        return "success";
    }

    @RequestMapping("dept/deptAdd")
    @ResponseBody
    String deptAdd(ModelMap map, String deptName, String remark){
        deptService.AddDept(deptName, remark);
        return "success";
    }
}
