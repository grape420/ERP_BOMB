package com.greedy.erp_bomb.note.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.erp_bomb.common.paging.Pagenation;
import com.greedy.erp_bomb.common.paging.SelectCriteria;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;
import com.greedy.erp_bomb.note.model.dto.NoteDTO;
import com.greedy.erp_bomb.note.model.service.NoteService;

@Controller
@RequestMapping("/note")
public class NoteContorller {
	
	private NoteService noteService;
	
	@SuppressWarnings("unused")
	private ObjectMapper objectMapper;
	
	
	@Autowired
	public NoteContorller(NoteService noteService, ObjectMapper objectMapper) {
		this.noteService = noteService;
		this.objectMapper = objectMapper;
	}
	
	@PostMapping(value = "sendMessage", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public HashMap<String, Object> sendMessage(@RequestParam HashMap<String, Object> map) {
		MemberDTO sendMemberDto = new MemberDTO();
		MemberDTO rcvMemberDto = new MemberDTO();
		NoteDTO noteDto = new NoteDTO();
		
		String send_memeber = map.get("send_member").toString(); // 유저 정보
		String receive_member = map.get("receive_member").toString(); // 받는 사람
		String content = map.get("message_contents").toString(); // 보낼 메세지
			
		sendMemberDto.setName(send_memeber);
		rcvMemberDto.setName(receive_member);
		
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
		resultMap.put("sendMember", send_memeber);
		
		return resultMap;
	}
	
	@PostMapping(value = "deleteMessage", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public HashMap<String, Object> deleteMessage(@RequestParam HashMap<String, Object> map, @RequestParam(value="serialNo[]") List<String> serialNo, 
			@RequestParam(value="send_member[]") List<String> send_member, @RequestParam(value="receive_member[]") List<String> receive_member) {
		String result = "";
		String userName = map.get("userName").toString(); // 유저정보
		
		/* 체크된 항목 Delete */
		for(int i=0; i<serialNo.size(); i++) {
			String no = serialNo.get(i);
			if(!no.isEmpty()) {
				MemberDTO sendMemberDto = new MemberDTO();
				MemberDTO rcvMemberDto = new MemberDTO();
				NoteDTO noteDto = new NoteDTO();
				
				String sendMember = send_member.get(i).toString();
				String receiveMember = receive_member.get(i).toString();
				
				sendMemberDto.setName(sendMember);
				rcvMemberDto.setName(receiveMember);
				
				noteDto.setSerialNo(Integer.parseInt(serialNo.get(i)));
				noteDto.setSentMember(sendMemberDto);
				noteDto.setReceiveMember(rcvMemberDto);
				
				try {
					noteService.remove(noteDto);
					result = "success";
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("error ==> " + e);
					result = e.getMessage();
				}
			}
		}
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("message", result);
		resultMap.put("sendMember", userName);
		
		return resultMap;
	}
	
	@PostMapping(value = "getSendMessage", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public HashMap<String, Object> getSendMessage(@RequestParam String name, @RequestParam int pageNumber) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int messageTotalCount = noteService.getSendTotalCount(name);
		/*** 3번쨰 변수 listSize ***/
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNumber, messageTotalCount, 10, 5);
		
		/* 보낸 쪽지 SELECT */
		List<HashMap<String, Object>> noteList = noteService.getSendNoteList(name, pageNumber);
		resultMap.put("list", noteList);
		resultMap.put("pagination", selectCriteria);
		return resultMap;
	}
	
	@PostMapping(value = "getreceiveMessage", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public HashMap<String, Object> getreceiveMessage(@RequestParam String name, @RequestParam int pageNumber) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int messageTotalCount = noteService.getreceiveTotalCount(name);
		/*** 3번쨰 변수 listSize ***/
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNumber, messageTotalCount, 10, 5);
		
		
		/* 받은 쪽지 SELECT */
		List<HashMap<String, Object>> noteList = noteService.getreceiveNoteList(name, pageNumber);
		resultMap.put("list", noteList);
		resultMap.put("pagination", selectCriteria);
		return resultMap;
	}
	
	@PostMapping(value = "getDetailMessage", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public HashMap<String, Object> getDetailMessage(@RequestParam String name, @RequestParam String serialNo, @RequestParam String type) {
		
		List<HashMap<String, Object>> noteList = null;
		/* 쪽지 리스트 SELECT */
		if(type.equals("send")) {
			noteList = noteService.getSendNoteList(name);
		}else {
			noteList = noteService.getreceiveNoteList(name);
			/* 쪽지 수신상태 UPDATE */
			noteService.updateNoteReception(serialNo);
		}
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		/* 쪽지 상세/이전/다음 SELECT */
		for(int i=0; i < noteList.size(); i++) {
			if( serialNo.equals( String.valueOf( noteList.get(i).get("serialNo") ) ) ) {
				HashMap<String, Object> noteDto = noteList.get(i);
				
				Object next = null;
				Object prev = null;
				if(i == 0) { // 첫 쪽지
					
					if(noteList.size() != 1) { // 쪽지가 한개이상 일 떄만 next
						next = noteList.get(i+1).get("serialNo");
					}
					prev = null;
					
				} else if(i == noteList.size()-1) { // 마지막 쪽지 
					next = null;
					prev = noteList.get(i-1).get("serialNo");
				} else {
					next = noteList.get(i+1).get("serialNo");
					prev = noteList.get(i-1).get("serialNo");
				}
				
				resultMap.put("serialNo", noteDto.get("serialNo"));
				resultMap.put("sendMember", noteDto.get("sendMember"));
				resultMap.put("receiveMember", noteDto.get("receiveMember"));
				resultMap.put("detailDate", noteDto.get("detailDate"));
				resultMap.put("content", noteDto.get("content"));
				resultMap.put("next", next);
				resultMap.put("prev", prev);
				break;
			}
		}
		
		return resultMap;
	}
	
	@PostMapping(value = "getReceiveMember", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<HashMap<String, Object>> getReceiveMember(@AuthenticationPrincipal UserImpl user) {
		
		List<HashMap<String, Object>> memberList = new ArrayList<HashMap<String,Object>>();
		
		List<MemberDTO> findAllMember = noteService.findAllMember();
		
		for(MemberDTO member : findAllMember) {
			if(!user.getName().equals(member.getName().toString())) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("name", member.getName());
				memberList.add(map);
			}
		}
		
		return memberList;
	}
}


