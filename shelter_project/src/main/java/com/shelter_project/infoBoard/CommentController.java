package com.shelter_project.infoBoard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@GetMapping("updateComment")
	private String updateComment(int postNo,int commentNo, String Content) {
		String replaceContent = Content.replace("\n", "<br>").replace(" ", "&nbsp;");
		commentService.updateComment(commentNo,replaceContent);
		return "redirect:/infoBoardContent?postNo=" + postNo;
	}
	
	@GetMapping("deleteComment")
	private String deleteComment(int commentNo,int postNo) {
		commentService.deleteComment(commentNo);
		return "redirect:/infoBoardContent?postNo=" + postNo;
	}
}
