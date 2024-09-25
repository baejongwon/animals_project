package com.shelter_project.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shelter_project.center.CenterDTO;
import com.shelter_project.center.CenterService;
import com.shelter_project.infoBoard.InfoBoardDTO;
import com.shelter_project.infoBoard.InfoBoardService;
import com.shelter_project.likes.LikeDTO;
import com.shelter_project.likes.LikeService;
import com.shelter_project.personal.PersonalDTO;
import com.shelter_project.personal.PersonalService;

import jakarta.servlet.http.HttpSession;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
public class MemberController {

	@Autowired
	MemberService MemberService;
	@Autowired
	PersonalService personalService;
	@Autowired
	InfoBoardService infoBoardService;
	
	@Autowired
	HttpSession session;
	@Autowired
	private KaKaoService kakaoService;

//	KaKaoApi kakaoapi = new KaKaoApi();

	@Value("${kakaoApiKey}")
	private String kakaoAPI;
	@Value("${redirectUri}")
	private String redirectUri;

	// 로그인
	@GetMapping("login")
	public String login(Model model) {
		model.addAttribute("kakaoApiKey", kakaoAPI);
		model.addAttribute("redirectUri", redirectUri);
		return "member/login";
	}

	@GetMapping("login/kakao")
	public String kakaoLogin(String code) {
		kakaoService.getAccessToken(code);
		kakaoService.getUserInfo();
		return "redirect:/index";
	}

