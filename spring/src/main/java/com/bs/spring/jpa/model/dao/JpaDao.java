package com.bs.spring.jpa.model.dao;

import java.util.Map;

import javax.persistence.EntityManager;

public interface JpaDao {
	void basicTest(EntityManager em);

	void manyToOne(EntityManager em);
	
	void boardById(EntityManager em, long no);

	void insertStudent(EntityManager em);
	
	void selectStudentId(EntityManager em, long no);

	void deleteStudent(EntityManager em, long no);

	void updateStudent(EntityManager em, Map<String, Object> param);

	void insertClub(EntityManager em);
}
