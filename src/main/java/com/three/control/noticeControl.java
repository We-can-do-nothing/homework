package com.three.control;

import com.three.bean.department;
import com.three.bean.notice;
import com.three.common.Page;
import com.three.common.searchUtils;
import com.three.service.noticeServiceInterface;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class noticeControl {

    @Autowired
    noticeServiceInterface noticeService;

    searchUtils utils = new searchUtils();

    private boolean userFirst;
    private String title;
    private String content;

    @GetMapping("notice/notice.html")
    String notice(ModelMap map, HttpServletRequest request){
        userFirst = true;
        title = "";
        content = "";
        //cpage当前页号
        int cpage = 1;
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从startrow行开始，查询pageSize条记录
        List<notice> rows = noticeService.selectusersPage(startrow, pageSize);
        int total = noticeService.getTotal();
        //根据页面属性生成页面对象
        Page<notice> page = new Page<notice>(total, pageSize, navigatePages, cpage, rows);
        //传递到前台页面
        int id = Integer.parseInt(utils.searchCookie(request, "user_id"));
        map.addAttribute("page", page);
        map.addAttribute("user_id", id);
        map.addAttribute("url","showall");
        map.addAttribute("urlPara","cpage");
        return "notice/notice";
    }

    @RequestMapping(value="/notice/noticeSearch", method = RequestMethod.POST)
    ModelAndView deptSearch(Integer cpage, String title, String content){
        if (cpage == null)
            cpage = 1;
        userFirst = false;
        this.title = title;
        this.content = content;
        ModelAndView modelAndView=new ModelAndView();
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从0行开始，查询pageSize条记录
        List<notice> rows = noticeService.selectByTC(title,content, startrow, pageSize);
        int total = noticeService.getTotalByTC(title, content);
        //根据页面属性生成页面对象
        Page<notice> page = new Page<notice>(total, pageSize, navigatePages, cpage, rows);
        //传递到前台页面
        modelAndView.setViewName("notice/notice::pageHtml");
        modelAndView.addObject("page", page);
        modelAndView.addObject("url","showall");
        modelAndView.addObject("urlPara","cpage");
        return modelAndView;
    }

    @RequestMapping(value="/notice/showNoticePage", method = RequestMethod.POST)
    ModelAndView showDeptPage(Integer cpage){
        if (userFirst == false)
            return deptSearch(cpage, this.title, this.content);
        ModelAndView modelAndView=new ModelAndView();
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从startrow行开始，查询pageSize条记录
        List<notice> rows = noticeService.selectusersPage(startrow, pageSize);
        int total = noticeService.getTotal();
        //根据页面属性生成页面对象
        Page<notice> page = new Page<notice>(total, pageSize, navigatePages, cpage, rows);
        //传递到前台页面
        modelAndView.setViewName("notice/notice::pageHtml");
        modelAndView.addObject("page", page);
        modelAndView.addObject("url","showall");
        modelAndView.addObject("urlPara","cpage");
        return modelAndView;
    }


    @RequestMapping(value="/notice/showUpdateReset", method = RequestMethod.POST)
    @ResponseBody
    Object showUpdateReset(Integer notice_id){
        notice notice1 = noticeService.selectById(notice_id);
        HashMap<String, String> map = new HashMap<>();
        map.put("title", notice1.getNotice_title());
        map.put("content", notice1.getNotice_content());
        return JSONObject.valueToString(map);
    }

    @GetMapping("notice/previewNotice.html")
    String previewNotice(ModelMap map, Integer id){
        notice notice1 = noticeService.selectById(id);
        map.addAttribute("notice", notice1);
        return "notice/previewNotice";
    }

    @GetMapping("notice/showAddNotice.html")
    String showAddNotice(ModelMap map){
        return "notice/showAddNotice";
    }

    @GetMapping("notice/showUpdateNotice.html")
    String showUpdateNotice(ModelMap map, Integer notice_id){
        notice notice1 = noticeService.selectById(notice_id);
        map.addAttribute("notice", notice1);
        return "notice/showUpdateNotice";
    }

    @RequestMapping(value="notice/noticeAdd", method = RequestMethod.POST)
    @ResponseBody
    String noticeAdd(HttpServletRequest request, String title, String content){
        int id = Integer.parseInt(utils.searchCookie(request, "user_id"));
        noticeService.notcieAdd(title, content, id);
        return "success";
    }

    @RequestMapping(value="/notice/removeNotice", method = RequestMethod.POST)
    @ResponseBody
    public String removeNotice(String ids){
        String[] idss = ids.split(",");
        for (String strNumber:idss) {
            noticeService.deleteById(Integer.parseInt(strNumber));
        }
        return "success";
    }

    @RequestMapping(value="notice/updateNotice", method = RequestMethod.POST)
    @ResponseBody
    String deptUpdate(ModelMap map, String title, String content,Integer id){
        noticeService.updateNotice(title, content, id);
        return "success";
    }
}
