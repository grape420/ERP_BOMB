package com.greedy.erp_bomb.inventory.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.inventory.model.dto.IceCreamDTO;
import com.greedy.erp_bomb.inventory.model.dto.InOutDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryPk;
import com.greedy.erp_bomb.inventory.model.service.InOutService;
import com.greedy.erp_bomb.member.model.dto.MemberDTO;
import com.greedy.erp_bomb.member.model.dto.UserImpl;

@Controller
@RequestMapping("/inOut")
public class InOutController {
	
	private InOutService inOutService;
	
	@Autowired
	public InOutController(InOutService inOutService) {
		this.inOutService = inOutService;
	}

	@GetMapping("/inOut")
	public ModelAndView findInOutList(ModelAndView mv) {
		List<InOutDTO> inOutList = inOutService.findInOutList();
		
		mv.addObject("inOutList", inOutList);
		mv.setViewName("inOut/inOut");
		
		return mv;
	}
	
	@GetMapping(value = "icecream", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<IceCreamDTO> findIcecreamList() {
		
		return inOutService.findIcecreamList();
	}
	
//	@GetMapping(value = "head", produces = "application/json; charset=UTF-8")
//	@ResponseBody
//	public List<InventoryDTO> findHeadList(Principal principal) {
////		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
////		
////		/* 재고 테이블 복합키 값 대입 */
////		InventoryPk pk = new InventoryPk();
////		pk.setIceCream(user.getCompany().getInventoryList().get);
////		pk.setCompany(user.getCompany().getSerialNo());
////		
////		/* 재고 테이블에 복합키로 DB 갔다와서 조회 */
////		InventoryDTO inven = inOutService.findInven(pk);
////		
////		return inOutService.findHeadList();
//	}
	
	
	@PostMapping("/regist")
	public ModelAndView registInOut(ModelAndView mv, @RequestParam int icecreamCode,
			                        @RequestParam int division, @RequestParam int amount, Principal principal) {
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		System.out.println("user : " + user);
		System.out.println("로그인한 사용자 이름 : " + user.getName());
		System.out.println("로그인한 사용자 회사 : " + user.getCompany());
		System.out.println("xxx : " + user.getCompany().getInventoryList());
		
		/* 재고 테이블 복합키 값 대입 */
		InventoryPk pk = new InventoryPk();
		pk.setIceCream(icecreamCode);
		pk.setCompany(user.getCompany().getSerialNo());
		
		/* 재고 테이블에 복합키로 DB 갔다와서 조회 */
		InventoryDTO inven = inOutService.findInven(pk);
		
		System.out.println(inven);
		
		/* 입출고 테이블에 값 대입 */
		InOutDTO inOut = new InOutDTO();
		inOut.setInventory(inven);
		inOut.setDivision(division);
		inOut.setDate(new java.sql.Date(System.currentTimeMillis()));
		inOut.setAmount(amount);
		
		if (division == 1) {				// 입고
			inven.setInvenRemainStock(inven.getInvenRemainStock() + amount);
		} else if (division == 2) {			// 출고
			inven.setInvenRemainStock(inven.getInvenRemainStock() - amount);
		} else {							// 판매
			inven.setInvenRemainStock(inven.getInvenRemainStock() - amount);
		}
		
		System.out.println(inOut);
		
		inOutService.updateInventory(inven);
		inOutService.registInOut(inOut);
		
		mv.setViewName("redirect:/inOut/inOut");
		
		return mv;
	}
	
}
