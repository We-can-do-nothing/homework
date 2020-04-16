package com.three.service;

import com.three.bean.notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface noticeServiceInterface {

    List<notice> selectAll();

    void notcieAdd(String title, String content,Integer id);

    List<notice> selectusersPage(int start, int row);

    int getTotal();

    notice selectById(Integer id);

    void updateNotice(String title, String content, Integer id);

    void deleteById(Integer id);

    List<notice> selectByTC(String title, String content, Integer start, Integer row);

    int getTotalByTC(String title, String content);
}
