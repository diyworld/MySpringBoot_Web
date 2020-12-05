package com.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.springboot.UserEntity;

@Mapper
//@Repository
public interface UserMapper {
	@Select("SELECT * FROM springboot_tbl WHERE userName = #{name}")
	@Results({
	    @Result(column="userName", property="name", jdbcType=JdbcType.VARCHAR),
	    @Result(column="age", property="age", jdbcType=JdbcType.INTEGER)
	})
	UserEntity findByName(@Param("name") String name);
	
	@Insert("INSERT INTO springboot_tbl(userName,age) VALUES(#{name}, #{age})")
	boolean insert(@Param("name") String name, @Param("age") Integer age);
}
