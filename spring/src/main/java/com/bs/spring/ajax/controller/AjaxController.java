package com.bs.spring.ajax.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ajax")
@Slf4j
public class AjaxController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/basicTest.do")
	public void basic(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Board b=Board.builder().boardTitle("냉무").boardContent("냉무").build();
		//log.debug("test 실행");
//		res.setContentType("text/csv;charset=utf-8");
//		res.getWriter().write("test");
		ObjectMapper mapper=new ObjectMapper();
		res.setContentType("application/json;charset=utf-8");
		res.getWriter().write(mapper.writeValueAsString(b)); //writeValueAsString() 메소드를 이용하면 객체를 json 방식으로 보낼 수 있다.
		
	}
	
	//리턴값에 반환할 객체를 선언
	//@ResponseBody 어노테이션을 선언하면 반환하는 객체를 컴포터를 통해서 Json으로 반환할 수 있게 한다.
	@GetMapping("/converter")
	@ResponseBody
	public Board convertTest() {
		return Board.builder().boardTitle("spring!").boardContent("conveter Test~").build();
	}
	
	@PostMapping("/idDuplicate")
	public @ResponseBody Member idDuplicate(@RequestParam Map param) {
		return memberService.selectMember(param);
	}
	
	@GetMapping("/basic2")
	public String basic2() {
		return "demo/demo";
	}
	
	@GetMapping("/selectMemberAll")
	@ResponseBody
	public List<Member> selectMemberAll(){
		return memberService.selectMemberAll();
	}
	
	@PostMapping("/insertData.do")
	@ResponseBody
	public Member insertData(@RequestBody Member m) { //ajax로 보낸 데이터 객체로 파싱되게 받음
		log.info("{}",m);
		return m;
	}
}
