package com.shelter_project.member;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
public class MemberController {

	@Autowired MemberService MemberService;
	@Autowired HttpSession session;
	
	//로그인
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	@GetMapping("logout")
	public String logout() {
		session.invalidate();
		return "redirect:index";
	}
	//로그인 검증
	@PostMapping("loginProc")
	public String loginProc(@RequestParam("id") String id, @RequestParam("pw") String pw,Model model) {

		String msg = MemberService.loginProc(id,pw);
		
		if(msg.equals("로그인 성공")) {
			return "redirect:index";
		}
		model.addAttribute("msg", msg);
		return "member/login";
	}
	
	//회원가입
	@GetMapping("regist")
	public String regist() {
		return "member/regist";
	}

	@PostMapping("registerProc")
	public String registProc(MemberDTO member, @RequestParam("sample6_address") String postcode, @RequestParam("sample6_detailAddress") String detailAddress,@RequestParam("confirmNum") String confirmNum) {
		
		String msg= "";
		msg = smsCheck(member.getTel(), confirmNum);
		if(msg.equals("인증 실패")) {
			msg = "인증번호가 다릅니다.";
			return "member/regist";
		}
		
		member.setAddress(postcode+detailAddress); 
		msg = MemberService.registProc(member);
		
		if(msg == "회원가입완료") {return "redirect:index";}
		
		return "member/regist";
		
	}
	
	
	//전화번호 인증
	@PostMapping("sendSms")
	@ResponseBody
	public String sendSms(@RequestParam("tel") String tel) {
		String msg = "";
		if(tel == null || tel.trim().isEmpty()) {
			msg = "전화번호를 입력해 주십시오.";
			return msg;
		}
		 msg = MemberService.sendSms(tel);
		return msg;
	}
	
	@PostMapping("smsCheck")
	@ResponseBody
	public String smsCheck(@RequestParam("tel") String tel, @RequestParam("confirmNum") String confirmNum) {
		String msg = MemberService.smsCheck(tel,confirmNum);
		return msg;
	}
	
	
	@GetMapping("mypage")
	public String mypage() {
		return "member/mypage";
	}
	
}