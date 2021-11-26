package com.greedy.erp_bomb.board.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.board.model.dto.BoardDTO;
import com.greedy.erp_bomb.vote.model.dto.VoteDTO;

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
	
	/* 사내게시판 디테일 */ 
	public BoardDTO selectBoardDetail(int no) {
		   BoardDTO boardDetail = em.find(BoardDTO.class, no);
		   return boardDetail;
	}

	/* 공지사항 리스트 */ 
	public List<BoardDTO> findNoticeList() {
		   String jpql = "SELECT m FROM BoardDTO as m ORDER BY m.no ASC";
		   TypedQuery<BoardDTO> query = em.createQuery(jpql,BoardDTO.class); 
		   List<BoardDTO> noticeList = query.getResultList();
		   return noticeList;
	}

	/* 공지사항 디테일 */ 
	public BoardDTO selectNoticeDetail(int no) {
		   BoardDTO noticeDetail = em.find(BoardDTO.class, no);
		   return noticeDetail;
	}
	
	/* 공지사항 입력 */
	public void insertBoard(BoardDTO board) {
		em.persist(board);
	}


}
