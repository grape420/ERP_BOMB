package com.greedy.erp_bomb.note.model.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.note.model.dto.NoteDTO;

@Repository
public class NoteDAO {

	@PersistenceContext
	private EntityManager em;

	public List<NoteDTO> findNoteList() {
		String jpql = "SELECT m FROM NoteDTO m ORDER BY m.serialNo";

		List<NoteDTO> noteList = em.createQuery(jpql, NoteDTO.class).getResultList();

		return noteList;
	}

	public void save(NoteDTO nodeDto) {	
	    em.persist(nodeDto); // 쪽지 발송
	}
	
	public List<HashMap<String, Object>> getSendNoteList(String name) {
		String jpql = "SELECT m FROM NoteDTO m WHERE m.sentMember.name = :name ORDER BY m.serialNo DESC";
		
		TypedQuery<NoteDTO> query = em.createQuery(jpql, NoteDTO.class);
		query.setParameter("name", name);
		
		List<NoteDTO> noteList = query.getResultList();
		
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy/MM/dd");
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		for(NoteDTO note : noteList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("sendMember", note.getSentMember().getName());
			map.put("receiveMember", note.getReceiveMember().getName());
			map.put("content", note.getContent());
			map.put("sendDate", sDate.format(note.getSendDate()));
			map.put("status", note.getReception());
			resultList.add(map);
		}
		
		return resultList;
	}

}
