package com.greedy.erp_bomb.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.admin.model.service.DeptService;
import com.greedy.erp_bomb.inventory.model.dto.IceCreamDTO;
import com.greedy.erp_bomb.member.model.dto.DeptDTO;

@Controller
@RequestMapping("/admin")
public class DeptController {
	private DeptService deptService;
	
	@Autowired
	public DeptController(DeptService deptService) {
		this.deptService = deptService;
	}
	
	@GetMapping("/dept")
	public ModelAndView findDeptList(ModelAndView mv) {
		List<DeptDTO> deptList = deptService.findDeptList();
		
		mv.addObject("deptList", deptList);
		mv.setViewName("admin/dept");
		
		return mv;
	}
	
	@PostMapping("/newDept")
	public ModelAndView registNewDept(ModelAndView mv, @ModelAttribute DeptDTO dept) {
		deptService.registNewDept(dept);
		
		mv.setViewName("redirect:/admin/dept");
		
		return mv;
	}
	

}
