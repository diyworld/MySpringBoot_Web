package com.springboot.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.member.service.MemberEntity;
import com.springboot.member.service.MemberService;
import com.springboot.order.service.OrderEntity;
import com.springboot.order.service.OrderService;

@Controller
public class MyBatisController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private OrderService orderService;
	
	@ResponseBody
	@RequestMapping("/findMemberByName")
	public MemberEntity findMemberByName(String memberName) {
		return memberService.findMemberByName(memberName);
	}
	
	@ResponseBody
	@RequestMapping("/findMemberById")
	public MemberEntity findMemberById(Integer memberId) {
		return memberService.findMemberById(memberId);
	}
	
	@ResponseBody
	@RequestMapping("/insertMember")
	/* http://localhost:8080/insertMember?memberName=member2&date=2020-12-6 */
	public String insertMember(String memberName, Date date) {
		return memberService.insertMember(memberName, date);
	}
	
	@ResponseBody
	@RequestMapping("/findOrderByName")
	/* http://localhost:8080/findOrderByName?orderName=order1 */
	public OrderEntity findOrderByName(String orderName) {
		return orderService.findOrderByName(orderName);
	}
	
	@ResponseBody
	@RequestMapping("/findOrderById")
	public OrderEntity findOrderById(Integer orderId) {
		return orderService.findOrderById(orderId);
	}
	
	@ResponseBody
	@RequestMapping("/insertOrder")
	public String insertOrder(String orderName, Date date) {
		return orderService.insertOrder(orderName, date);
	}
}
