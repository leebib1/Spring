package com.bs.spring.jpa.model.service;

import java.util.Map;

public interface JpaService {
	void basicTest();

	void manyToOne();

	void insertStudent();

	void deleteStudent(long no);

	void updateStudent(Map<String, Object> param);

	void insertClub();
}
