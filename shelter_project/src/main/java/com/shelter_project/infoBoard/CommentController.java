package com.shelter_project.infoBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommentController {

	@Autowired
	CommentService commentService;
	
	// 댓글 작성
		@PostMapping("addComment")
		public String addComment(@RequestParam(value = "postNo", required = false) Integer postNo, @RequestParam("content") String content,
				HttpSession session, @RequestParam("parentNo") int parentNo,
				@RequestParam("type") String type, @RequestParam(value = "animal_no", required = false) Integer animal_no) {
			String sessionID = (String) session.getAttribute("id");
			if(sessionID == null) {
				return "redirect:login";
			}
			if(type.equals("info")) {
				commentService.addComment(postNo,content,sessionID,parentNo,type);
				return "redirect:/infoBoardContent?postNo=" + postNo;
			}else if (type.equals("per")) {
				commentService.addPerComment(animal_no,content,sessionID,parentNo,type);
				return "redirect:/personalContent?no=" + animal_no;
			}
			
			return "redirect:/index";
		}
		
	@GetMapping("updateComment")
	private String updateComment(int no,int commentNo, String content, String type) {
		
		String replaceContent = content.replace("\n", "<br>").replace(" ", "&nbsp;");
		commentService.updateComment(commentNo,replaceContent);
		if(type.equals("info")) {
			return "redirect:/infoBoardContent?postNo=" + no;
		}
		return "redirect:/personalContent?no=" + no;
	}
	
	@GetMapping("deleteComment")
	private String deleteComment(int commentNo, @RequestParam("no") int no,String type) {
		commentService.deleteComment(commentNo);

		if(type.equals("info")) {
			return "redirect:/infoBoardContent?postNo=" + no;
		}
		return "redirect:/personalContent?no=" + no;
	}
}
