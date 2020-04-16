package com.three.mapper.job;

import com.three.bean.Job;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface jobMapperInterface {

    @Insert("insert into Job(Job_name, Job_remark) values(#{name}, #{remark})")
    void addJob(String name, String remark);

    @Select("select count(Job_id) from Job")
    int getTotal();

    @Select("select * from Job order by Job_id limit #{start},#{row}")
    List<Job> selectusersPage(int start, int row);

    @Select("select * from Job where Job_id = #{id}")
    Job selectById(Integer id);

    @Update("update Job set Job_name=#{name}, Job_remark=#{remark} where Job_id = #{id}")
    void updateJob(Integer id,String name, String remark);

    @Delete("delete from Job where Job_id = #{id}")
    void deleteById(Integer id);

    @SelectProvider(type = JobProvider.class, method = "selectByJobName")
    List<Job> selectByJobName(@Param("JobName") String JobName,
                              @Param("start") Integer start,
                              @Param("row") Integer row);

    @SelectProvider(type = JobProvider.class, method = "getTotalByJobName")
    int getTotalByJobName(@Param("JobName") String JobName);
}
