package com.three.control;

import com.three.bean.Job;
import com.three.bean.department;
import com.three.bean.employee;
import com.three.common.Page;
import com.three.service.employeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class employeeControl {

    @Autowired
    employeeServiceInterface employeeService;

    private boolean userFirst;
    private Integer job_id;
    private String name;
    private String card_id;
    private Integer sex;
    private String phone;
    private Integer dept_id;

    @GetMapping("employee/employee.html")
    String employee(ModelMap map) {
        userFirst = true;
        job_id = 0;
        name = "";
        card_id = "";
        sex = 0;
        phone = "";
        dept_id = 0;
        //cpage当前页号
        int cpage = 1;
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从startrow行开始，查询pageSize条记录
        List<employee> rows = employeeService.selectusersPage(startrow, pageSize);
        int total = employeeService.getTotal();
        //根据页面属性生成页面对象
        Page<employee> page = new Page<employee>(total, pageSize, navigatePages, cpage, rows);
        //传递到前台页面
        map.addAttribute("page", page);
        map.addAttribute("url","showall");
        List<Job> jobs = employeeService.selectJob();
        List<department> departments = employeeService.selectDept();

        HashMap<Integer, String> jobMap = new HashMap<Integer, String>();
        for (Job job:jobs)
            jobMap.put(job.getJob_id(), job.getJob_name());
        map.addAttribute("jobMap",jobMap);

        HashMap<Integer, String> deptMap = new HashMap<Integer, String>();
        for (department dept:departments)
            jobMap.put(dept.getDepart_id(), dept.getDepart_name());
        map.addAttribute("deptMap",deptMap);

        map.addAttribute("jobs", jobs);
        map.addAttribute("departments", departments);
        map.addAttribute("urlPara","cpage");
        return "employee/employee";
    }

    @RequestMapping(value="/employee/employeeSearch", method = RequestMethod.POST)
    ModelAndView deptSearch(Integer cpage, String employeeName, String employeeCardId, Integer job_id, Integer sex, String phone, Integer dept_id){
        if (cpage == null)
            cpage = 1;
        userFirst = false;
        this.name = employeeName;
        this.card_id = employeeCardId;
        this.job_id = job_id;
        this.sex = sex;
        this.phone = phone;
        this.dept_id = dept_id;
        ModelAndView modelAndView=new ModelAndView();
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从0行开始，查询pageSize条记录
        List<employee> rows = employeeService.selectByManyMessage(employeeName,employeeCardId,job_id,sex,phone,dept_id, startrow, pageSize);
        int total = employeeService.getTotalByManyMessage(employeeName, employeeCardId, job_id, sex, phone, dept_id);
        //根据页面属性生成页面对象
        Page<employee> page = new Page<employee>(total, pageSize, navigatePages, cpage, rows);
        //传递到前台页面
        modelAndView.setViewName("employee/employee::pageHtml");
        modelAndView.addObject("page", page);
        List<Job> jobs = employeeService.selectJob();
        List<department> departments = employeeService.selectDept();

        HashMap<Integer, String> jobMap = new HashMap<Integer, String>();
        for (Job job:jobs)
            jobMap.put(job.getJob_id(), job.getJob_name());
        modelAndView.addObject("jobMap",jobMap);

        HashMap<Integer, String> deptMap = new HashMap<Integer, String>();
        for (department dept:departments)
            jobMap.put(dept.getDepart_id(), dept.getDepart_name());
        modelAndView.addObject("deptMap",deptMap);

        modelAndView.addObject("jobs", jobs);
        modelAndView.addObject("departments", departments);
        modelAndView.addObject("url","showall");
        modelAndView.addObject("urlPara","cpage");
        return modelAndView;
    }

    @RequestMapping(value="/employee/showEmployeePage", method = RequestMethod.POST)
    ModelAndView showDeptPage(Integer cpage){
        if (userFirst == false)
            return deptSearch(cpage, this.name,this.card_id,this.job_id,this.sex,this.phone,this.dept_id);
        ModelAndView modelAndView=new ModelAndView();
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从startrow行开始，查询pageSize条记录
        List<employee> rows = employeeService.selectusersPage(startrow, pageSize);
        int total = employeeService.getTotal();
        //根据页面属性生成页面对象
        Page<employee> page = new Page<employee>(total, pageSize, navigatePages, cpage, rows);
        //传递到前台页面
        modelAndView.setViewName("employee/employee::pageHtml");
        modelAndView.addObject("page", page);
        List<Job> jobs = employeeService.selectJob();
        List<department> departments = employeeService.selectDept();
        modelAndView.addObject("jobs", jobs);
        modelAndView.addObject("departments", departments);

        HashMap<Integer, String> jobMap = new HashMap<Integer, String>();
        for (Job job:jobs)
            jobMap.put(job.getJob_id(), job.getJob_name());
        modelAndView.addObject("jobMap",jobMap);

        HashMap<Integer, String> deptMap = new HashMap<Integer, String>();
        for (department dept:departments)
            jobMap.put(dept.getDepart_id(), dept.getDepart_name());
        modelAndView.addObject("deptMap",deptMap);

        modelAndView.addObject("url","showall");
        modelAndView.addObject("urlPara","cpage");
        return modelAndView;
    }

    @GetMapping("employee/showAddEmployee.html")
    String showAddEmployee(ModelMap map) {
        List<Job> jobs = employeeService.selectJob();
        List<department> departments = employeeService.selectDept();
        map.addAttribute("jobs", jobs);
        map.addAttribute("departments", departments);
        return "employee/showAddEmployee";
    }

    @GetMapping("employee/showUpdateEmployee.html")
    String showUpdateEmployee(ModelMap map1, Integer user_id) {
        List<Job> jobs = employeeService.selectJob();
        List<department> departments = employeeService.selectDept();
        map1.addAttribute("jobs", jobs);
        map1.addAttribute("departments", departments);
        employee employee1 = employeeService.selectById(user_id);
        Map<String, String > map = new HashMap<>();
        map.put("Jan", "01");
        map.put("Feb", "02");
        map.put("Mar", "03");
        map.put("Apr", "04");
        map.put("May", "05");
        map.put("Jun", "06");
        map.put("Jul", "07");
        map.put("Aug", "08");
        map.put("Sep", "09");
        map.put("Oct", "10");
        map.put("Nov", "11");
        map.put("Dec", "12");
        String str = employee1.getEmployee_bir().toString();
        String[] strs = str.split(" ");
        String newStr = strs[5] + "-" + map.get(strs[1]) + "-" + strs[2];
        map1.addAttribute("birthday", newStr);
        map1.addAttribute("employee", employee1);
        return "employee/showUpdateEmployee";
    }

    @RequestMapping(value="employee/employeeAdd", method = RequestMethod.POST)
    @ResponseBody
    String employeeAdd(ModelMap map, Integer depart_id, Integer job_id, String name, String crad_id, String address,
                      String post_code, String tel, String phone, String qq, String email, Integer sex, String party, String bir,
                      String race, String edu, String spe, String hobby, String remark){
        Integer user_id = employeeService.getUserIdByUserName(name);
        employeeService.addEmployee(user_id, depart_id, job_id, name, crad_id, address, post_code, tel, phone, qq, email,
                sex, party, bir, race, edu, spe, hobby, remark);
        return "success";
    }

    @RequestMapping(value="/employee/removeEmployee", method = RequestMethod.POST)
    @ResponseBody
    public String removeDept(String ids){
        String[] idss = ids.split(",");
        for (String strNumber:idss) {
            employeeService.deleteById(Integer.parseInt(strNumber));
        }
        return "success";
    }

    @RequestMapping(value="employee/employeeUpdate", method = RequestMethod.POST)
    @ResponseBody
    String employeeUpdate(ModelMap map, Integer depart_id, Integer job_id, String name, String crad_id, String address,
                      String post_code, String tel, String phone, String qq, String email, Integer sex, String party, String bir,
                      String race, String edu, String spe, String hobby, String remark, Integer user_id){
        employeeService.deleteById(user_id);
        employeeService.addEmployee(user_id, depart_id, job_id, name, crad_id, address, post_code, tel, phone, qq, email,
                sex, party, bir, race, edu, spe, hobby, remark);
        return "success";
    }
}
