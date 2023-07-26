package com.bs.spring.jpa.model.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.entity.JpaMember;
@Repository
public class JpaDaoImpl implements JpaDao {

	@Override
	public void basicTest(EntityManager em) {
		//em메소드 이용
		//1. JpaMember클래스 영속성 등록
		JpaMember m=JpaMember.builder().memberId("첫번째 멤버").memberPwd("rhrh").age(26).height(172.2).build(); //비영속 객체
		//영속화 처리
		em.persist(m); //매개변수로 전달된 객체가 영속성 컨택스트에 저장되고 영속성 컨텍스트에 새로 저장된 객체면 insert문을 자동 생성한다. 실행X
		//insert문 실행 시점은 commit() 메소드 실행시
		JpaMember m1=JpaMember.builder().memberId("두번째 멤버").memberPwd("rhrh").age(26).height(172.2).build();
		em.persist(m1); 
		System.out.println(m);
		System.out.println(m1);
		
		//저장된 객체 불러오기
		JpaMember selectM=em.find(JpaMember.class, 1L);
		System.out.println(selectM);
	}

}
