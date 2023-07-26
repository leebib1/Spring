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

}
