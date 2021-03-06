package com.springboot.order.mapper;

import java.sql.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.springboot.order.service.OrderEntity;

@Mapper
//@Repository
public interface OrderMapper {
	/* 这里有个问题: 数据表order_tbl的主键是 id, 所以name可能有多个同名，这个情况下mysql返回的将是多个数据项，这时就会出错了 */
	/* 应该用唯一的id来进行索引，或者在mysql里把表项的名字设置为主键 */
	@Select("SELECT * FROM order_tbl WHERE order_name = #{name}")
	@Results({
		@Result(column="order_id", property="id", jdbcType=JdbcType.INTEGER),
	    @Result(column="order_name", property="name", jdbcType=JdbcType.VARCHAR),
	    @Result(column="submission_date", property="date", jdbcType=JdbcType.DATE)
	})
	OrderEntity findByName(@Param("name") String name);
	
	@Select("SELECT * FROM order_tbl WHERE order_id = #{id}")
	@Results({
		@Result(column="order_id", property="id", jdbcType=JdbcType.INTEGER),
	    @Result(column="order_name", property="name", jdbcType=JdbcType.VARCHAR),
	    @Result(column="submission_date", property="date", jdbcType=JdbcType.DATE)
	})
	OrderEntity findById(@Param("id") Integer id);
	
	@Insert("INSERT INTO order_tbl(order_name, submission_date) VALUES(#{name}, #{date})")
	boolean insert(@Param("name") String name, @Param("date") Date date);
}
