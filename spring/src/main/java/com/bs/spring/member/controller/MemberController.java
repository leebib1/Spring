package com.bs.spring.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

@Controller
@RequestMapping("/member") //공통되는 부분 작성해서 생략가능
@SessionAttributes({"loginMember"}) //Model에서 해당하는 key를 갖는 값은 Session에 등록해준다.
public class MemberController {
	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping("/enrollMember.do")
	public String enrollform() {
		return "member/enrollMember";
	}
	
	@RequestMapping(value="/insertMember.do", method=RequestMethod.POST)
	//@PostMapping("/member/insertMember.do")
	public String insertMember(Member m, Model model){
		//패스워드 암호화 처리
		String oriPwd=m.getPassword();
		System.out.println(oriPwd);
		String encodePwd=passwordEncoder.encode(oriPwd);
		System.out.println(encodePwd);
		m.setPassword(encodePwd);
		int result=service.insertMember(m);
		//System.out.println(result);
		String msg,loc;
		if(result>0) {
			msg="회원 가입 성공";
			loc="/";
		}else {
			msg="회원 가입 실패했습니다.";
			loc="/member/enrollMember.do";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("loc", loc);
		return "common/msg";
	}
	
	@PostMapping("/login.do")
	public String loginMember(@RequestParam Map param, Model model,HttpSession session) {
		Member m=service.selectMember(param);
		if(m!=null&&m.getPassword().equals(param.get("password"))) {
			//session.setAttribute("loginMember", m);
			//Model을 이용한 로그인 처리
			model.addAttribute("loginMember",m);
		}else {
			model.addAttribute("msg", "로그인 실패했습니다.");
			model.addAttribute("loc", "/");
			return "common/msg";
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logout.do")
	public String logout(SessionStatus status) {
		if(!status.isComplete()) { //session이 끝났는지 아닌지
			//SessionAttributes로 등록된 내용 삭제하기
			//SessionStatus 객체 사용한다.
			status.setComplete();
		}
		return "redirect:/";
	}
	
	@RequestMapping("/mypage.do")
	public String mypage() {
		return "member/mypage";
	}
}
