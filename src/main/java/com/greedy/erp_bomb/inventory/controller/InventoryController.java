package com.greedy.erp_bomb.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.inventory.model.dto.CompanyDTO;
import com.greedy.erp_bomb.inventory.model.dto.IceCreamDTO;
import com.greedy.erp_bomb.inventory.model.dto.InventoryDTO;
import com.greedy.erp_bomb.inventory.model.service.InventoryService;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
	
	private InventoryService inventoryService;
	
	@Autowired
	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@GetMapping("/inventory")
	public ModelAndView findInvenList(ModelAndView mv) {
		List<InventoryDTO> invenList = inventoryService.findInvenList();
		
		mv.addObject("inventoryList", invenList);
		mv.setViewName("inventory/inventory");
		return mv;
	}
	
	@GetMapping(value = "/company", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<CompanyDTO> findCompanyList() {
		return inventoryService.findCompanyList();
	}
	
	@GetMapping(value = "/icecream", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<IceCreamDTO> findIcecreamList() {
		return inventoryService.findIcecreamList();
	}
	
	@PostMapping("/regist")
	public ModelAndView registInven(ModelAndView mv, @RequestParam int companyCode,  @RequestParam int icecreamCode,
			                        @RequestParam int invenRemainStock) {
		
		/* 회사 테이블 */
		CompanyDTO com = new CompanyDTO();
		com.setSerialNo(companyCode);
		
		/* 아이스크림 테이블 */
		IceCreamDTO ice = new IceCreamDTO();
		ice.setNo(icecreamCode);
		
		/* 재고 관리 테이블 */
		InventoryDTO inven = new InventoryDTO();
		inven.setCompany(com);
		inven.setIceCream(ice);
		inven.setInvenRemainStock(invenRemainStock);
		
		/* Service로 값 넘기기 */
		inventoryService.registInven(inven);
		
		mv.setViewName("redirect:/inventory/inventory");
		
		return mv;
	}
	
	@PostMapping("/newIce")
	public ModelAndView registNewIce(ModelAndView mv, @ModelAttribute IceCreamDTO ice) {
		inventoryService.registNewIce(ice);
		
		mv.setViewName("redirect:/inventory/inventory");
		
		return mv;
	}
	
	@PostMapping("/search")
	public String searchInven(Model model, @RequestParam String keyword) {
		List<InventoryDTO> inventoryList = inventoryService.searchInven(keyword);
		
		model.addAttribute("inventoryList", inventoryList);
		
		return "/inventory/inventory";
	}
	
}
