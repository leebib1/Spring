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
import com.bs.spring.common.exception.AuthenticationException;
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
	
	@GetMapping("/memberAll.do")
	@ResponseBody
	public List<Member> selectMemberAll() throws AuthenticationException{
		//if(1==1) throw new AuthenticationException("권한 에러 발생");
		return memberService.selectMemberAll();
	}
	
	@PostMapping("/insertData.do")
	@ResponseBody
	public Member insertData(@RequestBody Member m) { //ajax로 보낸 데이터 객체로 파싱되게 받음
		log.info("{}",m);
		return m;
	}
	
	//REST API, RESTFul
	//->session,cookie 관리를 하지 않는다. stateless가 핵심
	//웹에서 UI를 설계할 때 독립적으로 동작할 수 있도록 하는 것.
	//주소를 알아볼 수 있도록 구분하고 메소드에 따라서 구별하게끔 하는 것
	//url을 설정할 때 간편하게 서비스를 식별할 수 있게 구현
	//주소 설정 시 행위에 대한 표현을 제외. 행위에 대한 표현은 메소드에 작성하여 구분.
	//method
	//GET : 데이터 조회하는 서비스는 get으로 작성
	//POST : 데이터를 저장하는 서비스는 post로 작성
	//PUT : 데이터를 수정하는 서비스
	//DELETE : 데이터를 삭제하는 서비스
	//url 작성할 때는 명사로 작성한다.
	//ex)회원관리 서비스
	//	localhost:9090/spring/member
	// get으로 넘어오면 조회. member 명사가 들어왔으면 전체 회원 조회
	// get localhost:9090/spring/member/userid ->회원 중 해당 아이디를 조회
	// post로 넘어오면 저장. member 명사가 있으므로 회원 가입
	// put으로 넘어오면 수정. member 명사가 있으므로 회원 정보 수정
	// delete로 넘어오면 삭제. member 명사가 있으므로 회원 삭제
	//ex)특정 게시글에 댓글들을 가져올 때
	// localhost:9090/spring/board/boardNo/comment/commentNo
	
	
	
	
	
}
