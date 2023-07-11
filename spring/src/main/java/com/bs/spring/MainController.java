package com.bs.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //spring에서 controller 역할을 하게 어노테이션 작성
//springbean으로 등록된다.
public class MainController {
	//@Controller로 등록된 클래스는 클라이언트가 요청한 서비스를 진행하는 메소드(매핑 메소드)를 갖게된다.
	//요청 주소(url)와 연결되는 메소드
	
	//@RequestMapping 어노테이션을 이용한다
	//Controller에 선언된 메소드는 일반적으로 view를 선택해서 출력시키기 위해 String 값을 반환하도록 설정한다.
	@RequestMapping("/")
	public String main() {
		//반환하는 값은 viewResolver bean이 받는다.
		//등록된 InternalResourceResolver Bean은 반환도니 문자열에 객체에 설정된 prefix, suffix를 붙여서 내부에서 view를 찾아서 실행한다.
		//RequestDispatcher(prefix+return값+suffix).forword
		return "index";
	}
}
