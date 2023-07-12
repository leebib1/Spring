package com.bs.spring.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Department;
import com.bs.spring.beantest.Employee;

//클래스 방식으로 bean 등록해서 사용
//POJO 클래스를 configuration으로 사용할 수 있다.
//@Configuration 어노테이션을 이용한다.
@Configuration
//servlet-context.xml에 설정했던 것처럼 어노테이션으로도 기본 패키지를 지정할 수 있다.
@ComponentScan(basePackages="com.bs.spring",
	includeFilters= {@ComponentScan.Filter(type=FilterType.REGEX, pattern= {"com.bs.spring.include.*"})}, 
	//어노테이션 표시가 없어도 includeFilter에 등록하면 bean으로 등록할 수 있음.
	excludeFilters= {})
	//등록한 bean을 제외 시킬 때 사용한다.
//@Import() //다른 configuration을 가져와서 처리하는 것
public class BeanTestConfiguration {
	//springbeanconfiguration.xml과 동일한 기능을 한다
	//spring에서 사용할 bean을 @Bean 어노테이션을 이용해서 자바 코드로 등록할 수 있다. 
	@Bean
	@Order(1) //bean의 우선 순위를 지정할 수 있다.
	public Animal ani() {
		//따로 설정하지 않으면 메소드 명이 bean의 id가 된다.
		return Animal.builder().name("kiki").age(5).height(80).build();
	}
	
	@Bean
	@Qualifier("sol")
	public Employee getEmployee(@Qualifier("sal")Department d) {
		return Employee.builder().name("최솔").age(27).address("경기도 안양시").salary(200)
				.dept(d)
				.build();
	}
	
	@Bean
	public Department sal() {
		return Department.builder().deptCode(2L).deptTitle("영업부").deptLocation("서울").build();
	}
	
	@Bean
	public BasicDataSource getDataSource() {
		BasicDataSource source=new BasicDataSource();
		source.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		source.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		source.setUsername("spring");
		source.setPassword("spring");
		return source;
	}
	
//	@Bean
//	public Gson gson() {
//		return new Gson();
//	}
}
