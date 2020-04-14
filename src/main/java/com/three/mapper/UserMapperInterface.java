package com.three.mapper;

import com.three.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapperInterface {

    @Select("select * from User")
    List<User> queryAllUser();

    @Select("delete from User where user_id = #{id}")
    void deleteById(Integer id);

    @Update("update User set UserName = #{username},Status = #{status},Loginname = #{loginName} where user_id = #{id}")
    void updateUserById(String username, Integer status, String loginName, Integer id);

    @Select("select * from User order by user_id asc limit #{startrow},#{num}")
    List<User> selectusersPage(@Param("startrow") int startrow,@Param("num") int num);

    @Select("select count(user_id) from User")
    int getTotal();

    @Select("select * from User where Loginname = #{Loginname}")
    User findByName(@Param("Loginname") String Loginname);

    @Select("select * from User where user_id = #{user_id}")
    User findById(@Param("user_id") int user_id);

    @Update("update User set password = #{newPassword} where user_id = #{user_id}")
    void updatePasswordById( @Param("user_id") Integer user_id, @Param("newPassword") String newPassword);

    @Insert("insert into User(Loginname, password, Status, UserName) VALUES(#{Loginname}, #{password}, #{Status}, #{UserName})")
    void insertUser(@Param("Loginname") String Loginname, @Param("password") String password, @Param("Status") Integer Status,
                    @Param("UserName") String UserName);
}
