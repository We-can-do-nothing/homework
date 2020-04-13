package com.three.mapper;

import com.three.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapperInterface {

    @Select("select * from User where Loginname = #{Loginname}")
    User findByName(@Param("Loginname") String Loginname);
}
