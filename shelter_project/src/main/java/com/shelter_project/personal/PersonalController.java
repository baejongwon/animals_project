package com.shelter_project.personal;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shelter_project.PageDTO;
import com.shelter_project.infoBoard.CommentDTO;
import com.shelter_project.infoBoard.CommentService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;

@Controller
public class PersonalController {
	
	@Autowired PersonalService personalService;
	@Autowired HttpSession session;
	@Autowired CommentService commentService;
	
	@GetMapping("/personalBoards")
	private String personalBoards(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) throws Exception {
		
		List<PersonalDTO> boards;
		PageDTO pageDTO;
		
		boards = personalService.getBoards(page);
		Map<Integer,String> imagePathMap = new HashMap<>();
		
		for(PersonalDTO board : boards) {
			List<String> images = personalService.animalImg(board.getAnimal_no());
			
			if(!images.isEmpty()) {
				String[] parts = images.get(0).split("\\\\");
				String imagePath = parts[12]+"/"+parts[13];
				imagePathMap.put(board.getAnimal_no(), imagePath);
			}
		}
		
		pageDTO = personalService.pagingParam(page);
		
		model.addAttribute("boards",boards);
		model.addAttribute("paging",pageDTO);
		model.addAttribute("imagePathMap",imagePathMap);
		return "personal/personalBoards";
	}
	
	@GetMapping("/personalWrite")
	private String personalWrite() {
		
		String sessionID = (String) session.getAttribute("id");
		if (sessionID == null) {
			return "redirect:login";
		}
		
		return "personal/personalWrite";
	}
	
	@PostMapping("/personalWriteProc")
	private String personalWriteProc(MultipartHttpServletRequest multi) {
		personalService.personalWriteProc(multi);
		return "redirect:/personalBoards";
	}
	
	@GetMapping("/personalContent")
	private String personalContent(Model model,int no,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page){
		PersonalDTO board = personalService.personalContent(no);
		
		List<CommentDTO> comments = commentService.getPerComments(no);
		for(CommentDTO comment : comments) {
			String originalContent = comment.getContent();
			String replaceContent = originalContent.replace("\r\n","<br>");
			comment.setContent(replaceContent);
		}
		String type = "per";
		int commentCount = commentService.getCount(no,type);
		
		Map<Integer,List<String>> ImagesMap = new HashMap<>();
		
		List<String> images = personalService.animalImg(no);
		List<String> imageNames = new ArrayList<>();
		for(String image : images) {
			String[] parts = image.split("\\\\");
			 String imagePath = parts[12] + "/" + parts[13];
			 imageNames.add(imagePath);
		}
		
		ImagesMap.put(board.getAnimal_no(), imageNames);
		
		model.addAttribute("type",type);
		model.addAttribute("board",board);
		model.addAttribute("paging",page);
		model.addAttribute("ImagesMap",ImagesMap);
		model.addAttribute("commentCount",commentCount);
		model.addAttribute("comments",comments);
		return "personal/personalContent";
	}
	@GetMapping("/personalModify")
	private String personalModify(Model model,@RequestParam("animal_no") int animal_no) {
		PersonalDTO board = personalService.personalModify(animal_no);
		model.addAttribute("board", board);
		return "personal/personalModify";
	}
	
	@PostMapping("/personalModifyProc")
	private String personalModifyProc(MultipartHttpServletRequest multi, int animal_no) {
		
		personalService.personalModifyProc(multi,animal_no);
		return "redirect:/personalBoards";
	}
	
	@GetMapping("/personalDelete")
	private String personalDelete(int animal_no) {
		personalService.personalDelete(animal_no);
		return "redirect:/personalBoards";
	}
	
}

	