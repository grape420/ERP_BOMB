package com.greedy.erp_bomb.note.model.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
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

	public void save(NoteDTO noteDto) {		    
		em.persist(noteDto); // 쪽지 발송  
	}
	
	public void remove(NoteDTO noteDto) {	
		noteDto = em.find(NoteDTO.class, noteDto.getSerialNo()); // 영속성 컨테스트에 저장
		em.remove(noteDto); // 쪽지 삭제 
	}
	
	public List<HashMap<String, Object>> getSendNoteList(String name) {
		String jpql = "SELECT m FROM NoteDTO m WHERE m.sentMember.name = :name ORDER BY m.serialNo DESC";
		
		TypedQuery<NoteDTO> query = em.createQuery(jpql, NoteDTO.class);
		query.setParameter("name", name);
		
		List<NoteDTO> noteList = query.getResultList();
		
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sDate2 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		for(NoteDTO note : noteList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("serialNo", note.getSerialNo());
			map.put("sendMember", note.getSentMember().getName());
			map.put("receiveMember", note.getReceiveMember().getName());
			map.put("content", note.getContent());
			map.put("sendDate", sDate.format(note.getSendDate()));
			map.put("detailDate", sDate2.format(note.getSendDate()));
			map.put("status", note.getReception());
			resultList.add(map);
		}
		
		return resultList;
	}
	
	public List<HashMap<String, Object>> getSendNoteList(String name, int pageNumber) {
		String jpql = "SELECT m FROM NoteDTO m WHERE m.sentMember.name = :name ORDER BY m.serialNo DESC";
		
		TypedQuery<NoteDTO> query = em.createQuery(jpql, NoteDTO.class);
		int pageSize = 10; /* 페이지당 데이터 수 */
		query.setFirstResult((pageNumber-1) * pageSize); 
		query.setMaxResults(pageSize);
		query.setParameter("name", name);
		
		List<NoteDTO> noteList = query.getResultList();
		
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy/MM/dd");
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		for(NoteDTO note : noteList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("serialNo", note.getSerialNo());
			map.put("sendMember", note.getSentMember().getName());
			map.put("receiveMember", note.getReceiveMember().getName());
			map.put("content", note.getContent());
			map.put("sendDate", sDate.format(note.getSendDate()));
			map.put("status", note.getReception());
			resultList.add(map);
		}
		
		return resultList;
	}
	
	public int getSendTotalCount(String name) {
		String jpql = "SELECT m FROM NoteDTO m WHERE m.sentMember.name = :name";
		
		TypedQuery<NoteDTO> query = em.createQuery(jpql, NoteDTO.class);
		query.setParameter("name", name);
		
		List<NoteDTO> totalCount = query.getResultList();
		
		return totalCount.size();
	}
	
	public int getreceiveTotalCount(String name) {
		String jpql = "SELECT m FROM NoteDTO m WHERE m.receiveMember.name = :name";
		
		TypedQuery<NoteDTO> query = em.createQuery(jpql, NoteDTO.class);
		query.setParameter("name", name);
		
		List<NoteDTO> totalCount = query.getResultList();
		
		return totalCount.size();
	}
	
	public List<HashMap<String, Object>> getreceiveNoteList(String name) {
		String jpql = "SELECT m FROM NoteDTO m WHERE m.receiveMember.name = :name ORDER BY m.serialNo DESC";
		
		TypedQuery<NoteDTO> query = em.createQuery(jpql, NoteDTO.class);
		query.setParameter("name", name);
		
		List<NoteDTO> noteList = query.getResultList();
		
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sDate2 = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		for(NoteDTO note : noteList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("serialNo", note.getSerialNo());
			map.put("sendMember", note.getSentMember().getName());
			map.put("receiveMember", note.getReceiveMember().getName());
			map.put("content", note.getContent());
			map.put("sendDate", sDate.format(note.getSendDate()));
			map.put("detailDate", sDate2.format(note.getSendDate()));
			map.put("status", note.getReception());
			resultList.add(map);
		}
		
		return resultList;
	}
	
	public List<HashMap<String, Object>> getreceiveNoteList(String name, int pageNumber) {
		String jpql = "SELECT m FROM NoteDTO m WHERE m.receiveMember.name = :name ORDER BY m.serialNo DESC";
		
		TypedQuery<NoteDTO> query = em.createQuery(jpql, NoteDTO.class);
		int pageSize = 10; /* 페이지당 데이터 수 */
		query.setFirstResult((pageNumber-1) * pageSize); 
		query.setMaxResults(pageSize);
		query.setParameter("name", name);
		
		List<NoteDTO> noteList = query.getResultList();
		
		SimpleDateFormat sDate = new SimpleDateFormat("yyyy/MM/dd");
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		for(NoteDTO note : noteList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("serialNo", note.getSerialNo());
			map.put("sendMember", note.getSentMember().getName());
			map.put("receiveMember", note.getReceiveMember().getName());
			map.put("content", note.getContent());
			map.put("sendDate", sDate.format(note.getSendDate()));
			map.put("status", note.getReception());
			resultList.add(map);
		}
		
		return resultList;
	}

	public void updateNoteReception(String serialNo) {
		int pk = Integer.parseInt(serialNo);
		NoteDTO noteDto = em.find(NoteDTO.class, pk);
		noteDto.setReception("Y");
	}
	
}
