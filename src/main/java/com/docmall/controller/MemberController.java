package com.docmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
@RequestMapping("/member/*")
@Controller
public class MemberController {

	// 주요기능 : 회원기능
	
	
	//회원가입 폼 : /member/join -> jsp파일명
	@GetMapping("/join")
	public void join() {
		
	}
	
	//회원가입저장
	@PostMapping("/join")
	public String joinOk() throws Exception{
		
		
		return "";
	}
	
	
	//아이디중복체크
	
	
	
	
	
	//회원수정 폼
	@GetMapping("/modify")
	public void modify() {
		
	}
	
	
	//회원수정저장
	
	
	
	//회원삭제
	
	
	//로그인
	@GetMapping("/login")
	public void login() {
		
	}
	
	
	//로그아웃
	
	
	//마이페이지
	@GetMapping("/mypage")
	public void mypage() {
		
	}
	
	
	//아이디및비밀번호 찾기
	@GetMapping("/searchIDPW")
	public void searchIDPW() {
		
	}
	
}
