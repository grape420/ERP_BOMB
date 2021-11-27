package com.greedy.erp_bomb.note.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.erp_bomb.note.model.dao.NoteDAO;
import com.greedy.erp_bomb.note.model.dto.NoteDTO;

@Service
public class NoteService {
	
	@Autowired
	private NoteDAO noteDAO;
	
	@Autowired
	public NoteService(NoteDAO noteDAO) {
		this.noteDAO = noteDAO;
	}
	
	@Transactional
	public List<NoteDTO> findNoteList() {
		return noteDAO.findNoteList();
	}

	@Transactional
	public void save(NoteDTO nodeDto) {
		noteDAO.save(nodeDto);
	}
	
	@Transactional
	public List<HashMap<String, Object>> getSendNoteList(String name) {
		return noteDAO.getSendNoteList(name);
	}


	
}
