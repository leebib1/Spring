package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component //bean 등록
@Aspect //aspect 역할
public class AnnoLoggerAspect {
	//pointcut에 대한 설정
	@Pointcut("within(com.bs.spring.member..*)") //실행할 경로 작성 ->member 패키지 아래 전부
	//메소드에 어노테이션 사용해야하므로 메소드를 하나 필수로 만들어준다.
	public void loggerTest() {}
	
	//advisor에 대한 설정
	@Before("loggerTest()") //어노테이션 괄호 안에 타겟 지정
	public void loggerBefore(JoinPoint jp) {
		log.debug("------------- annotation AOP -------------");
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("-------------------------------------------");
	}
	
	@Pointcut("execution(* com.bs.spring.memo..*(..))")
	public void memoLogger() {	}
	
	@After("memoLogger()")
	public void afterLogger(JoinPoint jp) {
		log.debug("----------- annotation AOP after ----------");
		Signature sig=jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+" "+sig.getName());
		log.debug("-------------------------------------------");
	}
	
	//메소드 실행 전, 후로 특정 로직 실행할 때
	@Around("execution(* com.bs.spring..*DaoImpl.*(..))")
	public Object daoLogger(ProceedingJoinPoint join) throws Throwable {
		//전, 후 로직을 한번에 설정할 수 있게된다.
		//전, 후를 구분하는 구문은 ProceedingJoinPoint 클래스가 제공하는 proceed() 메소드를 이용한다.
		//proceed() 메소드가 호출된 다음 라인을 후처리로 그 전은 전처리로 나눈다.
		//proceed() 메소드의 반환형은 Object이다.
		//실행 시간 체크하기
		StopWatch stop=new StopWatch();
		stop.start();
		log.debug("----------- around Logger DAO -------------");
		log.debug("전처리 내용 구현");
		Signature sig=join.getSignature();
		String clasName=sig.getDeclaringType().getName()+sig.getClass();
		log.debug(clasName);
		join.getTarget();
		log.debug("-------------------------------------------");
		Object obj=join.proceed();
		stop.stop();
		log.debug("후처리 내용 구현");
		log.debug("실행시간 : "+stop.getTotalTimeMillis()+"ms");
		log.debug("-------------------------------------------");
		return obj;
	}
	
	@AfterThrowing(pointcut="loggerTest()",throwing="e")
	public void afterThrowingLogger(JoinPoint jp, Throwable e) {
		log.debug("Error!!!!!");
		Signature sig=jp.getSignature();
		log.debug("{}",sig.getDeclaringType()+" "+sig.getName());
		log.debug("{}",e.getMessage());
		StackTraceElement[] stacktrace=e.getStackTrace();
		for(StackTraceElement element:stacktrace) {
			log.debug("{}",element);
		}
	}
	
	
	
	
}
