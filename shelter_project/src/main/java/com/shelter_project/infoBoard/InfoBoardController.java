package com.shelter_project.infoBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.shelter_project.PageDTO;

import jakarta.servlet.http.HttpSession;
import retrofit2.http.POST;

@Controller
public class InfoBoardController {

	@Autowired
	InfoBoardService infoBoardService;
	@Autowired
	CommentService commentService;
	
	@Autowired
	private HttpSession session;

	// 게시글 목록
	@GetMapping("infoBoard")
	private String infoBoard(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page,
			String searchColumn, String keyword)
			throws Exception {

		ArrayList<InfoBoardDTO> boards = infoBoardService.getInfoBoards(page);
		int boardCount = infoBoardService.getBoardCount();
		PageDTO pageDTO = infoBoardService.pagingParam(page,searchColumn,keyword);
		
		model.addAttribute("boardCount", boardCount);
		model.addAttribute("boards", boards);
		model.addAttribute("paging",pageDTO);

		return "InfoBoard/infoBoard";
	}

	// 게시글 쓰기
	@GetMapping("infoBoardWrite")
	private String infoBoard(Model model) {
		// 아이디 인증
		String sessionID = (String) session.getAttribute("id");
		if (sessionID == null) {
			return "redirect:login";
		}

		return "InfoBoard/infoBoardWrite";
	}

	@PostMapping("infoBoardWriteProc")
	private String infoBoardWriteProc(MultipartHttpServletRequest multi) {
		infoBoardService.infoBoardWrite(multi);
		return "redirect:infoBoard";
	}


	@PostMapping("infoBoard/addImageBlobHook")
	public ResponseEntity<Map<String, String>> addImageBlobHook(@RequestParam("image") MultipartFile image) {
		String imagePath = infoBoardService.saveImage(image);

		if (imagePath != null) {
			Map<String, String> response = new HashMap<>();

			response.put("imagePath", imagePath);

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(500).build();
		}
	}

	// 게시글 컨텐츠
	@GetMapping("infoBoardContent")
	public String infoBoardContent(Model model, int postNo,@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		InfoBoardDTO board = infoBoardService.getContent(postNo);
		List<CommentDTO> comments = commentService.getComments(postNo); 
		for(CommentDTO comment : comments) {
			String originalContent = comment.getContent();
			String replaceContent = originalContent.replace("\r\n","<br>");
			comment.setContent(replaceContent);
		}
		String type = "info";
		int commentCount = commentService.getCount(postNo,type);
		model.addAttribute("type",type);
		model.addAttribute("board", board);
		model.addAttribute("comments",comments);
		model.addAttribute("paging",page);
		model.addAttribute("commentCount",commentCount);
		return "InfoBoard/infoBoardContent";
	}

	// 게시글 수정
	@GetMapping("infoBoardModify")
	public String infoBoardModify(Model model, int postNo) {
		InfoBoardDTO board = infoBoardService.getContent(postNo);
		model.addAttribute("board", board);
		return "InfoBoard/infoBoardModify";
	}

	@PostMapping("infoBoardModifyProc")
	private String infoBoardModifyProc(MultipartHttpServletRequest multi, @Param("postNo") int postNo) {
		System.out.println("수정 컨트롤러 호출");
		infoBoardService.infoBoardModifyProc(multi, postNo);
		return "redirect:infoBoard";
	}

	// 게시글 삭제
	@GetMapping("deleteBoard")
	public String deleteBoard(@RequestParam("postNo") int postNo) {

		// 아이디 인증
		String sessionID = (String) session.getAttribute("id");
		if (sessionID == null) {
			return "redirect:login";
		}

		System.out.println(postNo);

		infoBoardService.deleteBoard(postNo);
		return "redirect:infoBoard";
	}

	@PostMapping("infoSearch")
	public String infoSearch(String searchColumn, String keyword,Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		List<InfoBoardDTO> boards = infoBoardService.infoSearch(searchColumn, keyword,page);
		int SearchCount = infoBoardService.getSearchCount(searchColumn,keyword);
		
		PageDTO pageDTO = infoBoardService.pagingParam(page,searchColumn,keyword);
		
		model.addAttribute("boards",boards);
		model.addAttribute("boardCount", SearchCount);
		model.addAttribute("paging",pageDTO);
		
		return "InfoBoard/infoBoard";
	}
	
}
