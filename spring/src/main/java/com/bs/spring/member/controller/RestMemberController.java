package com.bs.spring.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

@RestController
//Controller 역할을 하면서 Rest 방식으로 데이터를 사용하는 클래스에 선언
//모든 메소드에 @ResponseBody 어노테이션을 선언하지 않아도 모든 메소드에 선언된 것처럼 사용할 수 있다.
@RequestMapping("/member")
public class RestMemberController {
	
	private MemberService service;
	
	public RestMemberController(MemberService service) {
		this.service=service;
	}
	
	@GetMapping
	public ResponseEntity<List<Member>> selectMemberAll(){
		//메소드명으로 행위가 구분되게 작성
		List<Member> members=service.selectMemberAll();
		//ResponseEntity 객체를 이용해서 응답
		ResponseEntity<List<Member>> response=ResponseEntity.ok(members);
//		=new ResponseEntity<List<Member>>(members, HttpStatus.BAD_REQUEST);
				
		return response;
	}
	
	@GetMapping("/{id}")
	public Member selectMemberById(@PathVariable("id") String id) {
		return service.selectMember(Map.of("userId",id));
	}
	
	@PostMapping
	public int insertMember(@RequestBody Member m) {
		return service.insertMember(m);
	}
	
//	@PutMapping
//	public int updateMember(@RequestBody Member m) {
//		return service.updatetMember(m);
//	}
//	
//	@DeleteMapping("/{id}")
//	public int deleteMember(@PathVariable("id") String id) {
//		return service.deleteMember(m);
//	}
	
	//responseEntity 객체를 이용해서 응답
}
