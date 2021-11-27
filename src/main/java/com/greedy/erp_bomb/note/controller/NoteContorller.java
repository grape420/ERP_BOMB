package com.greedy.erp_bomb.note.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.erp_bomb.member.model.dao.MemberDAO;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.service.MemberService;
import com.greedy.erp_bomb.note.model.dto.NoteDTO;
import com.greedy.erp_bomb.note.model.service.NoteService;

@Controller
@RequestMapping("/note")
public class NoteContorller {
	
	private NoteService noteService;
	private ObjectMapper objectMapper;
	private MemberDAO memberDao;
	
	@Autowired
	public NoteContorller(NoteService noteService, ObjectMapper objectMapper) {
		this.noteService = noteService;
		this.objectMapper = objectMapper;
	}
	
	@GetMapping("/note")
	public ModelAndView findNoteList(ModelAndView mv) {
		List<NoteDTO> noteList = noteService.findNoteList();
		
		mv.addObject("noteList", noteList);
		mv.setViewName("note/note");
		
		return mv;
	}
	
	@GetMapping(value = "sent", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<NoteDTO> findNoteList() {
		return noteService.findNoteList();
	}
	
	@PostMapping(value = "sendMessage", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public HashMap<String, Object> sendMessage(@RequestParam HashMap<String, Object> map) {
		MemberDTO rcvMemberDto = new MemberDTO();
		MemberDTO sendMemberDto = new MemberDTO();
		NoteDTO noteDto = new NoteDTO();
		
		String receive_member = map.get("receive_member").toString(); // 보낸 사람
		String content = map.get("message_contents").toString(); // 보낼 메세지
			
		rcvMemberDto.setName(receive_member);
		sendMemberDto.setName(receive_member); // 로그인하여 아이디 필요
		
		noteDto.setSentMember(sendMemberDto);
		noteDto.setReceiveMember(rcvMemberDto);
		noteDto.setContent(content);
		noteDto.setSendDate(new Date());
		noteDto.setReception("N");
		
		String result = "";
		try {
			noteService.save(noteDto);
			result = "success";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error ==> " + e);
			result = e.getMessage();
		}
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("message", result);
		resultMap.put("sendMember", sendMemberDto.getName());
		
		return resultMap;
	}
	
	@PostMapping(value = "getSendMessage", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<HashMap<String, Object>> getSendMessage(@RequestParam String name) {
		List<HashMap<String, Object>> noteList = noteService.getSendNoteList(name);
		return noteList;
	}
}


