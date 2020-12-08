package com.springboot.member.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.config.MemberConfig;
import com.springboot.member.mapper.MemberMapper;

@Service
/* @Transactional: 事务底层原理采用 aop技术增强, 即如果函数后续的处理逻辑不正确，则不会把数据填写到数据库 */
/* 统一使用全局事务管理器 */
@Transactional()
@EnableConfigurationProperties(MemberConfig.class)
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;

	/* http://localhost:8080/findMemberByName?memberName=member1 */
	public MemberEntity findMemberByName(String memberName) {
		return memberMapper.findByName(memberName);
	}

	/* http://localhost:8080/findMemberById?memberId=1 */
	public MemberEntity findMemberById(Integer memberId) {
		return memberMapper.findById(memberId);
	}
	
	/* http://localhost:8080/insertMember?memberName=member2&date=2020-12-6 */
	public String insertMember(String memberName, Date date) {
		return memberMapper.insert(memberName, date) ? "success" : "fail";
	}
}

