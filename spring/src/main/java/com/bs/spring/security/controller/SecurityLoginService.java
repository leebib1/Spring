package com.bs.spring.security.controller;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bs.spring.member.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;
//@Componet를 사용하면 servlet-context.xml을 읽으면서 작동하기 때문에 실행 시점이 달라진다.
//security-context.xml이 먼저 실행되고 application 폴더 내의 xml파일이 실행되기 때문에이다.
//config.xml에 등록한 bean들은 서로 공유 되지 않는다.
public class SecurityLoginService implements UserDetailsService{
	
	private MemberDao dao;
	private SqlSessionTemplate session;
	
	public SecurityLoginService(MemberDao dao, SqlSessionTemplate session) {
		this.dao=dao;
		this.session=session;
	}

	//UserDetailsService 인터페이스의 추상 메소드 loadUserByUsername()를 구현
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//parameter 중에서 username에 작성된 key값을 매개변수로 받아온다.
		Member loginMember=dao.selectMember(session, Map.of("userId",username));
		return loginMember;
	}

}
