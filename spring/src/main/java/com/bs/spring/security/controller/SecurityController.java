package com.bs.spring.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SecurityController {
	@GetMapping("/loginpage")
	public String loginPage() {
		return "member/loginpage";
	}
	
	@RequestMapping("/error.do")
	public String loginFail(Model model) {
		model.addAttribute("msg","로그인 실패");
		model.addAttribute("loc","/");
		return "common/msg";
	}
	
	@RequestMapping("/successLoing.do")
	public String loginSuccess() {
		//인증 성공 후 실행되는 메소드
		//인증한 사용자에 대한 정보를 확인
		//security가 관리하는 영역으로 인증한 정보를 가져올 수 있음
		Object o=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.info("{}",o);
		return "redirect:/";
	}
	
	@RequestMapping("/seculogout.do")
	public void logout() {
		
	}
}
