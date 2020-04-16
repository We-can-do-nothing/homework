package com.three.control;

import com.three.service.jobServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JobControl {

    @Autowired
    jobServiceInterface jobService;

    @GetMapping("job/job.html")
    String job(ModelMap map){
        return "job/job";
    }

    @GetMapping("job/showAddJob.html")
    String showAddJob(ModelMap map){
        return "job/showAddJob";
    }

    @GetMapping("job/showUpdateJob.html")
    String showUpdateJob(ModelMap map){
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
