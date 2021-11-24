package com.greedy.erp_bomb.board.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.board.model.dto.BoardDTO;

@Repository
public class BoardDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	public BoardDTO findBoardByCode(int detail) { 
		return em.find(BoardDTO.class, detail);
	}
	
	/* 사내게시판 리스트 */ 
	public List<BoardDTO> findBoardList() {
		String jpql = "SELECT m FROM BoardDTO as m ORDER BY m.no ASC";	
		
		TypedQuery<BoardDTO> query = em.createQuery(jpql,BoardDTO.class);
		List<BoardDTO> boardList = query.getResultList();
		return boardList;
	}
	
	/* 공지사항 리스트 */ 
	public List<BoardDTO> findNoticeList() {
		String jpql = "SELECT m FROM BoardDTO as m ORDER BY m.no ASC";
	
		TypedQuery<BoardDTO> query = em.createQuery(jpql,BoardDTO.class); 
		List<BoardDTO> noticeList = query.getResultList();
		return noticeList;
		}
	}
