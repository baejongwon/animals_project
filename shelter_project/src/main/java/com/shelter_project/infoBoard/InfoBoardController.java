package com.shelter_project.infoBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

@Controller
public class InfoBoardController {

	@Autowired InfoBoardService infoBoardService;
	@Autowired private HttpSession session;
	
	@GetMapping("infoBoard")
	private String infoBoard(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) throws Exception {
		
		ArrayList<InfoBoardDTO> boards = infoBoardService.getInfoBoards(page);
		int boardCount = infoBoardService.getBoardCount();
		
		model.addAttribute("boardCount",boardCount);
		model.addAttribute("boards",boards);
		
		return "InfoBoard/infoBoard";
	}
	
	@GetMapping("infoBoardWrite")
	private String infoBoard(Model model){
		//아이디 인증
		String sessionID=(String)session.getAttribute("id");
		if(sessionID==null) {
			return "redirect:login";
		}
		
		return "InfoBoard/infoBoardWrite";
	}
	
	@PostMapping("infoBoardWriteProc")
	private String infoBoardWriteProc(MultipartHttpServletRequest multi) {
		infoBoardService.infoBoardWrite(multi);
		return "redirect:infoBoard";
	}
	@PostMapping("addImageBlobHook")
	public ResponseEntity<Map<String, String>> addImageBlobHook(@RequestParam("image") MultipartFile image) {
        String imageUrl = infoBoardService.saveImage(image);

        if (imageUrl != null) {
            Map<String, String> response = new HashMap<>();
            response.put("imageUrl", imageUrl);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(500).build();
        }
    }
	
	@GetMapping("infoBoardContent")
	public String infoBoardContent(Model model,int postNo) {
		InfoBoardDTO board = infoBoardService.getContent(postNo);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String contentJson  = mapper.writeValueAsString(board.getContent());
			model.addAttribute("contentJson", contentJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		model.addAttribute("board", board);
		return "InfoBoard/infoBoardContent";
	}

}
