package com.bs.spring.jpa.model.dao;

import javax.persistence.EntityManager;

public interface JpaDao {
	void basicTest(EntityManager em);
}
