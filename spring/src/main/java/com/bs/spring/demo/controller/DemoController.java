package com.bs.spring.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.demo.model.dto.Demo;
@Controller
public class DemoController {
	@RequestMapping("/demo/demo.do")
	public String demo() {
		return "demo/demo";
	}
	
	//메소드 선언
	//return값, parameter 알아보기
	//1. 반환형
	// 1-1. String : ViewResolver에 의해서 view 화면을 출력
	// 1-2. void : HttpServletResponse 객체로 직접 응답메시지를 작성할 때 사용한다.
	// 1-3. ModelAndView : 화면에 전달할 데이터와 view 내용을 동시에 저장하는 객체. Spring 제공
	// -> request와 생명 주기가 같음
	// 1-4. 클래스타입 : Json으로 데이터를 반환 시 restful하게 서버를 구성했을 때 많이 사용한다.
	//	->ResponesEntity<클래스타입>으로 많이 사용한다.
	
	//2. 매개변수
	// 2-1. HttpServletRequest : 서블릿처럼 사용 가능
	// 2-2. HttpServletResponse : 서블릿처럼 사용 가능
	// 2-3. HttpSession : 서블릿처럼 사용 가능. request.getSession() 없이 매개변수로 세션을 받아서 사용 가능
	// 2-4. java.util.Locale : 서버의 로케일 정보를 저장한 객체
	// 2-5. InputStream/Reader : 파일을 읽어올 때 사용하는 Stream
	// 2-6. OutputStream/Writer : 파일을 보낼 때 사용하는 stream
	// 2-7. 기본 자료형 변수 : 클라이언트가 보낸 parameter 데이터와 선언한 변수 이름이 동일하면 자동으로 매핑한다.
	// 	->선언 이름과 일치하지 않으면 @RequestParam 어노테이션을 이용해서 연결할 수 있다.
	//	@RequestParam은 Mapping, 기본 값 설정, 필수 여부 설정이 가능하다.
	// 2-8. 클래스변수 : Command라고 한다. 매개변수로 받은 데이터를 필드에 넣어서 객체를 전달한다.
	// ->매개변수명과 필드명이 같은 데이터를 대입해준다.
	// 2-9. java.util.Map : @RequestParam 어노테이션을 함께 사용한다. 매개변수를 map 방식으로 저장한다.
	// 2-10. @RequestHeader(name) 기본자료형 : header 정보를 받을 수 있다.
	// 2-11. @CookieValue(name) 기본자료형 : Cookie에 저장된 값을 받을 수 있다.
	// 2-12. Model : request와 비슷하게 데이터를 key:value 형식으로 저장할 수 있는 객체
	// 2-13. ModelAndView : model, view를 동시에 저장하는 객체
	
	//메소드 어노테이션
	//@ResponseBody : Rest방식으로 클래스를 json으로 전송할 때 선언한다.
	//@RequestBody : json방식으로 전송된 파라미터를 클래스(객체)로 받을 때 선언한다.
	//@GetMapping, @PostMapping, @DeleteMapping : rest방식으로 클래스를 구현할 때 자주 사용하는 어노테이션들...
	
	@RequestMapping("/demo/demo1.do")
	public void demo1(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println(req);
		System.out.println(res);
		String devName=req.getParameter("devName");
		int devAge=Integer.parseInt(req.getParameter("devAge"));
		String devGender=req.getParameter("devGender");
		String devEmail=req.getParameter("devEmail");
		String[] devLang=req.getParameterValues("devLang");
		System.out.println(devName+" "+devAge+" "+devGender+" "+devEmail);
		for(String l:devLang) {
			System.out.println(l);
		}
		
		Demo d=Demo.builder().devAge(devAge).devEmial(devEmail).devGender(devGender).devName(devName)
				.devLang(devLang).build();
		req.setAttribute("demo", d);
		req.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(req, res);
		
//		res.setContentType("text/html;charset=utf-8");
//		PrintWriter out=res.getWriter();
//		out.print("<h2>"+devName+" "+devAge+" "+devGender+" "+devEmail+"</h2>");
		
	}
}
