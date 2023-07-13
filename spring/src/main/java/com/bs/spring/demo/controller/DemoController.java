package com.bs.spring.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.model.dto.Demo;
import com.bs.spring.demo.service.DemoService;
@Controller
public class DemoController {
	@Autowired
	private DemoService service;
	
	
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
		
		Demo d=Demo.builder().devAge(devAge).devEmail(devEmail).devGender(devGender).devName(devName)
				.devLang(devLang).build();
		req.setAttribute("demo", d);
		req.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(req, res);
		
//		res.setContentType("text/html;charset=utf-8");
//		PrintWriter out=res.getWriter();
//		out.print("<h2>"+devName+" "+devAge+" "+devGender+" "+devEmail+"</h2>");
		
	}
	
	//1:1매칭하여 데이터 받기
		//매핑메소드의 매개변수에 파라미터로 전송되는 name과 동일한 이름의 변수를 선언
		//매개변수의 타입은 사용할 타입으로 설정 * 값이 변경이 가능해야함. 
		@RequestMapping("/demo/demo2.do")
		public String demo2(String name, int devAge,
				String gender,String devEmail,String[] devLang
				/*double weight*/
				, Model model) {
			System.out.println(name+devAge+gender+devEmail
					+Arrays.toString(devLang));
			//페이지에 생성한 데이터를 전송하려면.... request, session, servletcontext
			//Spring에서 데이터전송하는 객체를 제공함. -> Model
			//Model에 데이터저장하기 -> model.addAttribute("key",value);
			Demo d=Demo.builder()
					.devName(name)
					.devAge(devAge)
					.devGender(gender)
					.devEmail(devEmail)
					.devLang(devLang)
					.build();
			
			model.addAttribute("demo",d);
			
			
			
			return "demo/demoResult";
		}
		
		//파라미터데이터를 받을때 @RequestParam어노테이션을 이용해서
		//옵션을 설정할 수 있다.
		@RequestMapping("/demo/demo3.do")
		public String requestParamuse(
				@RequestParam(value="devName",defaultValue="아무개") String name, 
				@RequestParam(value="devAge",defaultValue = "10") int age, 
				@RequestParam(value="devGender") String gender, 
				@RequestParam(required = true ) String devEmail, 
				String[] devLang, 
				Model model) {
			
			System.out.println(name+age+gender+devEmail+Arrays.toString(devLang));
			
			Demo d=Demo.builder()
					.devName(name)
					.devAge(age)
					.devGender(gender)
					.devEmail(devEmail)
					.devLang(devLang)
					.build();
			
			model.addAttribute("demo",d);
			
			return "demo/demoResult";
		}
		
		// DTO/VO 객체로 직접 parameter값 받기
		// 매개변수로 전달된 parameter이름과 동일한 필드를 갖는 객체를 선언함.
		//* 주의할 점은 클래스타입 Date를 전달받을때는 java.sql.Date로 하자.
		@RequestMapping("/demo/demo4.do")
		public String commandMapping(Demo demo,Model m) {
			System.out.println(demo);
			m.addAttribute("demo",demo);
			return "demo/demoResult";
		}
		
		// Map으로 parameter데이터 받아오기
		//@RequestParam어노테이션 설정 Map
		@RequestMapping("/demo/demo5.do")
		 public String mapPapping(@RequestParam Map param,String[] devLang ,Model m) {
			 System.out.println(param);
			 param.put("devLang",devLang);
			 m.addAttribute("demo",param);
			 return "demo/demoResult";
		 }
		
		//추가데이터 받아오기
		//Cookie, Header, Session
		//Cookie : @CookieValue(value="key") String data
		//Header : @RequestHeader(value="해더이름") String header
		//Session : @SessionAttribute(value="세션key값") String id
		@RequestMapping("/demo/demo6.do")
		public String extraData(
				@CookieValue(value="testData",required = false,defaultValue = "rest-time") String data,
				@RequestHeader(value="User-agent") String userAgent,
				@SessionAttribute(value="sessionId") String sessionId,
				@RequestHeader(value="Referer") String referer
				) {
			System.out.println("쿠키 : "+data);
			System.out.println("해더 : "+userAgent);
			System.out.println("세션 : "+sessionId);
			System.out.println("이전페이지 : "+referer);
			
			return "index";
		}
		
		//ModelAndView객체를 이용해서 반환하기
		
		@RequestMapping("/demo/demo7.do")
		public ModelAndView modelAndViewReturn(Demo d,ModelAndView mv) {
			//ModelAndView view설정과, Model설정은 같이 할 수 있는 객체
			//view : setViewName()메소드를 이용해서 저장
			//data : addObject("key",value)메소드이용해서 저장
			mv.addObject("demo",d);
			
			mv.setViewName("demo/demoResult");
			
			//mv.get
			
			return mv;
		}
		
		
		//자료형에 대해 반환하기 -> Data만 응답할때 사용 -> jackson라이브러리를 이용해서 처리
		//메소드선언부 @ResponseBody어노테이션 사용
		//Restful메소드를 구현했을때 사용
		@RequestMapping("/demo/demo8.do")
		@ResponseBody
		public String dataReturn() {
			return "유병승 최주영 조장흠 최솔 조윤진";
		}
		
//		public List<String> dataReturn(){
//			return List.of("유병승","최주영","조장흠","최솔","조윤진");
//		}
		
		
		//Request요청 메소드(GET, POST)를 필터링하기
		//@RequestMapping(value="url",method=RequestMethod.GET||RequestMethod.POST)
		
		@RequestMapping(value="/demo/demo9.do",method = RequestMethod.POST)
//		@PostMapping("/demo/demo9.do")
		//@GetMapping("/demo/demo9.do")
		public String methodCheck(Demo d) {
			System.out.println(d);
			//m.addAttribute("demo",d);
			return "demo/demoResult";
		}
		
		
		//간편하게 사용할 수 있게 Mapping어노테이션을 지원
//		@GetMapping
//		@PostMapping
//		@DeleteMapping
//		@PutMapping
		
		//mapping주소를 설정할때 {}를 사용할 수 있음
		// /board/boardview?no=1
		// /board/1 method=GET
		// /board method=GET
		@GetMapping("/demo/{no}")
		public String searchDemo(@PathVariable(value = "no") int no) {
			System.out.println(no);
			return "demo/demoResult";
		}
		
		
		
		@RequestMapping(value="/demo/insertDemo.do", 
				method=RequestMethod.POST)
		public String insertDemo(Demo demo, Model m) {
			
			int result=service.insertDemo(demo);
			System.out.println(result);
			m.addAttribute("msg",result>0?"저장성공":"저장실패");
			m.addAttribute("loc","/demo/demo.do");
			//sendRedirect로 변경하는 방법
			//prefix redirect:요청할 주소(매핑주소)
			//return "redirect:/";
			return "common/msg";
		}
		
		@RequestMapping("/demo/selectDemoAll.do")
		public String selectDemoAll(Model m) {
			List<Demo> demos=service.selectDemoAll();
			m.addAttribute("demos",demos);
			m.addAttribute("lang",List.of("1","2","3","4"));
			demos.forEach(System.out::println);
			return "demo/demoList";
		}
}
