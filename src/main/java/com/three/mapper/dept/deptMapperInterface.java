package com.three.mapper.dept;

import com.three.bean.department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface deptMapperInterface {

    @Select("select * from department")
    List<department> selectDeptAll();

    @Insert("insert into department(depart_name, depart_remark) values(#{deptName}, #{remark})")
    void AddDept(String deptName, String remark);

    @Select("select count(depart_id) from department")
    int getTotal();

    @SelectProvider(type = DeptProvider.class, method = "getTotalByDeptName")
    int getTotalByDeptName(@Param("deptName") String deptName);

    @Select("select * from department order by depart_id limit #{start},#{row}")
    List<department> selectusersPage(int start, int row);

    @Select("select * from department where depart_id = #{id}")
    department selectById(Integer id);

    @Update("update department set depart_name=#{name}, depart_remark=#{remark} where depart_id = #{id}")
    void updateDept(String name, String remark, Integer id);

    @Delete("delete from department where depart_id = #{id}")
    void deleteById(Integer id);

    @SelectProvider(type = DeptProvider.class, method = "selectByDeptName")
    List<department> selectByDeptName(@Param("deptName") String deptName,
                                      @Param("start") Integer start,
                                      @Param("row") Integer row);
}
