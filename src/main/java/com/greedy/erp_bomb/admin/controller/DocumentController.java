package com.greedy.erp_bomb.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.greedy.erp_bomb.admin.model.service.DocumentService;
import com.greedy.erp_bomb.ea.model.dto.DocumentFormDTO;
import com.greedy.erp_bomb.member.model.dto.DeptDTO;

@Controller
@RequestMapping("/admin")
public class DocumentController {
	
	private DocumentService documentService;
	
	public DocumentController(DocumentService documentService) {
		this.documentService = documentService;
	}
	
	@GetMapping("/document")
	public ModelAndView findDocumentList(ModelAndView mv) {
		List<DocumentFormDTO> documentList = documentService.findDocumentList();
		
		mv.addObject("documentList", documentList);
		mv.setViewName("admin/document");
		return mv;
	}
	
	@PostMapping("/newDocu")
	public ModelAndView registNewDocument(ModelAndView mv, @ModelAttribute DocumentFormDTO document) {
		documentService.registNewDocument(document);
		
		mv.setViewName("redirect:/admin/document");
		
		return mv;
	}
	

}
