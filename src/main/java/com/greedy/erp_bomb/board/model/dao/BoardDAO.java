package com.greedy.erp_bomb.board.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.greedy.erp_bomb.board.model.dto.BoardDTO;
import com.greedy.erp_bomb.board.model.dto.CommentDTO;
import com.greedy.erp_bomb.ea.model.dto.EADTO;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;

@Repository
public class BoardDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	public BoardDTO findBoardByCode(int detail) { 
		return em.find(BoardDTO.class, detail);
	}
	
	/* 사내게시판 리스트 */ 
	public List<BoardDTO> findBoardList() {
		   String jpql = "SELECT m FROM BoardDTO as m WHERE m.category = 2 ORDER BY m.no ASC";	
		   TypedQuery<BoardDTO> query = em.createQuery(jpql,BoardDTO.class);
		   List<BoardDTO> boardList = query.getResultList();
		   return boardList;
	}
	
	/* 사내게시판 디테일 */ 
	public BoardDTO selectBoardDetail(int no) {
		   BoardDTO boardDetail = em.find(BoardDTO.class, no);
		   
		   /* 조회수 증가 */ 
		   int hit = boardDetail.getHit();
		   boardDetail.setHit(hit+1);
			
		   return boardDetail;
	}

	/* 사내게시판 입력 */
	public void insertBoard(BoardDTO board) {
		MemberDTO member = em.find(MemberDTO.class,board.getMember().getName());
		board.setMember(member);
		em.persist(board);
	}
	
	/* 공지사항 리스트 */ 
	public List<BoardDTO> findNoticeList() {
		   String jpql = "SELECT m FROM BoardDTO as m WHERE m.category = 1 ORDER BY m.no ASC";
		   TypedQuery<BoardDTO> query = em.createQuery(jpql,BoardDTO.class); 
		   List<BoardDTO> noticeList = query.getResultList();
		   return noticeList;
	}

	/* 공지사항 디테일 */ 
	public BoardDTO selectNoticeDetail(int no) {
		   BoardDTO noticeDetail = em.find(BoardDTO.class, no);
		   
		   /* 조회수 증가 */ 
		   int hit = noticeDetail.getHit();
		   noticeDetail.setHit(hit+1);
		   
		   return noticeDetail;
	}
	
	/* 공지사항 입력 */
	public void insertNotice(BoardDTO notice) {
		MemberDTO member = em.find(MemberDTO.class,notice.getMember().getName());
		notice.setMember(member);
		em.persist(notice);
	}

	/* 사내게시판 대댓글 */ 
	public CommentDTO replyComment(CommentDTO replyCm) {
		CommentDTO eaCm = em.find(CommentDTO.class, replyCm.getRefNo().getNo());
		
		replyCm.setRefNo(eaCm);
		replyCm.setBoard(eaCm.getBoard());
		replyCm.setMember(em.find(MemberDTO.class, replyCm.getMember().getName()));
		replyCm.setDepth(eaCm.getDepth() + 1);
		replyCm.setLength(eaCm.getLength() + 1);
		
		String jpql = "SELECT a FROM CommentDTO as a WHERE a.ea.serialNo = :no ORDER BY a.length";
		List<CommentDTO> adList = em.createQuery(jpql, CommentDTO.class).setParameter("no", eaCm.getBoard().getNo()).getResultList();
		
		replyCm.getBoard().getMember().getName();
		replyCm.getMember().getName();
		
		for(CommentDTO ad : adList) { 
			if(eaCm.getLength() < ad.getLength()) { 
				ad.setLength(ad.getLength() +1);
			}
		}
		em.persist(replyCm);
		return replyCm;
	}

	public CommentDTO addComment(CommentDTO addAd) {
		addAd.setBoard(em.find(BoardDTO.class, addAd.getNo()));
		addAd.setMember(em.find(MemberDTO.class, addAd.getMember().getName()));
		
		String jpql = "SELECT a FROM CommentDTO as a WHERE a.ea.serialNo = :no ORDER BY a.length";
		List<CommentDTO> adList = em.createQuery(jpql, CommentDTO.class).setParameter("no", addAd.getBoard().getNo()).getResultList();
		
		addAd.setLength(adList.size() + 1);
		
		addAd.getBoard().getMember().getName();
		addAd.getMember().getName();
		
		System.out.println(addAd);
		
		em.persist(addAd);
		return addAd;
	}

	public void deleteComment(int no) {
		CommentDTO ad = em.find(CommentDTO.class, no);
		ad.setCommentList(null);
		ad.setRefNo(null);
		ad.setMember(null);
		ad.setBoard(null);
		em.remove(ad);
	}
}
