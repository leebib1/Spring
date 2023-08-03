package com.bs.spring.jpa.model.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bs.spring.jpa.entity.WebMember;
import com.bs.spring.jpa.model.dao.WebDao;
@Service
public class WebServiceImpl implements WebService {
	
	private EntityManagerFactory factory;
	private WebDao dao;
	
	public WebServiceImpl(@Qualifier("web") EntityManagerFactory factory, WebDao dao) {
		this.factory=factory;
		this.dao=dao;
	}
	@Override
	public List<WebMember> selectMemberAll() {
		EntityManager em=factory.createEntityManager();
		List<WebMember> members=dao.selectMemberAll(em);
		em.close();
		return members;
	}
	@Override
	public List<WebMember> selectMemberByName(String name) {
		EntityManager em=factory.createEntityManager();
		List<WebMember> members=dao.selectMemberByName(em, name);
		em.close();
		return members;
	}
	
}
