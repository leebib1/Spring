package com.bs.spring.common.aop;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerAspect {
	
	public void loggerBefore(JoinPoint jp) {
		log.debug("--------- AOP Logger Before ---------");
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		Object[] arg=jp.getArgs(); //메소드가 실행될 때 전달되는 매개변수의 인수값을 가져온다.
		if(arg!=null) {
			for(Object o:arg) {
				log.debug("{}",o);
			}
		}
		log.debug("-------------------------------------");
	}
	
	public void loggerAfter(JoinPoint jp) {
		log.debug("--------- AOP Logger After ---------");
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("-------------------------------------");
	}
}
