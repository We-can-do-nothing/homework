package com.three.service.impl;

import com.three.bean.notice;
import com.three.mapper.notice.noticeMapperInterface;
import com.three.service.noticeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class noticeService implements noticeServiceInterface {

    @Autowired
    noticeMapperInterface noticeMapper;

    @Override
    public List<notice> selectAll() {
        return noticeMapper.selectAll();
    }

    @Override
    public void notcieAdd(String title, String content, Integer id) {
        noticeMapper.notcieAdd(title, content, id);
    }

    @Override
    public List<notice> selectusersPage(int start, int row) {
        return noticeMapper.selectusersPage(start, row);
    }

    @Override
    public int getTotal() {
        return noticeMapper.getTotal();
    }

    @Override
    public notice selectById(Integer id) {
        return noticeMapper.selectById(id);
    }

    @Override
    public void updateNotice(String title, String content, Integer id) {
        noticeMapper.updateNotice(title, content, id);
    }

    @Override
    public void deleteById(Integer id) {
        noticeMapper.deleteById(id);
    }

    @Override
    public List<notice> selectByTC(String title, String content, Integer start, Integer row) {
        return noticeMapper.selectByTC(title, content, start, row);
    }

    @Override
    public int getTotalByTC(String title, String content) {
        return noticeMapper.getTotalByTC(title, content);
    }
}
