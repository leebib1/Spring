package com.bs.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.beantest.Animal;

@Controller
//Spring Bean 등록함
public class HomeController {
	
	//springbean으로 등록된 객체는 필드에 가져와 사용할 수 있다.
	@Autowired
	private Animal a;
	
	@RequestMapping("/")
	public String home() {
		System.out.println(a);
		//ViewResolver가 알아서 실행함 ->servlet-context 에서 확인
		return "index";
	}
}
