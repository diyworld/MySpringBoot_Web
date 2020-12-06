package com.springboot.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.member.MemberEntity;
import com.springboot.member.MemberMapper;
import com.springboot.order.OrderEntity;
import com.springboot.order.OrderMapper;

@Controller
public class MyBatisController {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private OrderMapper orderMapper;
	
	@ResponseBody
	@RequestMapping("/findMemberByName")
	/* http://localhost:8080/findMemberByName?memberName=member1 */
	public MemberEntity findMemberByName(String memberName) {
		return memberMapper.findByName(memberName);
	}
	
	@ResponseBody
	@RequestMapping("/insertMember")
	/* http://localhost:8080/insertMember?memberName=member2&date=2020-12-6 */
	public String insertMember(String memberName, Date date) {
		return memberMapper.insert(memberName, date) ? "success" : "fail";
	}
	
	@ResponseBody
	@RequestMapping("/findOrderByName")
	/* http://localhost:8080/findOrderByName?orderName=order1 */
	public OrderEntity findOrderByName(String orderName) {
		return orderMapper.findByName(orderName);
	}
	
	@ResponseBody
	@RequestMapping("/insertOrder")
	public String insertOrder(String orderName, Date date) {
		return orderMapper.insert(orderName, date) ? "success" : "fail";
	}
}
