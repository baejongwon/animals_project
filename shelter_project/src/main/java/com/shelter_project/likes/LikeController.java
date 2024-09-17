package com.shelter_project.likes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Controller
public class LikeController {

	@Autowired
	HttpSession session;
	@Autowired
	LikeService likeService;
	
	@GetMapping("likeBoards")
	private String likeBoards(Model model){
		return "likeBoards";
	}
	
	@ResponseBody
	@PostMapping("like/{type}")
	public Map<String, String> handleLike(@PathVariable String type,
			 @RequestBody Map<String, Object> requestBody){	
		
		String sessionID = (String)session.getAttribute("id");
		
		Map<String, String> response = new HashMap<>();
		if (sessionID == null) {
			 response.put("status", "로그인이 필요합니다.");
			return response;
		}
		
		Integer postNo = (Integer)requestBody.get("postNo");
		String post_type = (String) requestBody.get("post_type");
	
		LikeDTO likeDTO = new LikeDTO();
		likeDTO.setMember_id(sessionID);
		likeDTO.setPost_no(postNo);
		likeDTO.setPost_type(post_type);
		
		if("active".equals(type)) {
			likeService.likeActive(likeDTO);
		}else if("Inactive".equals(type)) {
			likeService.likeInActive(likeDTO);
		}
		
		response.put("status", "success");
		return response;
	}
}
