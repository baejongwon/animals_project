package com.shelter_project.likes;

import java.util.ArrayList;
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

import com.shelter_project.center.CenterDTO;
import com.shelter_project.center.CenterService;
import com.shelter_project.infoBoard.InfoBoardDTO;
import com.shelter_project.infoBoard.InfoBoardService;
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
public class LikeController {

	@Autowired
	HttpSession session;
	@Autowired
	LikeService likeService;
	@Autowired
	PersonalService personalService;
	@Autowired
	InfoBoardService infoBoardService;
	@Autowired
	CenterService centerService;
	
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
	
		// 좋아요 관리
	 	@GetMapping("likeManage")
	 	public String likeManage(Model model) {
	 		
	 		String sessionID = (String) session.getAttribute("id");
	 		if (sessionID == null) {
	 			return "redirect:/login";
	 		}
	 		int start = 0;
	 		int limit = 3;
	 				
	 		List<PersonalDTO> perLikeBoards = new ArrayList<>();
	 	    List<InfoBoardDTO> infoLikeBoards = new ArrayList<>();
	 	    List<CenterDTO> cenLikeBoards = new ArrayList<>();
	 	    
	 	    List<LikeDTO> personalLikes = likeService.LikeBoards(sessionID, "per", start, limit);
	 	    List<LikeDTO> infoLikes = likeService.LikeBoards(sessionID, "info", start, limit);
	 	    List<LikeDTO> centerLikes = likeService.LikeBoards(sessionID, "cen", start, limit);

	 	    
	 	   // 개인분양 좋아요 게시글 처리
	 	    for (LikeDTO like : personalLikes) {
	 	        int postNo = like.getPost_no();
	 	        PersonalDTO perPost = personalService.getPerPost(postNo);
	 	        if (perPost != null) {
	 	            perLikeBoards.add(perPost);
	 	        }
	 	    }

	 	    // 지식공유 좋아요 게시글 처리
	 	    for (LikeDTO like : infoLikes) {
	 	        int postNo = like.getPost_no();
	 	        InfoBoardDTO infoPost = infoBoardService.getInfoPost(postNo);
	 	        if (infoPost != null) {
	 	            infoLikeBoards.add(infoPost);
	 	        }
	 	    }

	 	    // 보호센터 좋아요 게시글 처리
	 	    for (LikeDTO like : centerLikes) {
	 	        int postNo = like.getPost_no();
	 	        CenterDTO cenPost = centerService.getCenPost(postNo);
	 	        if (cenPost != null) {
	 	            cenLikeBoards.add(cenPost);
	 	        }
	 	    }

	 	    model.addAttribute("perLikeBoards", perLikeBoards);
	 	    model.addAttribute("infoLikeBoards", infoLikeBoards);
	 	    model.addAttribute("cenLikeBoards", cenLikeBoards);
	 		
	 		return "member/likeManage";
	 	}
		 // 개인분양 좋아요 더보기
	    @GetMapping("perLikeMore")
	    @ResponseBody
	    public List<PersonalDTO> loadMorePersonalPosts(@RequestParam int offset, @RequestParam int limit) {
	        String sessionID = (String) session.getAttribute("id");
	        if (sessionID == null) {
	            return null;
	        }
	        List<LikeDTO> likes = likeService.LikeBoards(sessionID, "per", offset, limit);
	        List<PersonalDTO> perLikeBoards = new ArrayList<>();
	        
	        for (LikeDTO like : likes) {
	 	        int postNo = like.getPost_no();

	 	        PersonalDTO perPost = personalService.getPerPost(postNo);
	 	        	if (perPost != null) {
	 	        		perLikeBoards.add(perPost);
	 	            }
	 	    }
	        return perLikeBoards;
	    }

		 // 보호센터 좋아요 더보기
	    @GetMapping("cenLikeMore")
	    @ResponseBody
	    public List<CenterDTO> loadMoreCenterPosts(@RequestParam int offset, @RequestParam int limit) {
	        String sessionID = (String) session.getAttribute("id");
	        if (sessionID == null) {
	            return null;
	        }

	        List<LikeDTO> likes = likeService.LikeBoards(sessionID,"cen", offset, limit);

	        List<CenterDTO> cenLikeBoards = new ArrayList<>();
	        
	        for (LikeDTO like : likes) {
	 	        int postNo = like.getPost_no();

	 	       CenterDTO cenPost = centerService.getCenPost(postNo);
	            if (cenPost != null) {
	                cenLikeBoards.add(cenPost);
	            }
	 	    }
	        return cenLikeBoards;
	    }
	    
	    // 지식공유 좋아요 더보기
	    @GetMapping("infoLikeMore")
	    @ResponseBody
	    public List<InfoBoardDTO> loadMoreInfoPosts(@RequestParam int offset, @RequestParam int limit) {
	        String sessionID = (String) session.getAttribute("id");
	        if (sessionID == null) {
	            return null;
	        }

	        List<LikeDTO> likes = likeService.LikeBoards(sessionID,"info", offset, limit);
	        
	        List<InfoBoardDTO> infoLikeBoards = new ArrayList<>();
	        
	        for (LikeDTO like : likes) {
	 	        int postNo = like.getPost_no();

	 	       InfoBoardDTO infoPost = infoBoardService.getInfoPost(postNo);
	            if (infoPost != null) {
	                infoLikeBoards.add(infoPost);
	            }
	 	    }
	        return infoLikeBoards;
	    }
	    
}
