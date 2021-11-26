package com.greedy.erp_bomb.board.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.board.model.dto.BoardDTO;
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
	@GetMapping("list")
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
		System.out.println(boardDetail);
		mv.addObject("boardDetail", boardDetail);
		mv.setViewName("/board/boardDetail"); 
		return mv;
	}
	
	/* 사내게시판 등록 */ 
	@PostMapping("insertBoard")
	public ModelAndView insertBoard(ModelAndView mv, @RequestParam String title, @RequestParam String insertMember, 
			@RequestParam java.sql.Date endDate, @RequestParam String content, Principal principal) { 
		
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
		
		System.out.println(insertMember);
		
		boardService.insertBoard(board);
		
		mv.setViewName("/board/regBoard");
		return mv;
	}
	
	
	
	/* 공지사항 리스트 */
	@GetMapping("nolist")
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
}
