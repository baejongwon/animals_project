package com.shelter_project.member;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
public class MemberService {

	@Autowired MemberMapper MemberMapper;
	@Autowired private HttpSession session;
	
	@Value("${coolsms.apikey}")
    private String apiKey;

    @Value("${coolsms.apisecret}")
    private String apiSecret;

    @Value("${coolsms.fromnumber}")
    private String fromNumber;
	
	public String registProc(MemberDTO member) {
	
		if(member.getId() == null || member.getId().trim().isEmpty()) {
			return "아이디가 입력하십시오.";
		}
		if(member.getPw() == null || member.getPw().trim().isEmpty()) {
			return "비밀번호를 입력하십시오.";
		}
		
		MemberDTO checkID = MemberMapper.checkId(member.getId());
		
		if(checkID!=null) {
			return "이미 사용중인 아이디입니다.";
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //DB에 데이터 저장하기 전에 암호화 진행.
		String encodePw = encoder.encode(member.getPw());
		member.setPw(encodePw);
		
		int result = MemberMapper.registPro(member);
		if(result == 1) {
			session.removeAttribute(member.getTel());
			return "회원가입완료";	
		}
		return "회원가입실패 ";
	}

	public String loginProc(String id, String pw) {
		
		MemberDTO userInfo = MemberMapper.checkId(id);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				
		if(userInfo != null && encoder.matches(pw, userInfo.getPw())==true) {
			session.setAttribute("id", userInfo.getId());
			session.setAttribute("pw", userInfo.getPw());
			session.setAttribute("name", userInfo.getName());
			session.setAttribute("email", userInfo.getEmail());
			session.setAttribute("address", userInfo.getAddress());
			session.setAttribute("tel", userInfo.getTel());
			
			return "로그인 성공";
		}
		return "아이디 또는 비밀번호를 확인 후 다시 입력하십시오.";
	}

	public String sendSms(String tel) {
		
		//랜덤 숫자 생성
		Random rand  = new Random();
		String numStr = "";
		for(int i=0; i<4; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}
		
		session.setAttribute(tel, numStr);
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.schedule(() -> session.removeAttribute(tel), 3, TimeUnit.MINUTES);
		
		DefaultMessageService messageService =  NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
		// Message 패키지가 중복될 경우 net.nurigo.sdk.message.model.Message로 치환하여 주세요
		Message message = new Message();
		message.setFrom(fromNumber);
		message.setTo(tel);
	    message.setText("명냥고홈의 가입 인증번호는"+ numStr +"입니다.");

		try {
		  messageService.send(message);
		  return "전송 완료 3분 이내로 인증번호를 입력해 주십시오.";
		} catch (NurigoMessageNotReceivedException exception) {
		  // 발송에 실패한 메시지 목록을 확인할 수 있습니다.
		  System.out.println(exception.getFailedMessageList());
		  System.out.println(exception.getMessage());
		  return "전송 실패";
		} catch (Exception exception) {
		  System.out.println(exception.getMessage());
		  return "전송 실패";
		}
		
	}

	public String smsCheck(String tel, String confirmNum) {
		 String sessionAuthNum = (String) session.getAttribute(tel);
		 if(confirmNum.equals(sessionAuthNum)) {
			 return "인증 성공";
		 }
		return "인증 실패";
	}

}
