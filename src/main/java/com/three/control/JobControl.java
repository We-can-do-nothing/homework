package com.three.control;

import com.three.bean.Job;
import com.three.bean.department;
import com.three.common.Page;
import com.three.service.jobServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class JobControl {

    @Autowired
    jobServiceInterface jobService;

    private boolean userFirst;
    private String JobName;

    @GetMapping("job/job.html")
    String job(ModelMap map){
        userFirst = true;
        JobName = "";
        //cpage当前页号
        int cpage = 1;
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从startrow行开始，查询pageSize条记录
        List<Job> rows = jobService.selectusersPage(startrow, pageSize);
        int total = jobService.getTotal();
        //根据页面属性生成页面对象
        Page<Job> page = new Page<Job>(total, pageSize, navigatePages, cpage, rows);
        //传递到前台页面
        map.addAttribute("page", page);
        map.addAttribute("url","showall");
        map.addAttribute("urlPara","cpage");
        return "job/job";
    }

    @RequestMapping(value="/job/jobSearch", method = RequestMethod.POST)
    ModelAndView deptSearch(Integer cpage, String JobName){
        if (cpage == null)
            cpage = 1;
        userFirst = false;
        this.JobName = JobName;
        ModelAndView modelAndView=new ModelAndView();
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从0行开始，查询pageSize条记录
        List<Job> rows = jobService.selectByJobName(JobName, startrow, pageSize);
        int total = jobService.getTotalByJobName(JobName);

        //根据页面属性生成页面对象
        Page<Job> page = new Page<Job>(total, pageSize, navigatePages, cpage, rows);
        //传递到前台页面
        modelAndView.setViewName("job/job::pageHtml");
        modelAndView.addObject("page", page);
        modelAndView.addObject("url","showall");
        modelAndView.addObject("urlPara","cpage");
        return modelAndView;
    }

    @RequestMapping(value="/job/showJobPage", method = RequestMethod.POST)
    ModelAndView showDeptPage(Integer cpage){
        if (userFirst == false)
            return deptSearch(cpage, this.JobName);
        ModelAndView modelAndView=new ModelAndView();
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从startrow行开始，查询pageSize条记录
        List<Job> rows = jobService.selectusersPage(startrow, pageSize);
        int total = jobService.getTotal();
        //根据页面属性生成页面对象
        Page<Job> page = new Page<Job>(total, pageSize, navigatePages, cpage, rows);
        //传递到前台页面
        modelAndView.setViewName("job/job::pageHtml");
        modelAndView.addObject("page", page);
        modelAndView.addObject("url","showall");
        modelAndView.addObject("urlPara","cpage");
        return modelAndView;
    }


    @RequestMapping(value="/job/updateJob", method = RequestMethod.POST)
    @ResponseBody
    String updateJob(Integer job_id, String job_name, String job_remark){
        jobService.updateJob(job_id, job_name, job_remark);
        return "success";
    }

    @RequestMapping(value = "/job/removeJob", method = RequestMethod.POST)
    @ResponseBody
    String remove(String ids){
        String[] idss = ids.split(",");
        for (String strNumber:idss) {
            jobService.deleteById(Integer.parseInt(strNumber));
        }
        return "success";
    }

    @GetMapping("job/showAddJob.html")
    String showAddJob(ModelMap map){
        return "job/showAddJob";
    }

    @GetMapping("job/showUpdateJob.html")
    String showUpdateJob(ModelMap map, Integer job_id){
        Job job = jobService.selectById(job_id);
        map.addAttribute("job", job);
        return "job/showUpdateJob";
    }

    @RequestMapping("job/jobAdd")
    @ResponseBody
    String deptAdd(ModelMap map, String jobName, String remark){
        System.out.println(jobName+":" + remark);
        jobService.addJob(jobName,remark);
        return "success";
    }
}
