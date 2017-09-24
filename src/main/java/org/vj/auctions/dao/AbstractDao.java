package org.vj.auctions.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractDao {

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	protected JdbcTemplate getJdbcTemplate() {
//		return jdbcTemplate;
//	}
	
	@PersistenceContext
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
