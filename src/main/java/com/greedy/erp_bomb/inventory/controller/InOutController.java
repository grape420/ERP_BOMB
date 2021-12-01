package com.greedy.erp_bomb.inventory.controller;

import java.security.Principal;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greedy.erp_bomb.inventory.model.dto.InOutDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryPk;
import com.greedy.erp_bomb.inventory.model.service.InOutService;
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
	public ModelAndView findInOutList(ModelAndView mv, Principal principal) {
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		List<InOutDTO> inOutList = inOutService.findInOutList(user.getName());
		
		mv.addObject("inOutList", inOutList);
		mv.setViewName("inOut/inOut");
		
		return mv;
	}
	
	@GetMapping(value = "icecream", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<InventoryDTO> findIcecreamList(Principal principal) {
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		return inOutService.findIcecreamList(user.getName());
	}
	
	@PostMapping(value = "comparison", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public int findComparison(@RequestParam int icecreamCode,@RequestParam int division, @RequestParam int amount,
			                             Principal principal ) {
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		System.out.println("회사 코드 : " + user.getCompany().getSerialNo());
		System.out.println("아이스크림 코드 : " + icecreamCode);
		System.out.println("구분 : " + division);
		System.out.println("수량 : " + amount);
		
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
		
		if (division == 1) {											// 입고
			
			InventoryPk pk2 = new InventoryPk();
			pk2.setCompany(1);						// 본사 회사 일련 번호 = 1				
			pk2.setIceCream(icecreamCode);
			
			/* 본사에 재고가 있을 경우 입고 가능 */
			InventoryDTO headInven = inOutService.findHeadInven(pk2);
			if (headInven.getInvenRemainStock() - amount >= 0) {
				headInven.setInvenRemainStock(headInven.getInvenRemainStock() - amount);
				
				inven.setInvenRemainStock(inven.getInvenRemainStock() + amount);
			} else {
				return 1;
			}
			
		} else if (division == 2) {										// 출고
			if (inven.getInvenRemainStock() - amount >= 0) {
				inven.setInvenRemainStock(inven.getInvenRemainStock() - amount);
			} else {
				return 2;
			}
		} else {														// 판매
			if (inven.getInvenRemainStock() - amount >= 0) {
				inven.setInvenRemainStock(inven.getInvenRemainStock() - amount);
			} else {
				return 3;
			}
		}
		
		return 4;
		
	}
	
	@PostMapping("/regist")
	public ModelAndView registInOut(ModelAndView mv, @RequestParam int icecreamCode,
			                        @RequestParam int division, @RequestParam int amount, Principal principal,
			                        RedirectAttributes rttr) {
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		System.out.println("user : " + user);
		System.out.println("로그인한 사용자 이름 : " + user.getName());
		System.out.println("로그인한 사용자 회사 : " + user.getCompany());
		
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
		
		if (division == 1) {											// 입고
			
			InventoryPk pk2 = new InventoryPk();
			pk2.setCompany(1);						// 본사 회사 일련 번호 = 1				
			pk2.setIceCream(icecreamCode);
			
			/* 본사에 재고가 있을 경우 입고 가능 */
			InventoryDTO headInven = inOutService.findHeadInven(pk2);
			if (headInven.getInvenRemainStock() - amount >= 0) {
				headInven.setInvenRemainStock(headInven.getInvenRemainStock() - amount);
				
				/* 본사 재고 UPDATE */
				inOutService.updateHeadInven(headInven);
				
				inven.setInvenRemainStock(inven.getInvenRemainStock() + amount);
				
				/* 가맹점 입출고 INSERT */
				inOutService.registInOut(inOut);
			} 
			
		} else if (division == 2) {										// 출고
			if (inven.getInvenRemainStock() - amount >= 0) {
				inven.setInvenRemainStock(inven.getInvenRemainStock() - amount);
				
				/* 가맹점 입출고 INSERT */
				inOutService.registInOut(inOut);
			} 
		} else {														// 판매
			if (inven.getInvenRemainStock() - amount >= 0) {
				inven.setInvenRemainStock(inven.getInvenRemainStock() - amount);
				
				/* 가맹점 입출고 INSERT */
				inOutService.registInOut(inOut);
			}
		}
		
		System.out.println(inOut);
		
		inOutService.updateInventory(inven);
		
		mv.setViewName("redirect:/inOut/inOut");
		
		return mv;
	}
	
	@PostMapping("/search")
	public String searchInOut(Model model, @RequestParam String keyword, Principal principal) {
		UserImpl user = (UserImpl)((Authentication)principal).getPrincipal();
		
		List<InOutDTO> inOutList = inOutService.searchInOutList(keyword, user.getName());
		
		model.addAttribute("inOutList", inOutList);
		
		return "/inOut/inOut";
	}
	
}
