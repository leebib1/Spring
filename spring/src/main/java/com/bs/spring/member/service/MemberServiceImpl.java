package com.bs.spring.member.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao dao;
	@Autowired
	private SqlSession session;
	@Override
	public int insertMember(Member m) {
		return dao.insertMember(session, m);
	}
	@Override
	public Member selectMember(Map param) {
		return dao.selectMember(session,param);
	}
	
	
}
