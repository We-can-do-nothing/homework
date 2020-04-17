package com.three.mapper.employee;

import com.three.bean.Job;
import com.three.bean.department;
import com.three.bean.employee;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface employeeMapperInterface {
    @Select("select * from Job order by Job_id")
    List<Job> selectJob();

    @Select("select * from department order by depart_id")
    List<department> selectDept();

    @Select("select user_id from User where UserName=#{name}")
    int getUserIdByUserName(String name);

    @Select("select * from employee order by user_id limit #{start},#{row}")
    List<employee> selectusersPage(Integer start, Integer row);

    @Select("select count(user_id) from employee")
    int getTotal();

    @Select("select * from employee where user_id = #{id}")
    employee selectById(Integer id);

    @Delete("delete from employee where user_id=#{id}")
    void deleteById(Integer id);

    @SelectProvider(type = employeeProvider.class, method = "selectByManyMessage")
    List<employee> selectByManyMessage(@Param("employeeName") String employeeName,
                                       @Param("employeeCardId") String employeeCardId,
                                       @Param("job_id") Integer job_id,
                                       @Param("sex") Integer sex,
                                       @Param("phone") String phone,
                                       @Param("dept_id") Integer dept_id,
                                       @Param("start") Integer start,
                                       @Param("row") Integer row);

    @SelectProvider(type = employeeProvider.class, method = "getTotalByManyMessage")
    int getTotalByManyMessage(@Param("employeeName") String employeeName,
                              @Param("employeeCardId") String employeeCardId,
                              @Param("job_id") Integer job_id,
                              @Param("sex") Integer sex,
                              @Param("phone") String phone,
                              @Param("dept_id") Integer dept_id);

    @Insert("insert into employee(user_id,employee_depart_id,employee_job_id,employee_name,employee_crad_id,employee_address,employee_post_code,employee_tel,employee_phone," +
            "employee_qq,employee_email,employee_sex,employee_party,employee_bir,employee_race,employee_edu,employee_spe,employee_hobby,employee_remark) " +
            " values(#{user_id}, #{depart_id}, #{job_id}, #{name}, #{crad_id}, #{address}, #{post_code},#{tel},#{phone}, #{qq}, #{email}, #{sex}, #{party}, #{bir}, #{race}, #{edu}, #{spe}, #{hobby}, #{remark})")
    void addEmployee(Integer user_id,Integer depart_id,Integer job_id,String name,String crad_id,String address,String post_code,
                     String tel,String phone,String qq,String email,Integer sex,String party,String bir,String race,String edu,String spe,String hobby,String remark);
}
