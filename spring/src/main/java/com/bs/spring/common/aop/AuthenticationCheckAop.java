package com.bs.spring.common.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.bs.spring.common.exception.AuthenticationException;
import com.bs.spring.member.model.dto.Member;

@Component
@Aspect
public class AuthenticationCheckAop {

	@Before("execution(* com.bs.spring.memo..*(..))")
	public void checkCheck(JoinPoint jp) {
		//로그인 정보를 확인해서 아이디가 admin이면 접근 가능
		//스프링이 제공하는 RequestContextHolder 클래스를 이용한다.
		HttpSession session=(HttpSession)RequestContextHolder.currentRequestAttributes().resolveReference(RequestAttributes.REFERENCE_SESSION);
		Member loginMember=(Member)session.getAttribute("loginMember");
		if(loginMember==null||!loginMember.getUserId().equals("admin")) {
			throw new AuthenticationException("서비스 이용 권한이 부족합니다.");
		}
	}
}
