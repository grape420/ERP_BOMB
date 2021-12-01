package com.greedy.erp_bomb.admin.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greedy.erp_bomb.admin.model.dao.RankDAO;
import com.greedy.erp_bomb.member.model.dto.RankDTO;

@Service
public class RankService {

	private RankDAO rankDAO;
	
	@Autowired
	public RankService(RankDAO rankDAO) {
		this.rankDAO = rankDAO;
	}

	@Transactional
	public List<RankDTO> findRankList() {
		return rankDAO.findRankList();
	}

	@Transactional
	public void registNewRank(RankDTO rank) {
		rankDAO.registNewRank(rank);
	}
}
