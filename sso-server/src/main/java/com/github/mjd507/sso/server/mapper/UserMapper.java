package com.github.mjd507.sso.server.mapper;

import com.github.mjd507.sso.client.entity.SSOUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT id, name FROM user WHERE id = #{uid} ")
    SSOUser selectUserById(@Param("uid") int id);

    @Select("SELECT id, name FROM user WHERE name = #{name} and password = #{pwd} ")
    SSOUser selectUserByNameAndPwd(@Param("name") String name, @Param("pwd") String pwd);

}
