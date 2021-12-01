package com.greedy.erp_bomb.main.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greedy.erp_bomb.board.model.dto.BoardDTO;
import com.greedy.erp_bomb.main.model.dao.MainDAO;
import com.greedy.erp_bomb.tna.model.dto.TNADTO;
import com.greedy.erp_bomb.tna.model.dto.TNAPk;
import com.greedy.erp_bomb.vote.model.dto.VoteDTO;

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
	public Long myApprovalingDocumentCount(String userName) {
		return mainDAO.myApprovalingDocumentCount(userName);
	}

	@Transactional
	public Long myApprovalingCount(String userName) {
		return mainDAO.myApprovalingCount(userName);
	}

	@Transactional
	public Long myCarbonCount(String userName) {
		return mainDAO.myCarbonCount(userName);
	}

	@Transactional
	public List<VoteDTO> selectAllVote() {
		return mainDAO.selectAllVote();
	}

	@Transactional
	public void regEntWork(TNADTO tna) {
		mainDAO.regEntWork(tna);
	}

	@Transactional
	public TNADTO findTodayWork(TNAPk tnaPk) {
		return mainDAO.findTodayWork(tnaPk);
	}

}
