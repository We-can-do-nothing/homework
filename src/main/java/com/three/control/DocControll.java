package com.three.control;

import com.three.bean.Document;
import com.three.common.Page;
import com.three.common.searchUtils;
import com.three.service.docServiceInterface;
import com.three.service.loginServiceInterface;
import com.three.service.userServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
public class DocControll {


    private static final Logger LOGGER = LoggerFactory.getLogger(DocControll.class);
//    private String filePath = "C:\\Users\\16834\\Desktop\\exerciseOneFiles\\homework-master\\homework-master\\src\\main\\resources\\uploadFiles\\";

    // 服务器路径
    private String filePath = "/root/uploadFiles/";
    searchUtils utils = new searchUtils();
    loginServiceInterface loginService;
    @Autowired
    docServiceInterface docService;
    @Autowired
    userServiceInterface userService;

    private boolean userFirst;
    private String title;


    @GetMapping(value = "document/document.html")    //展示文档信息界面
    public String showAllDoc(Model model, HttpServletRequest request){
        userFirst = true;
        title = "";
        //cpage当前页号
        int cpage = 1;
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从startrow行开始，查询pageSize条记录
        List<Document> rows = docService.selectusersPage(startrow, pageSize);
        int total = docService.getTotal();
        //根据页面属性生成页面对象
        Page<Document> page = new Page<Document>(total, pageSize, navigatePages, cpage, rows);

        Map<Integer,String> haspmap=new HashMap<Integer,String>();
        String userId = utils.searchCookie(request,"user_id");
        for(int i=0; i<rows.size(); i++){
            Integer user_id = rows.get(i).getDocument_user_id();
            String user_name = userService.findNameById(user_id);
            haspmap.put(user_id, user_name);
//            if(user_id.equals(userId)){
//                currentDoc.add(allDoc.get(i));
//            }
        }
        model.addAttribute("page", page);
        model.addAttribute("hashmap", haspmap);
        model.addAttribute("currentUserId", userId);
        return "document/document";
    }

    @RequestMapping(value="/doc/docSearch", method = RequestMethod.POST)
    ModelAndView docSearch(HttpServletRequest request, Integer cpage, String fileTitle){
        if (cpage == null)
            cpage = 1;
        userFirst = false;
        this.title = fileTitle;
        ModelAndView modelAndView=new ModelAndView();
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从0行开始，查询pageSize条记录
        List<Document> rows = docService.selectByTitle(fileTitle, startrow, pageSize);
        int total = docService.getTotalByTitle(fileTitle);
        //根据页面属性生成页面对象
        Page<Document> page = new Page<Document>(total, pageSize, navigatePages, cpage, rows);
        //传递到前台页面
        modelAndView.setViewName("document/document::pageHtml");

        Map<Integer,String> haspmap=new HashMap<Integer,String>();
        String userId = utils.searchCookie(request,"user_id");
        for(int i=0; i<rows.size(); i++){
            Integer user_id = rows.get(i).getDocument_user_id();
            String user_name = userService.findNameById(user_id);
            haspmap.put(user_id, user_name);
//            if(user_id.equals(userId)){
//                currentDoc.add(allDoc.get(i));
//            }
        }
        modelAndView.addObject("hashmap", haspmap);
        modelAndView.addObject("currentUserId", userId);

        modelAndView.addObject("page", page);
        modelAndView.addObject("url","showall");
        modelAndView.addObject("urlPara","cpage");
        return modelAndView;
    }

    @RequestMapping(value="/doc/showDocPage", method = RequestMethod.POST)
    ModelAndView showDocPage(HttpServletRequest request, Integer cpage){
        if (userFirst == false)
            return docSearch(request, cpage, this.title);
        ModelAndView modelAndView=new ModelAndView();
        int pageSize = 5;   //页面显示行数，自行设置
        int navigatePages = 3;//滑动窗口中格子个数，自行设置

        int startrow = (cpage - 1) * pageSize;
        //从startrow行开始，查询pageSize条记录
        List<Document> rows = docService.selectusersPage(startrow, pageSize);
        int total = docService.getTotal();
        //根据页面属性生成页面对象
        Page<Document> page = new Page<Document>(total, pageSize, navigatePages, cpage, rows);
        //传递到前台页面
        modelAndView.setViewName("document/document::pageHtml");

        Map<Integer,String> haspmap=new HashMap<Integer,String>();
        String userId = utils.searchCookie(request,"user_id");
        for(int i=0; i<rows.size(); i++){
            Integer user_id = rows.get(i).getDocument_user_id();
            String user_name = userService.findNameById(user_id);
            haspmap.put(user_id, user_name);
//            if(user_id.equals(userId)){
//                currentDoc.add(allDoc.get(i));
//            }
        }
        modelAndView.addObject("hashmap", haspmap);
        modelAndView.addObject("currentUserId", userId);

        modelAndView.addObject("page", page);
        modelAndView.addObject("url","showall");
        modelAndView.addObject("urlPara","cpage");
        return modelAndView;
    }

