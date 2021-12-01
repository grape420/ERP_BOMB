package com.greedy.erp_bomb.ea.contorller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.ea.model.dto.AddendumDTO;
import com.greedy.erp_bomb.ea.model.dto.EACarbonDTO;
import com.greedy.erp_bomb.ea.model.dto.EADTO;
import com.greedy.erp_bomb.ea.model.dto.EAPathDTO;
import com.greedy.erp_bomb.ea.model.service.EaService;
import com.greedy.erp_bomb.ea.vo.ApprovalerVO;
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
	
	@PostMapping("/regNewEa/{type}")
	public ModelAndView regEa(@AuthenticationPrincipal UserImpl user, ModelAndView mv, HttpServletRequest request, @PathVariable int type) {
		EADTO ea = new EADTO();
		List<EAPathDTO> eaPathList = new ArrayList<>();
		List<EACarbonDTO> eaCarbonList = new ArrayList<>();
		String[] approvaler = request.getParameterValues("approvaler");
		String[] carboner = request.getParameterValues("carboner");
		MemberDTO drafter = new MemberDTO();
		drafter.setName(user.getName());
		
		ea.setMember(drafter);
		ea.setTitle(request.getParameter("title"));
		ea.setContent(request.getParameter("content"));
		ea.setDate(new java.sql.Date(System.currentTimeMillis()));
		
		if(type == 1) {
			ea.setEaStatus(1);
		} else {
			ea.setEaStatus(4);
		}
		
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
			for(int i = 0 ; i < carboner.length ; i++) {
				EACarbonDTO eaCarbon = new EACarbonDTO();
				MemberDTO member = new MemberDTO();
				member.setName(carboner[i]);
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
	
	@PostMapping("/updateEa/{type}")
	public ModelAndView updateEa(@AuthenticationPrincipal UserImpl user, ModelAndView mv, HttpServletRequest request, @PathVariable int type) {
		EADTO ea = new EADTO();
		List<EAPathDTO> eaPathList = new ArrayList<>();
		List<EACarbonDTO> eaCarbonList = new ArrayList<>();
		String[] approvaler = request.getParameterValues("approvaler");
		String[] carboner = request.getParameterValues("carboner");
		MemberDTO drafter = new MemberDTO();
		drafter.setName(user.getName());
		
		System.out.println(request.getParameter("eaNo"));
		
		ea.setSerialNo(Integer.valueOf(request.getParameter("eaNo")));
		ea.setMember(drafter);
		ea.setTitle(request.getParameter("title"));
		ea.setContent(request.getParameter("content"));
		ea.setDate(new java.sql.Date(System.currentTimeMillis()));
		
		if(type == 1) {
			ea.setEaStatus(1);
		} else {
			ea.setEaStatus(4);
		}
		
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
			for(int i = 0 ; i < carboner.length ; i++) {
				EACarbonDTO eaCarbon = new EACarbonDTO();
				MemberDTO member = new MemberDTO();
				member.setName(carboner[i]);
				eaCarbon.setEa(ea);
				eaCarbon.setStatus(1);
				eaCarbon.setMember(member);
				eaCarbonList.add(eaCarbon);
			}
		}
		
		ea.setEaApprovalPathList(eaPathList);
		ea.setEaCarbonList(eaCarbonList);
		
		System.out.println("ea : " + ea);
		
		eaService.updateEa(ea);
		
		mv.setViewName("redirect:/ea/ea");
		
		return mv;
	}
	
	@GetMapping("/approval")
	public ModelAndView approval(@AuthenticationPrincipal UserImpl user, ModelAndView mv, @RequestParam int no) {
		eaService.approval(user.getName(), no);
		mv.setViewName("redirect:/ea/ea");
		
		return mv;
	}
	
	@GetMapping("/return")
	public ModelAndView eaReturn(@AuthenticationPrincipal UserImpl user, ModelAndView mv, @RequestParam int no) {
		eaService.eaReturn(user.getName(), no);
		mv.setViewName("redirect:/ea/ea");
		
		return mv;
	}
	
	@GetMapping("/eaCancle")
	public ModelAndView eaCancle(@AuthenticationPrincipal UserImpl user, ModelAndView mv, @RequestParam int no, @RequestParam(defaultValue = "0") int type) {
		eaService.eaCancle(user.getName(), no, type);
		mv.setViewName("redirect:/ea/ea");
		return mv;
	}
	
	@GetMapping("/ea")
	public void ea(@AuthenticationPrincipal UserImpl user, Model model, @RequestParam(defaultValue = "1") int tab) {
		String userName = user.getName();
		
		List<MemberDTO> memberList = eaService.findMemberList();
		
		for(int i = 0 ; i < memberList.size() ; i++) {
			if(userName.equals(memberList.get(i).getName())) {
				memberList.remove(i);
			}
		}
		
		List<EADTO> myEaList = eaService.findMyEa(userName);
		List<EADTO> myEaPathList = eaService.findMyEaPathList(userName);
		List<EADTO> myEaCarbonList = eaService.findMyEaCarbonList(userName);
		
		myEaList = eaSort(myEaList);
		myEaPathList = eaSort(myEaPathList);
		myEaCarbonList = eaSort(myEaCarbonList);
		
		List<EADTO> allEaList = new ArrayList<>();
		allEaList.addAll(myEaList);
		allEaList.addAll(myEaPathList);
		allEaList.addAll(myEaCarbonList);
		
		
		/* 사용자가 볼 결재 버튼의 종류를 만들기 위한 알고리즘 */
		for(EADTO ea : allEaList) {
			ea.setSaveNo(4);
			
			/* 결재 중 상태면 아래에 알고리즘을 시작한다. */
			if(ea.getEaStatus() == 1) {
				
				/* 현재 로그인한 사용자가 기안자라면 */
				if(userName.equals(ea.getMember().getName())) {
					
					/* 자신이 올린 기안의 첫 결재자가 아직 미결재 상태면 */
					if(ea.getEaApprovalPathList().get(0).getStatus() == 4) {
						
						/* 결재 버튼이 나타나게 설정한다. */
						ea.setSaveNo(3);
					}
				} else {	// 현재 로그인한 사용자가 기안자가 아니라면 */
					for(EAPathDTO eaPath : ea.getEaApprovalPathList()) {
						
						/* 현재 로그인한 사람의 결재 순번을 조회 */
						if(userName.equals(eaPath.getMember().getName())) {
							/* 현재 로그인한 사용자가 이번에 결재를 처리 해야할 상태면 */
							if(eaPath.getStatus() == 4) {
								
								/* 결재 또는 반려 버튼이 보이게 한다. */
								ea.setSaveNo(1);
								break;
							
							/* 현재 로그인한 사용자가 마지막 결재 자 라면*/
							} else if(eaPath.getStatus() == 3) {
								
								/* 결재 취소 버튼이 보이게 한다. */
								ea.setSaveNo(2);
								break;
								
							/* 그 외에 상황에는 */
							} else {
								break;
							}
						}
					}
				}
			}
			
			if(ea.getEaStatus() == 4) {
				List<ApprovalerVO> approvalerList = new ArrayList<>();
				for(EAPathDTO eaPath : ea.getEaApprovalPathList()) {
					approvalerList.add(new ApprovalerVO(eaPath.getMember().getName(), 1));
				}
				
				for(MemberDTO member : memberList) {
					boolean flag = false;
					for(ApprovalerVO approvaler : approvalerList) {
						if(member.getName().equals(approvaler.getName())) {
							flag = true;
						}
					}
					if(!flag) {
						approvalerList.add(new ApprovalerVO(member.getName(), 0));
					}
				}
				
				List<ApprovalerVO> carbonerList = new ArrayList<>();
				for(EACarbonDTO eaCarbon : ea.getEaCarbonList()) {
					carbonerList.add(new ApprovalerVO(eaCarbon.getMember().getName(), 1));
				}
				
				for(MemberDTO member : memberList) {
					boolean flag = false;
					for(ApprovalerVO carboner : carbonerList) {
						if(member.getName().equals(carboner.getName())) {
							flag = true;
						}
					}
					if(!flag) {
						carbonerList.add(new ApprovalerVO(member.getName(), 0));
					}
				}
				
				ea.setApprovalerList(approvalerList);
				ea.setCarbonerList(carbonerList);
			}
		}
		
		Collections.sort(allEaList);
		
		System.out.println(tab);
		
		model.addAttribute("myEaList", myEaList);
		model.addAttribute("myEaPathList", myEaPathList);
		model.addAttribute("myEaCarbonList", myEaCarbonList);
		model.addAttribute("allEaList", allEaList);
		model.addAttribute("memberList", memberList);
		model.addAttribute("tab", tab);
		
	}
	
	@GetMapping(value = "/deleteAddendum", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public void deleteAddendum(@RequestParam int no, Model model) {
		eaService.deleteAddendum(no);
	}
	
	@GetMapping(value = "/replyAddendum", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public AddendumDTO replyAddendum(@RequestParam int no, @RequestParam String content, Principal principal, Model model) {
		AddendumDTO replyAd = new AddendumDTO();
		AddendumDTO refAd = new AddendumDTO();
		MemberDTO drafter = new MemberDTO();
		
		refAd.setNo(no);
		
		replyAd.setRefNo(refAd);
		replyAd.setContent(content);
		replyAd.setDate(new java.sql.Date(System.currentTimeMillis()));
		replyAd.setRequestYn("N");
		
		drafter.setName(((UserImpl)((Authentication)principal).getPrincipal()).getName());
		replyAd.setMember(drafter);
		
		replyAd = eaService.replyAddendum(replyAd);
		
		EADTO ea = new EADTO();
		MemberDTO eaMember = new MemberDTO();
		eaMember.setName(replyAd.getEa().getMember().getName());
		ea.setMember(eaMember);
		
		MemberDTO adMember = new MemberDTO();
		adMember.setName(replyAd.getMember().getName());
		
		replyAd.setAddendumList(null);
		replyAd.setEa(ea);
		replyAd.setMember(adMember);
		
		refAd.setNo(replyAd.getRefNo().getNo());
		replyAd.setRefNo(refAd);
		
		return replyAd;
	}
	
	@GetMapping(value = "/addAddendum", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public AddendumDTO addADdendum(@RequestParam int no, @RequestParam String content, Principal principal, Model model) {
		AddendumDTO addAd = new AddendumDTO();
		EADTO ea = new EADTO();
		MemberDTO drafter = new MemberDTO();
		ea.setSerialNo(no);
		
		addAd.setRefNo(null);
		addAd.setEa(ea);
		addAd.setDepth(0);
		addAd.setContent(content);
		addAd.setDate(new java.sql.Date(System.currentTimeMillis()));
		addAd.setRequestYn("N");
		
		drafter.setName(((UserImpl)((Authentication)principal).getPrincipal()).getName());
		addAd.setMember(drafter);
		
		addAd = eaService.addAddendum(addAd);
		
		MemberDTO eaMember = new MemberDTO();
		eaMember.setName(addAd.getEa().getMember().getName());
		ea.setMember(eaMember);
		
		MemberDTO adMember = new MemberDTO();
		adMember.setName(addAd.getMember().getName());
		
		addAd.setAddendumList(null);
		addAd.setEa(ea);
		addAd.setMember(adMember);
		addAd.setRefNo(null);
		
		return addAd;
	}
	
	private List<EADTO> eaSort(List<EADTO> eaList) {
		for(EADTO ea : eaList) {
			Collections.sort(ea.getEaApprovalPathList());
			Collections.sort(ea.getAddendumList());
		}
		return eaList;
	}
	
}