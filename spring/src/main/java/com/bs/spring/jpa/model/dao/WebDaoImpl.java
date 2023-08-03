package com.bs.spring.jpa.model.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.entity.WebMember;

@Repository
public class WebDaoImpl implements WebDao {

	@Override
	public List<WebMember> selectMemberAll(EntityManager em) {
		WebMember member=em.find(WebMember.class,"admin");
		System.out.println(member);
		//jpa가 제공하는 메소드를 이용하면 PK값으로 한 개의 row를 가져올 수 있다.
		//테이블에 있는 전체 데이터를 조회하기 위해서 JPQL이라는 객체지향 SQL문을 이용한다. ->insert문은 없음
		//SELECT 별칭.필드명||별칭(=전체컬럼) FROM Entity명 별칭 [JOIN ->has a 관계에 있는 별칭.Entity필드명][FETCH]
		//[WHERE, GROUP BY, HAVING, ORDER BY]
		String sql="""
				SELECT m
				FROM WebMember m
				""";
		//em.createQuery(sql); //반환형은 Qeury클래스, TypeQuery<T> 두가지
		//Query : 조회 결과 타입이 지정되지 않았을 때 사용
		//TypeQuery<T> : select문 실행 결과 타입이 지정되어있는 경우 사용
		TypedQuery<WebMember> tquery=em.createQuery(sql,WebMember.class);
		Query query=em.createQuery(sql);
		//생성된 qeury로 데이터 가져오기
		//getResultList() : 리스트로 row를 가져옴
		//getSingleResult() : 한개의 row만 반환하는 쿼리문 -> 다중값을 가져온 경우 Exception 발생
		//getResultStream() : row들을 stream으로 반환
		
		//Query클래스에서 paging처리 하는 메소드 제공
		//setFirstResult() : 시작 인덱스 cPage
		//setMaxResults() : 조회할 갯수 numPerPage
		tquery.setFirstResult(0);
		tquery.setMaxResults(10);
		List<WebMember> result=tquery.getResultList();
		List result1=query.getResultList();
		
		result.forEach(System.out::println);
		System.out.println("====================================================================");
		result1.forEach(System.out::println);
		
		//컬럼 선택해서 조회 ->프로젝션
		sql="""
			SELECT m.userName, m.email, m.age FROM WebMember m	
			""";
		query=em.createQuery(sql); //Object[]로 생성해서 반환
		List<Object[]> result3=query.getResultList();
		result3.forEach(e->{
			System.out.println(e);
		});
		query.getResultStream().forEach(e->{
			Object[] oarr=(Object[])e;
			System.out.println(oarr[0]+" "+oarr[1]+" "+oarr[2]);
		});
		
		sql="""
				SELECT AVG(m.age), sum(m.age)
				FROM WebMember m
				""";
		query=em.createQuery(sql);
		result3=query.getResultList();
		Object[] gruop=(Object[])query.getSingleResult();
		System.out.println(Arrays.toString(gruop));
		return result;
	}

	@Override
	public List<WebMember> selectMemberByName(EntityManager em, String name) {
		//매개변수를 받아서 처리하는 JPQL
		String sql="""
				SELECT m FROM WebMember m
				WHERE m.userName LIKE '%'||:name||'%'
				""";
		TypedQuery<WebMember> tquery=em.createQuery(sql,WebMember.class);
		//setParameter() 메소드로 매개변수 셋팅
		tquery.setParameter("name", name);
		return tquery.getResultList();
	}
	
	

}
