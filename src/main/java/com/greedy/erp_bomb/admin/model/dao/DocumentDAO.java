package com.greedy.erp_bomb.admin.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.ea.model.dto.DocumentFormDTO;

@Repository
public class DocumentDAO {

	@PersistenceContext
	private EntityManager em;

	public List<DocumentFormDTO> findDocumentList() {
		String jpql = "SELECT a FROM DocumentFormDTO a";
		
		List<DocumentFormDTO> documentList = em.createQuery(jpql, DocumentFormDTO.class).getResultList();
		
		return documentList;
	}

	public void findNewDocument(DocumentFormDTO document) {
		em.persist(document);
	}
}
