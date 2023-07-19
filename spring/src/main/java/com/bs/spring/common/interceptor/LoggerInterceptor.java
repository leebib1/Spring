package com.bs.spring.common.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.demo.controller.DemoController;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//반환형은 boolean으로 true 반환 시 매핑 메소드가 실행되고 false를 반환 시 매핑 메소드를 실행하지 않는다.
//		log.debug("----- interceptor preHandle 실행 ------");
//		log.debug(request.getRequestURI());
//		Map params=request.getParameterMap();
//		for(Object key:params.keySet()) {
//			System.out.println(key);
//		}
//		log.debug("---------------------------------------------");
		//response.sendRedirect(request.getContextPath());
		
		//handler 매개변수
		//실행되는 controller 클래스와 실행되는 메소드를 확인할 수 있다.
		HandlerMethod hm=(HandlerMethod)handler;
		//log.debug("{}",hm.getBean()); //Controller 클래스 정보 확인
		DemoController demo=(DemoController)hm.getBean();
		//demo.demo1(request, response); 다른 메소드 실행 또는 전환 가능해짐
		//log.debug("{}",hm.getMethod()); //실행할 Method 정보 확인
		Method m=hm.getMethod();
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
//		log.debug("---------- interceptor postHandle ------------");
//		log.debug("{}",modelAndView.getViewName());
//		Map modelData=modelAndView.getModel();
//		log.debug("{}",modelData);
//		log.debug("-----------------------------------------------");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
		throws Exception{
//		log.debug("------응답후 인터셉터 실행=------");
//		log.debug("요청 주소 {} ",request.getRequestURI());
//		log.debug("에러 메시지 {} ",ex!=null?ex.getMessage():"응답 성공!");
	}
	
}
