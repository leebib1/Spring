package com.bs.spring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //spring에서 controller 역할을 하게 어노테이션 작성
//springbean으로 등록된다.
public class MainController {
	//log 출력을 위해 Logger 가져오기
	private static final Logger logger=LoggerFactory.getLogger(MainController.class);
	
	
	//@Controller로 등록된 클래스는 클라이언트가 요청한 서비스를 진행하는 메소드(매핑 메소드)를 갖게된다.
	//요청 주소(url)와 연결되는 메소드
	
	//@RequestMapping 어노테이션을 이용한다
	//Controller에 선언된 메소드는 일반적으로 view를 선택해서 출력시키기 위해 String 값을 반환하도록 설정한다.
	@RequestMapping("/")
	public String main(HttpServletRequest req,HttpSession session,HttpServletResponse res) {
		//반환하는 값은 viewResolver bean이 받는다.
		//등록된 InternalResourceResolver Bean은 반환도니 문자열에 객체에 설정된 prefix, suffix를 붙여서 내부에서 view를 찾아서 실행한다.
		//RequestDispatcher(prefix+return값+suffix).forword
		//쿠키추가하기
		Cookie c = new Cookie("testData", "cookiedata");
		c.setMaxAge(60 * 60 * 24);
		res.addCookie(c);

		session.setAttribute("sessionId", "admin");
		
		//log4j를 이용해서 로그 출력하기
		//slf4j에서 제공하는 Logger 인터페이스를 구현한 클래스를 이용한다.
		//LoggerFactory 클래스의 static method getLogger(logger를 가져오는 클래스 지칭)를 이용한다.
		//로그를 출력할 때는 logger가 제공하는 메소드를 이용한다.
		//1. debug() : 개발 용도로 사용하는 로그를 출력할 때 사용한다.
		//2. info() : 프로그램 실행 정보를 출력할 때 사용한다.
		//3. warn() : 잘못된 사용에 대해서 출력할 때 사용한다.
		//4. error() : Exception이 발생하거나 불가능한 처리에 대해서 출력할 때 사용한다.
		//메소드의 매개변수로는 기본적으로 String만 가능하고 객체나 다른 데이터를 출력하려면 ("{}",출력할변수)식으로 작성한다.
		
		//로그 레벨
		//debug -> info -> warn -> error -> fatal
		//오른쪽으로 갈수록 위험한 순서로 하위 level을 선택하면 상위 레벨을 전부 출력해준다.
		//ex) info 선택시 info부터 fatal까지 출력하고 debug는 제외
		
		//로그 출력
//		logger.debug("debug 내용 출력하기");
//		logger.info("info 내용 출력하기");
//		logger.warn("warn 내용 출력하기");
//		logger.error("error 내용 출력하기");
		
		return "index";
	}
}
