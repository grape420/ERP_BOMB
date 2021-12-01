package com.greedy.erp_bomb.tna.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class TNADAO {

	@PersistenceContext
	private EntityManager em;
}
