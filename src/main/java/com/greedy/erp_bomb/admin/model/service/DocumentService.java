package com.greedy.erp_bomb.admin.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.admin.model.dao.DocumentDAO;
import com.greedy.erp_bomb.ea.model.dto.DocumentFormDTO;

@Service
public class DocumentService {

	private DocumentDAO documentDAO;
	
	@Autowired
	public DocumentService(DocumentDAO documentDAO) {
		this.documentDAO = documentDAO;
	}

	@Transactional
	public List<DocumentFormDTO> findDocumentList() {
		return documentDAO.findDocumentList();
	}

	@Transactional
	public void registNewDocument(DocumentFormDTO document) {
		documentDAO.findNewDocument(document);
	}
}
