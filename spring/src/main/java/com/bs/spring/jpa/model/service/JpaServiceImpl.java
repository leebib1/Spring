package com.bs.spring.jpa.model.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Service;

import com.bs.spring.jpa.model.dao.JpaDao;

@Service
public class JpaServiceImpl implements JpaService {
	private EntityManager em;
	private JpaDao dao;
	public JpaServiceImpl(EntityManager em, JpaDao dao) {
		this.em=em;
		this.dao=dao;
	}
	@Override
	public void basicTest() {
		EntityTransaction et=em.getTransaction();
		et.begin(); //트랜잭션 시작
		dao.basicTest(em);
		et.commit(); //트랜잭션 종료
	}
	@Override
	public void manyToOne() {
		EntityTransaction et=em.getTransaction();
		et.begin();
		dao.manyToOne(em);
		et.commit();
		//em.clear(); //영속성 컨텍스트 지우기
		dao.boardById(em, 1);
	}
	@Override
	public void insertStudent() {
		EntityTransaction et=em.getTransaction();
		et.begin();
		dao.insertStudent(em);
		et.commit();
		em.clear();
		dao.selectStudentId(em,1L);
	}
	@Override
	public void deleteStudent(long no) {
		EntityTransaction et=em.getTransaction();
		et.begin();
		dao.deleteStudent(em, no);
		et.commit();
	}
	
}
