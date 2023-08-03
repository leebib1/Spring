package com.bs.spring.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.spring.jpa.entity.WebMember;
import com.bs.spring.jpa.model.service.WebService;

@RestController
@RequestMapping("/web")
public class WebJpaController {
	@Autowired
	private WebService service;
	
	@GetMapping("/members")
	public List<WebMember> selectMemberAll(){
		return service.selectMemberAll();
	}
	
	@GetMapping("/members/{name}")
	public List<WebMember> selectMemberByName(@PathVariable("name") String name){
		return service.selectMemberByName(name);
	}
}
