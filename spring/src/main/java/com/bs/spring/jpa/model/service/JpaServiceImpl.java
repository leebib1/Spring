package com.bs.spring.jpa.model.service;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bs.spring.jpa.model.dao.JpaDao;

@Service
public class JpaServiceImpl implements JpaService {
	private EntityManagerFactory factory;
	private JpaDao dao;
	public JpaServiceImpl(@Qualifier("bstest") EntityManagerFactory factory, JpaDao dao) {
		this.factory=factory;
		this.dao=dao;
	}
	@Override
	public void basicTest() {
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin(); //트랜잭션 시작
		dao.basicTest(em);
		et.commit(); //트랜잭션 종료
		em.close();
	}
	@Override
	public void manyToOne() {
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
		dao.manyToOne(em);
		et.commit();
		//em.clear(); //영속성 컨텍스트 지우기
		dao.boardById(em, 1);
		em.close();
	}
	@Override
	public void insertStudent() {
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
		dao.insertStudent(em);
		et.commit();
		em.clear();
		dao.selectStudentId(em,1L);
		em.close();
	}
	@Override
	public void deleteStudent(long no) {
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
		dao.deleteStudent(em, no);
		et.commit();
		em.close();
	}
	@Override
	public void updateStudent(Map<String, Object> param) {
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
		dao.updateStudent(em, param);
		et.commit();
		em.close();
	}
	@Override
	public void insertClub() {
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
		dao.insertClub(em);
		et.commit();
		em.close();
	}
	
}
