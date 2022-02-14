package com.docmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor // 모든 필드를 파라미터로 하는 생성자 메소드를 생성
@RequestMapping("/admin/*")
@Log4j
public class AdminController {
	
	@GetMapping("/logon")
	public void adLoginForm() {
		
	}
	
	@PostMapping("/logon")
	public String adLoginOk() {
		
		
		return "redirect:/admin/main";
	}
	
	//관리자 로그인 후에 보여 줄 메뉴 페이지
	@GetMapping("/main")
	public void main() {
		
	}

}
