package com.greedy.erp_bomb.main.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.erp_bomb.board.model.dto.BoardDTO;
import com.greedy.erp_bomb.main.model.dao.MainDAO;

@Service
public class MainService {
	
	private MainDAO mainDAO;
	
	public MainService(MainDAO mainDAO) {
		this.mainDAO = mainDAO;
	}

	@Transactional
	public List<BoardDTO> selectMainPageBoardList() {
		return mainDAO.selectMainPageBoardList();
	}

	@Transactional
	public Integer myApprovalingDocumentCount(String userName) {
		return mainDAO.myApprovalingDocumentCount(userName);
	}

}
