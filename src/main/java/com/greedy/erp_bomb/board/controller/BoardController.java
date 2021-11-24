package com.greedy.erp_bomb.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.board.model.dto.BoardDTO;
import com.greedy.erp_bomb.board.model.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private BoardService boardService;
	
	@Autowired 
	public BoardController(BoardService boardService) { 
		this.boardService = boardService;
	}
	
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
	
	@GetMapping("/{detail}")
	public ModelAndView findBoardByCode(ModelAndView mv, @PathVariable int detail) { 
		BoardDTO board = boardService.findBoardByCode(detail);
		
		System.out.println(board);
		
		mv.addObject("detail", board); 
		mv.setViewName("/detail/one");
		
		return mv;
	}
	
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

	
}