	@GetMapping("logout")
	public String logout() {
		String accessToken = (String) session.getAttribute("accessToken");
		if (accessToken != null) {
			try {
				kakaoService.unlink(accessToken);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.invalidate();

		return "redirect:index";
	}

	// 로그인 검증
	@PostMapping("loginProc")
	public String loginProc(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) {

		String msg = MemberService.loginProc(id, pw);

		if (msg.equals("로그인 성공")) {
			return "redirect:index";
		}
		model.addAttribute("msg", msg);
		return "member/login";
	}

	// 회원가입
	@GetMapping("regist")
	public String regist() {
		return "member/regist";
	}

	@PostMapping("registerProc")
	public String registProc(MemberDTO member, @RequestParam("sample6_postcode") String postcode,
			@RequestParam("sample6_address") String address,
			@RequestParam("sample6_detailAddress") String detailAddress,
			@RequestParam("confirmNum") String confirmNum,
			Model model) {

		// 전화번호 인증 확인
		String msg = "";
		msg = smsCheck(member.getTel(), confirmNum);
		if (msg.equals("인증 실패")) {
			msg = "인증번호가 다릅니다.";
			model.addAttribute("msg",msg);
			return "member/regist";
		}

		//주소 정보 합치기
		member.setAddress(postcode + "," + address + "," + detailAddress);
		msg = MemberService.registProc(member);

		if (msg.equals("회원가입완료")) {
			return "redirect:index";
		}else if(msg.equals("아이디를 입력하십시오.")) {
			model.addAttribute("msg",msg);
			return "member/regist";
		}else if(msg.equals("비밀번호를 입력하십시오.")) {
			model.addAttribute("msg",msg);
			return "member/regist";
		}else if(msg.equals("이미 사용중인 아이디입니다.")) {
			model.addAttribute("msg",msg);
			return "member/regist";
		}else if(msg.equals("이미 등록된 핸드폰 번호입니다.")) {
			model.addAttribute("msg",msg);
			return "member/regist";
		}
		
		msg = "회원가입 실패 다시 시도해 주십시오.";
		model.addAttribute("msg",msg);
		return "member/regist";
	}

	// 전화번호 인증
	@PostMapping("sendSms")
	@ResponseBody
	public String sendSms(@RequestParam("tel") String tel) {
		String msg = "";
		if (tel == null || tel.trim().isEmpty()) {
			msg = "전화번호를 입력해 주십시오.";
			return msg;
		}
		msg = MemberService.sendSms(tel);
		return msg;
	}

	@PostMapping("/smsCheck")
	@ResponseBody
	public String smsCheck(@RequestParam("tel") String tel, @RequestParam("confirmNum") String confirmNum) {
		String msg = MemberService.smsCheck(tel, confirmNum);
		return msg;
	}

	@GetMapping("mypage")
	public String mypage() {
		return "member/mypage";
	}
	//프로필 관리
	@GetMapping("profileManage")
	public String profileManage(Model model) {
		
		String sessionID = (String) session.getAttribute("id");
		if (sessionID == null) {
			return "redirect:/login";
		}
		MemberDTO member = MemberService.profileManage(sessionID);
		
		if(member != null) {
			if(member.getAddress() != null) {
				String[] addressParts = member.getAddress().split(",",3);
				String postcode = addressParts[0];
				String address = addressParts[1];
				String detailAddress = addressParts[2];
				
				model.addAttribute("postcode",postcode);
				model.addAttribute("address",address);
				model.addAttribute("detailAddress",detailAddress);
			}
			model.addAttribute("member",member);
		}

		return "member/profileManage";
	}
	// 게시글 관리
	@GetMapping("postManage")
	public String postManage(Model model) {
		
		String sessionID = (String) session.getAttribute("id");
		if (sessionID == null) {
			return "redirect:/login";
		}
		int start = 0;
		int limit = 3;
				
		List<PersonalDTO> perBoards = personalService.perBoards(sessionID,start,limit);
		List<InfoBoardDTO> infoBoards = infoBoardService.infoBoards(sessionID,start,limit);
		
		model.addAttribute("perBoards",perBoards);
		model.addAttribute("infoBoards",infoBoards);
		
		return "member/postManage";
	}
	
	 // 개인분양 게시글 더보기
    @GetMapping("personalMore")
    @ResponseBody
    public List<PersonalDTO> loadMorePersonalPosts(@RequestParam int offset, @RequestParam int limit) {
        String sessionID = (String) session.getAttribute("id");
        if (sessionID == null) {
            return null;
        }

        return personalService.perBoards(sessionID, offset, limit);
    }

    // 지식공유 게시글 더보기
    @GetMapping("infoMore")
    @ResponseBody
    public List<InfoBoardDTO> loadMoreInfoPosts(@RequestParam int offset, @RequestParam int limit) {
        String sessionID = (String) session.getAttribute("id");
        if (sessionID == null) {
            return null;
        }

        return infoBoardService.infoBoards(sessionID, offset, limit);
    }
    
    
	//정보 업데이트
	@PostMapping("updateProc")
	public String updateProc(MemberDTO member, @RequestParam("sample6_postcode") String postcode,
			@RequestParam("sample6_address") String address,
			@RequestParam("sample6_detailAddress") String detailAddress) {

		String msg = "";

		member.setAddress(postcode + "," + address + "," + detailAddress);

		msg = MemberService.updateProc(member);

		if (msg == "수정완료") {
			return "redirect:index";
		}

		return "member/profileManage";
	}

	
	//아이디 찾기 
		@GetMapping("findID")
		public String findID() {
			return "member/findID";
		}
		@PostMapping("findIDProc")
		public String findIDProc(MemberDTO member,
				@RequestParam("confirmNum") String confirmNum,
				Model model,
				RedirectAttributes redirectAttributes) {
			
			String msg = "";
			msg = smsCheck(member.getTel(), confirmNum);
			if (msg.equals("인증 실패")) {
				msg = "인증번호가 다릅니다.";
				model.addAttribute("msg",msg);
				return "member/findID";
			}else {
				String userID = MemberService.findID(member);
				if(userID!=null) {
					msg = "아이디는 "+ userID + "입니다.";
					redirectAttributes.addFlashAttribute("msg", msg);
				}else {
					msg = "회원가입된 아이디가 없습니다.";
					redirectAttributes.addFlashAttribute("msg", msg);
				}
			}
			 
			return "redirect:login";
		}
		
		//비밀번호 찾기
		@GetMapping("findPW")
		public String findPW() {
			return "member/findPW";
		}
		@PostMapping("findPWProc")
		public String findPwProc(MemberDTO member,
				@RequestParam("confirmNum") String confirmNum,
				Model model,
				RedirectAttributes redirectAttributes) {
			
			String msg = "";
			msg = smsCheck(member.getTel(), confirmNum);
			if (msg.equals("인증 실패")) {
				msg = "인증번호가 다릅니다.";
				model.addAttribute("msg",msg);
				return "member/findPW";
			}else {
				String userID = MemberService.findPw(member);
				
				if(userID!=null) {
					redirectAttributes.addFlashAttribute("userID",userID);
					session.removeAttribute(member.getTel()); 
					return "redirect:changePw";
				}else {
					msg = "이름 또는 아이디가 틀렸습니다.";
					redirectAttributes.addFlashAttribute("msg", msg);
				}
			}
			session.removeAttribute(member.getTel()); 
			return "redirect:login";
		}
		
		//비밀번호 바꾸기
		@GetMapping("changePw")
		public String changePw() {
			return "member/changePw";
		}
		
		@PostMapping("/changePwProc")
		public String changePwProc( @RequestParam("pw") String pw,
				@RequestParam("confirmPw") String confirmPw,
				@RequestParam("userID") String userID,
				Model model,
				RedirectAttributes redirectattributes) {
			
		    if (!pw.equals(confirmPw)) {
		        model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
		        return "member/changePw";
		    }
		    String result =  MemberService.changePw(userID,pw);
		    
		    if (result.equals("성공")) {
		    	redirectattributes.addFlashAttribute("msg", "비밀번호가 성공적으로 변경되었습니다.");
		        return "redirect:login"; 
		    } else {
		        model.addAttribute("msg", "비밀번호 변경에 실패했습니다.");
		        return "member/changePw";
		    }
		}
}
