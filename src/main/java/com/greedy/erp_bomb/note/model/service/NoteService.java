package com.greedy.erp_bomb.note.model.service;

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
	public void save(NoteDTO noteDto) {
		noteDAO.save(noteDto);
	}

	@Transactional
	public void remove(NoteDTO noteDto) {
		noteDAO.remove(noteDto);
	}
	
	@Transactional
	public int getSendTotalCount(String name) {
		return noteDAO.getSendTotalCount(name);
	}
	
	@Transactional
	public List<HashMap<String, Object>> getSendNoteList(String name) {
		return noteDAO.getSendNoteList(name);
	}
	
	@Transactional
	public List<HashMap<String, Object>> getSendNoteList(String name, int pageNumber) {
		return noteDAO.getSendNoteList(name, pageNumber);
	}
	
	@Transactional
	public int getreceiveTotalCount(String name) {
		return noteDAO.getreceiveTotalCount(name);
	}
	
	@Transactional
	public List<HashMap<String, Object>> getreceiveNoteList(String name) {
		return noteDAO.getreceiveNoteList(name);
	}
	
	@Transactional
	public List<HashMap<String, Object>> getreceiveNoteList(String name, int pageNumber) {
		return noteDAO.getreceiveNoteList(name, pageNumber);
	}
	
	@Transactional
	public void updateNoteReception(String serialNo) {
		noteDAO.updateNoteReception(serialNo);
	}
	
	
}
