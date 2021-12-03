package com.greedy.erp_bomb.board.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.board.model.dto.BoardDTO;
import com.greedy.erp_bomb.board.model.dto.CommentDTO;
import com.greedy.erp_bomb.board.model.service.BoardService;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private BoardService boardService;
	
	@Autowired 
	public BoardController(BoardService boardService) { 
		this.boardService = boardService;
	}
	
	/* 사내게시판 리스트 */
	@GetMapping("boardList")
	public ModelAndView findBoardList(ModelAndView mv) { 
		List<BoardDTO> boardList = boardService.findBoardList();
		for(BoardDTO board : boardList) { 
			System.out.println(board);
		}
		mv.addObject("boardList", boardList);
		mv.setViewName("board/boardList"); 
		return mv;
	}
	
	/* 사내게시판 디테일 */
	@GetMapping(value = "detail")
	public ModelAndView boardDetail(ModelAndView mv, @RequestParam int no) { 
		BoardDTO boardDetail = boardService.selectBoardDetail(no);
		
		System.out.println("코멘트 사이즈 : " + boardDetail.getCommentList().size());
		
		for(CommentDTO comment : boardDetail.getCommentList()) {
			System.out.println(comment);
		}
		
		Collections.sort(boardDetail.getCommentList());
		
		mv.addObject("boardDetail", boardDetail);
		mv.setViewName("/board/boardDetail"); 
		return mv;
	}
	
	/* 사내게시판 대댓글 시작 */ 
	@GetMapping(value = "/replyComment", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public CommentDTO replyComment(@RequestParam int no, @RequestParam String content, Principal principal, Model model) {
		CommentDTO replyCm = new CommentDTO();
		CommentDTO refAd = new CommentDTO();
		MemberDTO drafter = new MemberDTO();
		
		refAd.setNo(no);
		
		replyCm.setRefNo(refAd);
		replyCm.setContent(content);
		replyCm.setDate(new java.sql.Date(System.currentTimeMillis()));
		replyCm.setStatus("N");
		
		drafter.setName(((UserImpl)((Authentication)principal).getPrincipal()).getName());
		replyCm.setMember(drafter);
		
		replyCm = boardService.replyComment(replyCm);
		
		BoardDTO ar = new BoardDTO();
		MemberDTO emMember = new MemberDTO();
		emMember.setName(replyCm.getBoard().getMember().getName());
		ar.setMember(emMember);
		
		MemberDTO adMember = new MemberDTO();
		adMember.setName(replyCm.getMember().getName());
		
		replyCm.setCommentList(null);
		replyCm.setBoard(ar);
		replyCm.setMember(adMember);
		
		refAd.setNo(replyCm.getRefNo().getNo());
		replyCm.setRefNo(refAd);
		
		return replyCm;
	}
	
	@GetMapping(value = "/addComment", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public CommentDTO addComment(@RequestParam int no, @RequestParam String content, Principal principal, Model model) {
		CommentDTO addAd = new CommentDTO();
		BoardDTO bt = new BoardDTO();
		MemberDTO drafter = new MemberDTO();
		bt.setNo(no);
		
		addAd.setRefNo(null);
		addAd.setBoard(bt);
		addAd.setDepth(0);
		addAd.setContent(content);
		addAd.setDate(new java.sql.Date(System.currentTimeMillis()));
		addAd.setStatus("N");
		
		drafter.setName(((UserImpl)((Authentication)principal).getPrincipal()).getName());
		addAd.setMember(drafter);
		
		addAd = boardService.addComment(addAd);
		
		MemberDTO eaMember = new MemberDTO();
		eaMember.setName(addAd.getBoard().getMember().getName());
		bt.setMember(eaMember);
		
		MemberDTO adMember = new MemberDTO();
		adMember.setName(addAd.getMember().getName());
		
		addAd.setCommentList(null);
		addAd.setBoard(bt);
		addAd.setMember(adMember);
		addAd.setRefNo(null);
		
		return addAd;
	}
	
	@GetMapping(value = "/deleteComment", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public void deleteAddendum(@RequestParam int no, Model model) {
		boardService.deleteComment(no);
	}
	
	/* 사내게시판 등록 */ 
	@GetMapping("regBoard")
	public void regBoard() { 
	}
	
	@PostMapping("insertBoard")
	public ModelAndView insertBoard(ModelAndView mv, @RequestParam String title, 
			 @RequestParam String content, Principal principal) { 
		
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		System.out.println(user.getName());
		
		MemberDTO member = new MemberDTO();
		member.setName(user.getName());
		
		BoardDTO board = new BoardDTO();
		board.setMember(member);
		board.setTitle(title);
		board.setRegDate(date);
		board.setContent(content);
		board.setCategory(2);
		
		boardService.insertBoard(board);
		
		mv.setViewName("redirect:/board/boardList");
		return mv;
	}
	
	/* 공지사항 리스트 */
	@GetMapping("noticeList")
	public ModelAndView findNoticeList(ModelAndView mv) {
		List<BoardDTO> noticeList = boardService.findNoticeList(); 
		for(BoardDTO board : noticeList) { 
		System.out.println(board);
		}
		mv.addObject("noticeList", noticeList);
		mv.setViewName("board/noticeList");
		return mv;
	}
	
	
	/* 공지사항 디테일 */ 
	@GetMapping(value = "nodetail") 
	public ModelAndView noticeDetail(ModelAndView mv, @RequestParam int no) { 
		BoardDTO noticeDetail = boardService.selectNoticeDetail(no); 
		System.out.println(noticeDetail);
		mv.addObject("noticeDetail", noticeDetail);
		mv.setViewName("/board/noticeDetail"); 
		return mv;
	}
	
	/* 공지사항 등록 */ 
	@GetMapping("regNotice")
	public void regNotice() { 
	}
	
	@PostMapping("insertNotice")
	public ModelAndView insertNotice(ModelAndView mv, @RequestParam String title, 
			 @RequestParam String content, Principal principal) { 
		java.sql.Date nodate = new java.sql.Date(System.currentTimeMillis());
		UserImpl us = (UserImpl)((Authentication)principal).getPrincipal();
		
		System.out.println(us.getName());
		
		MemberDTO member = new MemberDTO();
		member.setName(us.getName());
		
		BoardDTO notice = new BoardDTO();
		notice.setMember(member);
		notice.setTitle(title);
		notice.setRegDate(nodate);
		notice.setContent(content);
		notice.setCategory(1);
		
		boardService.insertNotice(notice);
		
		mv.setViewName("redirect:/board/noticeList");
		return mv;
	}
}
