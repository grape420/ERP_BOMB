package com.greedy.erp_bomb.admin;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.inventory.model.dto.InOutDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;

@Controller
@RequestMapping("/admin")
public class AuthorityController {

//	@GetMapping("/authority")
//	public ModelAndView findAuthorityList(ModelAndView mv, Principal principal) {
////		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
////		
////		List<InOutDTO> inOutList = inOutService.findInOutList(user.getName());
//		
////		mv.addObject("inOutList", inOutList);
//		mv.setViewName("admin/authority");
//		
//		return mv;
//	}
	
	@GetMapping("/authority")
	public String authority() {
		return "admin/authority";
	}
}
