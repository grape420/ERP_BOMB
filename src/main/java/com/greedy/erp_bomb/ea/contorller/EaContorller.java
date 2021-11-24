package com.greedy.erp_bomb.ea.contorller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.ea.model.dto.EACarbonDTO;
import com.greedy.erp_bomb.ea.model.dto.EADTO;
import com.greedy.erp_bomb.ea.model.dto.EAPathDTO;
import com.greedy.erp_bomb.ea.model.service.EaService;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;

@Controller
@RequestMapping("/ea")
public class EaContorller {
	
	private EaService eaService;
	
	@Autowired
	public EaContorller(EaService eaService) {
		this.eaService = eaService;
	}
	
	@PostMapping("/regNewEa")
	public ModelAndView regEa(Principal principal, ModelAndView mv, HttpServletRequest request) {
		EADTO ea = new EADTO();
		List<EAPathDTO> eaPathList = new ArrayList<>();
		List<EACarbonDTO> eaCarbonList = new ArrayList<>();
		String[] approvaler = request.getParameterValues("approvaler");
		String[] carboner = request.getParameterValues("carboner");
		MemberDTO drafter = new MemberDTO();
		drafter.setName(((UserImpl)((Authentication)principal).getPrincipal()).getName());
		
		ea.setMember(drafter);
		ea.setTitle(request.getParameter("title"));
		ea.setContent(request.getParameter("content"));
		ea.setDate(new java.sql.Date(System.currentTimeMillis()));
		ea.setCategory(1);
		ea.setSaveNo(1);
		
		for(int i = 0 ; i < approvaler.length ; i++) {
			EAPathDTO eaPath = new EAPathDTO();
			MemberDTO member = new MemberDTO();
			member.setName(approvaler[i]);
			eaPath.setEa(ea);
			eaPath.setNo(i);
			eaPath.setMember(member);
			if(i == 0) {
				eaPath.setStatus(4);
			} else {
				eaPath.setStatus(1);
			}
			eaPathList.add(eaPath);
		}
		
		if(carboner != null) {
			for(int i = 0 ; i < approvaler.length ; i++) {
				EACarbonDTO eaCarbon = new EACarbonDTO();
				MemberDTO member = new MemberDTO();
				member.setName(approvaler[i]);
				eaCarbon.setEa(ea);
				eaCarbon.setStatus(1);
				eaCarbon.setMember(member);
				eaCarbonList.add(eaCarbon);
			}
		}
		
		ea.setEaApprovalPathList(eaPathList);
		ea.setEaCarbonList(eaCarbonList);
		
		eaService.insertEa(ea);
		
		mv.setViewName("redirect:/ea/ea");
		
		return mv;
	}
	
	@GetMapping("/ea")
	public void ea(Principal principal, Model model) {
		String userName = ((UserImpl)((Authentication)principal).getPrincipal()).getName();
		
		List<MemberDTO> memberList = eaService.findMemberList();
		
		for(int i = 0 ; i < memberList.size() ; i++) {
			if(userName.equals(memberList.get(i).getName())) {
				memberList.remove(i);
			}
		}
		
		List<EADTO> myEaList = eaService.findMyEa(userName);
		List<EADTO> myEaPathList = eaService.findMyEaPathList(userName);
		List<EADTO> myEaCarbonList = eaService.findMyEaCarbonList(userName);
		
		for(EADTO ea : myEaList) {
			Collections.sort(ea.getEaApprovalPathList());
			for(EAPathDTO eaPath : ea.getEaApprovalPathList()) {
				System.out.println(eaPath.getMember().getRank().getName());
			}
		}
		
		
		model.addAttribute("myEaList", myEaList);
		model.addAttribute("myEaPathList", myEaPathList);
		model.addAttribute("myEaCarbonList", myEaCarbonList);
		model.addAttribute("memberList", memberList);
	}
	
}