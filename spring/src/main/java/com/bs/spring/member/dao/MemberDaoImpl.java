package com.bs.spring.member.dao;

import java.util.Map;

import javax.websocket.Session;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.model.dto.Member;
@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public int insertMember(SqlSession session, Member m) {
		return session.insert("member.insertMember",m);
	}

	@Override
	public Member selectMember(SqlSession session,Map param) {
		return session.selectOne("member.selectMember",param);
	}

}
