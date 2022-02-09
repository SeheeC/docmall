package com.docmall.controller;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.EmailDTO;
import com.docmall.domain.MemberVO;
import com.docmall.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
@RequestMapping("/member/*")
@Controller
public class MemberController {
	
	@Inject
	private PasswordEncoder cryptPassEnc;
	
	@Inject
	private MemberService service;
	// 주요기능 : 회원기능
	
	private JavaMailSender mailSender;
	// root-context org...JavaMailSenderImpl class의 mailSender 기능 사용
	
	//회원가입 폼 : /member/join -> jsp파일명
	@GetMapping("/join")
	public void join() {
		
	}
	
	//회원가입저장
	@PostMapping("/join")
	public String joinOk(MemberVO vo, RedirectAttributes rttr) throws Exception{
		
		// 비밀번호 암호화. (spring security 기능)
		// StringUtils.insEmpty(매개변수) : 매개 변수의 값이 null 또는 빈 문자열일 경우를 확인하는 기능.
		
		vo.setMbsp_password(cryptPassEnc.encode(vo.getMbsp_password()));
		
		vo.setMbsp_receive(!StringUtils.isEmpty(vo.getMbsp_receive()) ? "Y" : "N");
		log.info("MemberVO: vo");
		
		service.join(vo);

		return "redirect:/member/login";
	}
	
	
	//아이디중복체크
	@ResponseBody
	@GetMapping("/checkID")
	public ResponseEntity<String> checkID(@RequestParam String mbsp_id) throws Exception{
		// db 작업이 있으면 예외 처리를 해야 함
		
		String result = "";
		ResponseEntity<String> entity = null;
		
		result = StringUtils.isEmpty(service.checkID(mbsp_id)) ? "Y" : "N";
		
		entity = new ResponseEntity<String>(result, HttpStatus.OK);
		
		return entity;
	}
	
	
	//메일 인증 요청
	@ResponseBody
	@GetMapping("/sendMailAuth")
	public ResponseEntity<String> sendMailAuth(@RequestParam("mbsp_email") String mbsp_email){
		
		ResponseEntity<String> entity = null;
		
		String authCode = makeAuthCode();
		
		EmailDTO dto = new EmailDTO("docmall", "doccomsa@nate.com", mbsp_email, "docmall 인증 메일", authCode);
		
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			// 수신자 설정
			message.addRecipient(RecipientType.TO, new InternetAddress(mbsp_email));
			// 발신자 설정(이메일, 이름)
			message.addFrom(new InternetAddress[] {new InternetAddress(dto.getSenderMail(), dto.getSenderName())});
			// 제목
			message.setSubject(dto.getSubject(), "UTF-8");
			// 본문
			message.setText(dto.getMessage(), "UTF-8");
			
			mailSender.send(message);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			entity = new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	// 회원 가입 시 메일 인증 코드 생성
	private String makeAuthCode() {
		
		String authCode = "";

		
		for(int i=0; i<6; i++) {
			authCode += ((int)(Math.random() * 9) + 1);
		}
		
		return null;
	}

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
