package com.shelter_project.infoBoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class InfoBoardController {

	@Autowired InfoBoardService infoBoardService;
	@Autowired private HttpSession session;
	
	@GetMapping("infoBoard")
	private String infoBoard(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) throws Exception {
		
		List<InfoBoardDTO> boards = infoBoardService.getInfoBoard(page);
		int boardCount = infoBoardService.getBoardCount();
		
		model.addAttribute("boardCount",boardCount);
		model.addAttribute("boards",boards);
		
		return "InfoBoard/infoBoard";
	}
	
	@GetMapping("infoBoardWrite")
	private String infoBoard(){
		//아이디 인증
//		String sessionID=(String)session.getAttribute("id");
//		if(sessionID==null) {
//			return "redirect:login";
//		}
		return "InfoBoard/infoBoardWrite";
	}
	
	@PostMapping("infoBoardWriteProc")
	private String infoBoardWriteProc() {
		return null;
	}
	
}
