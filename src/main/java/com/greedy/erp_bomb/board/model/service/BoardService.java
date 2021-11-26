package com.greedy.erp_bomb.board.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.board.model.dao.BoardDAO;
import com.greedy.erp_bomb.board.model.dto.BoardDTO;

@Service
public class BoardService {
	private BoardDAO boardDAO;
	
	@Autowired 
	public BoardService(BoardDAO boardDAO) { 
		this.boardDAO = boardDAO; 
	}
	
	/* 사내게시판 리스트 */ 
	@Transactional
	public List<BoardDTO> findBoardList()  { 
		return boardDAO.findBoardList();
	}
	
	/* 사내게시판 디테일 */ 
	@Transactional
	public BoardDTO selectBoardDetail(int no) {
		return boardDAO.selectBoardDetail(no);
	}

	

	/*공지사항 리스트 */ 
	@Transactional
	public List<BoardDTO> findNoticeList() {
		return boardDAO.findNoticeList();
	}

	/*공지사항 디테일 */
	@Transactional
	public BoardDTO selectNoticeDetail(int no) {
		return boardDAO.selectNoticeDetail(no);
	}
	@Transactional
	public void insertBoard(BoardDTO board) {
		boardDAO.insertBoard(board);
		
	}



	
}