    @GetMapping(value = "document/showAddDocument.html")
    public String addDoc(ModelMap map){
        return "document/showAddDocument";
    }

    @PostMapping(value = "/upload")  //上传文档功能
    @ResponseBody
    public String upload(ModelMap map, HttpServletRequest request ,@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String title = request.getParameter("title");
        String detail = request.getParameter("detail");
        String user_name = utils.searchCookie(request,"LoginName");
        String userId_s = utils.searchCookie(request, "user_id");
        Integer userId = Integer.parseInt(userId_s);
        docService.insertDoc(title,fileName,detail,userId);
        Integer thisDocId = docService.getMaxDocId();
        System.out.println(thisDocId);
        File dest = new File(filePath + thisDocId.toString() + "__" + user_name + "__" +fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功！");
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
            return "上传失败！";
        }
        return "上传成功！";
    }

    @GetMapping(value = "document/showUpdateDocument.html")  //修改文档界面
    public String showUpdateDoc(ModelMap map, Integer document_id){
        Document updateDoc;
        updateDoc = docService.getDocByDocId(document_id);
        System.out.println(updateDoc);
        map.addAttribute("updateDoc", updateDoc);
        return "document/showUpdateDocument";
    }

    @PostMapping(value = "/update") //修改文档功能
    @ResponseBody
    public String update(ModelMap map, HttpServletRequest request, @RequestParam("file") MultipartFile file){
        String newTitle = request.getParameter("title");
        String newDetail = request.getParameter("detail");
        String docId_s = request.getParameter("DocId");
        String userId_s = request.getParameter("UserId");
        Integer docId = Integer.parseInt(docId_s);
        Integer userId = Integer.parseInt(userId_s);
        String newFilename = file.getOriginalFilename();
        String userName = userService.findNameById(userId);
        System.out.println(userName);
        System.out.println(docId);
        System.out.println(newTitle);
        System.out.println(newDetail);
        System.out.println(newFilename);
        if(newFilename.length() == 0){
            docService.updateDoc3(docId,newTitle,newDetail);
        }
        else {
            String oldFilename = docId_s + "__" + userName + "__" + docService.getDocByDocId(docId).getDocument_filename();
            LOGGER.info("旧文件：" + oldFilename);
            File deleteFile = new File(filePath+oldFilename);
            if(deleteFile.exists()){
                deleteFile.delete();
            }
            File savaFile = new File(filePath + docId_s + "__" + userName + "__" + newFilename);
            try {
                file.transferTo(savaFile);
                docService.updateDoc4(docId,newTitle,newDetail,newFilename);
                LOGGER.info("修改成功！");
            } catch (IOException e) {
                LOGGER.error(e.toString(), e);
                return "修改失败！（文件保存失败）";
            }

        }
        return "修改成功！";
    }

    @RequestMapping(value="/doc/removeDoc", method = RequestMethod.POST)
    @ResponseBody
    public String removeDoc(String ids){
        String[] idss = ids.split(",");
        for (String strNumber:idss) {
            docService.deleteById(Integer.parseInt(strNumber));
        }
        return "success";
    }

    @GetMapping("/document/download")
    @ResponseBody
    public String downloadFile(HttpServletRequest request, HttpServletResponse response, String DocId) {
        Integer userId = docService.getDocByDocId(Integer.parseInt(DocId)).getDocument_user_id();
        String userName = userService.findById(userId).getLoginname();
        System.out.println(docService.getDocByDocId(Integer.parseInt(DocId)).getDocument_filename());
        String searchFilename = DocId + "__" + userName + "__" + docService.getDocByDocId(Integer.parseInt(DocId)).getDocument_filename();
        File scFileDir = new File(filePath);
        File TrxFiles[] = scFileDir.listFiles();
        System.out.println(searchFilename);
        for(int index=0; index<TrxFiles.length; index++){
            if( searchFilename.equals(TrxFiles[index].getName()) ){
                System.out.println(TrxFiles[index].getName());
                //设置文件路径
//                File file = new File(filePath+"\\"+searchFilename);

//                服务器路径写法
                File file = new File(filePath+"/"+searchFilename);
                if (file.exists()) {
                    response.setContentType("application/force-download");// 设置强制下载不打开
                    response.addHeader("Content-Disposition", "attachment;fileName=" + docService.getDocByDocId(Integer.parseInt(DocId)).getDocument_filename());// 设置文件名
                    byte[] buffer = new byte[1024];
                    FileInputStream fis = null;
                    BufferedInputStream bis = null;
                    try {
                        fis = new FileInputStream(file);
                        bis = new BufferedInputStream(fis);
                        OutputStream os = response.getOutputStream();
                        int i = bis.read(buffer);
                        while (i != -1) {
                            os.write(buffer, 0, i);
                            i = bis.read(buffer);
                        }
                        return "下载成功";
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (bis != null) {
                            try {
                                bis.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if (fis != null) {
                            try {
                                fis.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return "文件已丢失！请联系管理员！";
    }


}
