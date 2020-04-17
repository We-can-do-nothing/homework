package com.three.mapper.document;

import com.three.bean.Document;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DocMapperInterface {

    @Select("select * from Document")
    List<Document> getAllDoc();

    @Delete("delete from Document where document_id=#{id}")
    void deleteById(Integer id);

    @Select("select * from Document order by document_id limit #{start},#{row}")
    List<Document> selectusersPage(Integer start, Integer row);

    @Select("select count(document_id) from Document")
    int getTotal();

    @Select("select * from Document where document_id=#{id}")
    Document getDocByDocId(@Param("id") Integer id);

    @Select("select max(document_id) from Document")
    Integer getMaxDocId();

    @SelectProvider(type = docProvider.class, method = "selectByTitle")
    List<Document> selectByTitle(@Param("title") String title,
                                 @Param("start") Integer start,
                                 @Param("row") Integer row);

    @Insert("insert into Document(document_title, document_filename, document_remark, document_user_id) values(#{document_title}, #{document_filename}, #{document_remark}, #{document_user_id})")
    void insertDoc(@Param("document_title") String title, @Param("document_filename") String filename,
                   @Param("document_remark") String detail, @Param("document_user_id") Integer userId
    );
    @Update("update Document set document_title=#{newTitle}, document_remark=#{newRemark}, document_filename=#{newFilename} where document_id = #{documentId}")
    void updateDoc4(@Param("documentId") Integer documentId, @Param("newTitle") String newTitle,
                    @Param("newRemark") String newRemark, @Param("newFilename") String newFilename);

    @Update("update Document set document_title=#{newTitle}, document_remark=#{newRemark} where document_id = #{documentId}")
    void updateDoc3(@Param("documentId") Integer documentId, @Param("newTitle") String newTitle,
                    @Param("newRemark") String newRemark);

    @SelectProvider(type = docProvider.class, method = "getTotalByTitle")
    int getTotalByTitle(@Param("title") String title);
}
