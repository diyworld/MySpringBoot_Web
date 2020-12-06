package com.springboot.order;

import java.sql.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface OrderMapper {
	@Select("SELECT * FROM order_tbl WHERE order_name = #{name}")
	@Results({
	    @Result(column="order_id", property="id", jdbcType=JdbcType.INTEGER),
	    @Result(column="order_name", property="name", jdbcType=JdbcType.VARCHAR),
	    @Result(column="submission_date", property="date", jdbcType=JdbcType.DATE)
	})
	OrderEntity findByName(@Param("name") String name);

	@Insert("INSERT INTO order_tbl(order_name, submission_date) VALUES(#{name}, #{date})")
	boolean insert(@Param("name") String name, @Param("date") Date date);
}
