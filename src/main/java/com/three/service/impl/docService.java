package com.three.service.impl;

import com.three.bean.Document;
import com.three.mapper.document.DocMapperInterface;
import com.three.service.docServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class docService implements docServiceInterface {

    @Autowired
    DocMapperInterface docMapper;

    @Override
    public List<Document> getAllDoc() {
        return docMapper.getAllDoc();
    }

    @Override
    public void deleteById(Integer id) {
        docMapper.deleteById(id);
    }

    @Override
    public List<Document> selectusersPage(Integer start, Integer row) {
        return docMapper.selectusersPage(start, row);
    }

    @Override
    public int getTotal() {
        return docMapper.getTotal();
    }

    @Override
    public Document getDocByDocId(Integer id) {
        return docMapper.getDocByDocId(id);
    }

    @Override
    public Integer getMaxDocId() {
        return docMapper.getMaxDocId();
    }

    @Override
    public List<Document> selectByTitle(String title, Integer start, Integer row) {
        return docMapper.selectByTitle(title, start, row);
    }

    @Override
    public void insertDoc(String title, String filename, String detail, Integer userId) {
        docMapper.insertDoc(title, filename, detail, userId);
    }

    @Override
    public void updateDoc4(Integer documentId, String newTitle, String newRemark, String newFilename) {
        docMapper.updateDoc4(documentId, newTitle, newRemark, newFilename);
    }

    @Override
    public void updateDoc3(Integer documentId, String newTitle, String newRemark) {
        docMapper.updateDoc3(documentId, newTitle, newRemark);
    }

    @Override
    public int getTotalByTitle(String title) {
        return docMapper.getTotalByTitle(title);
    }
}
