package com.bs.spring.jpa.common;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfing {
	//jpa로 DB에 접속하기 위해서 EntityManager 객체 생성
	//Perssistence에 static메소드로 createEntityManagerFactory() 메소드를 이용해서 만든 EntityManagerFactory 클래스에서
	//createEntityManager() 메소드를 이용해서 객체를 가져온다.
	
	//생성할 객체를 bean으로 등록 ->기본적으로 싱글톤 패턴으로 관리
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("bstest");
		return factory;
	}
	
//	@Bean
//	public EntityManager entityManager() {
//		return entityManagerFactory().createEntityManager();
//	}
}
