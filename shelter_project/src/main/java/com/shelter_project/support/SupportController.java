package com.shelter_project.support;

import java.io.IOException;
import java.lang.reflect.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shelter_project.member.MemberDTO;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import jakarta.servlet.http.HttpSession;

@Controller
public class SupportController {

	@Autowired
	private HttpSession session;

	@Value("${imp.api.Key}")
	private String impApiKey;

	@Value("${imp.api.Secret}")
	private String impApiSecret;

	private final IamportClient iamportClient;

	@GetMapping("donate")
	public String support() {
		return "support/donate";
	}

	// 기부자 정보 얻기
	@ResponseBody
	@GetMapping("getContributor")
	public MemberDTO getContributor() {
		String sessionID = (String) session.getAttribute("id");
		
		if (sessionID == null) {
			throw new IllegalStateException("로그인이 필요합니다.");
		}
		
		String email = (String) session.getAttribute("email");
		String name = (String) session.getAttribute("name");
		String tel = (String) session.getAttribute("tel");
		String addr = (String) session.getAttribute("address") + (String) session.getAttribute("detailAddress"); 
		
		MemberDTO user = new MemberDTO();
		user.setEmail(email);
		user.setName(name);
		user.setTel(tel);
		user.setAddress(addr);
		
		return user;
	}

	public SupportController(@Value("${imp.api.Key}") String impApiKey,
			@Value("${imp.api.Secret}") String impApiSecret) {
		this.iamportClient = new IamportClient(impApiKey, impApiSecret);
	}

	@ResponseBody
	@RequestMapping("/verify/{imp_uid}")
	public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String imp_uid)
			throws IamportResponseException, IOException {
		return iamportClient.paymentByImpUid(imp_uid);
	}
}
