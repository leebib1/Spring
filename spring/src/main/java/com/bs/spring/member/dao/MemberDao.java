package com.bs.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.member.model.dto.Member;

public interface MemberDao {
	int insertMember(SqlSession session,Member m);

	Member selectMember(SqlSession session,Map map);
	
	List<Member> selectMemberAll(SqlSession session);
}
