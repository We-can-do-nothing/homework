package com.three.mapper.notice;

import com.three.bean.notice;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface noticeMapperInterface {

    @Select("select * from notice")
    List<notice> selectAll();

    @Insert("insert into notice(notice_title, notice_content, notice_user_id) values(#{title},#{content},#{id})")
    void notcieAdd(String title, String content,Integer id);

    @Select("select * from notice order by notice_id limit #{start},#{row}")
    List<notice> selectusersPage(int start, int row);

    @Select("select count(depart_id) from department")
    int getTotal();

    @Select("select * from notice where notice_id=#{id}")
    notice selectById(Integer id);

    @Update("update notice set notice_title=#{title},notice_content=#{content} where notice_id = #{id}")
    void updateNotice(String title, String content, Integer id);

    @Delete("delete from notice where notice_id=#{id}")
    void deleteById(Integer id);

    @SelectProvider(type = noticeProvider.class, method = "selectByTC")
    List<notice> selectByTC(@Param("title") String title,
                            @Param("content") String content,
                            @Param("start") Integer start,
                            @Param("row") Integer row);


    @SelectProvider(type = noticeProvider.class, method = "getTotalByTC")
    int getTotalByTC(@Param("title") String title,
                     @Param("content") String content);
}
