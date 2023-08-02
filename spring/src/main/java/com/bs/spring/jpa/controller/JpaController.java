package com.bs.spring.jpa.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.jpa.model.service.JpaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/jpa")
@Slf4j
public class JpaController {
	private JpaService service;
	public JpaController(JpaService service) {
		this.service=service;
	}
	
	@GetMapping("/basictest.do")
	public String basicTest() {
		service.basicTest();
		return "redirect:/";
	}
	
	@GetMapping("/manytoone.do")
	public String manytoone() {
		service.manyToOne();
		return "redirect:/";
	}
	
	@GetMapping("/oneToOne.do")
	public String oneToOne() {
		service.insertStudent();
		return "redirect:/";
	}
	
	@GetMapping("/entitydelete.do")
	public String deleteStudent(long no) {
		service.deleteStudent(no);
		return "redirect:/";
	}
	
	@PostMapping("/updatestudent.do")
	public String updateStudent(@RequestParam Map<String,Object> param) {
		service.updateStudent(param);
		return "redirect:/";
	}
	
	@GetMapping("/insertClub.do")
	public String insertClub() {
		service.insertClub();
		return "redirect:/";
	}
}
