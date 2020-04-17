package com.three.service;

import com.three.bean.Document;
import com.three.mapper.document.docProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface docServiceInterface {

    List<Document> getAllDoc();

    void deleteById(Integer id);

    List<Document> selectusersPage(Integer start, Integer row);

    int getTotal();

    Document getDocByDocId(Integer id);

    Integer getMaxDocId();

    List<Document> selectByTitle(String title,Integer start,Integer row);

    void insertDoc(String title, String filename,String detail, Integer userId);

    void updateDoc4(Integer documentId, String newTitle,String newRemark, String newFilename);

    void updateDoc3(Integer documentId, String newTitle, String newRemark);

    int getTotalByTitle(String title);
}
