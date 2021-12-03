package com.greedy.erp_bomb.board.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.board.model.dao.BoardDAO;
import com.greedy.erp_bomb.board.model.dto.BoardDTO;
import com.greedy.erp_bomb.board.model.dto.CommentDTO;

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

	/* 사내게시판 입력 */ 
	@Transactional
	public void insertBoard(BoardDTO board) {
		boardDAO.insertBoard(board);
	}

	/* 공지사항 리스트 */ 
	@Transactional
	public List<BoardDTO> findNoticeList() {
		return boardDAO.findNoticeList();
	}

	/* 공지사항 디테일 */
	@Transactional
	public BoardDTO selectNoticeDetail(int no) {
		return boardDAO.selectNoticeDetail(no);
	}
	
	/* 공지사항 입력 */
	@Transactional
	public void insertNotice(BoardDTO notice) {
		boardDAO.insertNotice(notice);
	}

	/* 사내게시판 대댓글 */
	@Transactional
	public CommentDTO replyComment(CommentDTO replyCm) {
		System.out.println("여긴 보드서비스");
		return boardDAO.replyComment(replyCm);
	}
	
	@Transactional
	public CommentDTO addComment(CommentDTO addAd) {
		return boardDAO.addComment(addAd);
	}
	
	@Transactional
	public void deleteComment(int no) {
		boardDAO.deleteComment(no);
	}



	
}
