package com.shelter_project.center;

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

import com.shelter_project.PageDTO;
import com.shelter_project.personal.PersonalDTO;

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
public class CenterController {
	
	@Autowired CenterService centerService;
	
	@GetMapping("adoption")
	public String adoption(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "type", required = false, defaultValue = "all") String type,
			String searchColumn, String keyword) throws Exception {
		
		List<CenterDTO> boards;
		PageDTO pageDTO;

		Map<Integer, String> firstImagesMap = new HashMap<>();
		
		boards = centerService.getBoards(page,type);
		pageDTO = centerService.pagingParam(page,type,searchColumn,keyword);
		
		
		for(CenterDTO board : boards) {
			List<String> images = centerService.animalImg(board.getAnimal_no());
			if(!images.isEmpty()) {
				
				String firstImage = images.get(0);
				firstImagesMap.put(board.getAnimal_no(),firstImage);
			}
		}
	
		model.addAttribute("type",type);
		model.addAttribute("paging",pageDTO);
		model.addAttribute("firstImagesMap",firstImagesMap);
		model.addAttribute("boards",boards);
		
		return "center/adoption";
	}
	
	@GetMapping("adoptionDetail")
	public String adoptionDetail(Model model,int no) {
		
		CenterDTO board = centerService.adoptionDetail(no);
		
		Map<Integer, List<String>> ImagesMap = new HashMap<>();
		
		List<String> images = centerService.animalImg(no);
		ImagesMap.put(board.getAnimal_no(), images);
		
		//유튜브 링크
		if(board.getIntrcn_mvp_url()!=null) {
			String youtubeURL = board.getIntrcn_mvp_url();
			String youtubeID = null;
			if(youtubeURL.contains("youtu.be/")) {
				youtubeID = youtubeURL.substring(youtubeURL.lastIndexOf("/") + 1);
				model.addAttribute("youtubeID",youtubeID);
			}
		}
		
		model.addAttribute("ImagesMap",ImagesMap);
		model.addAttribute("board",board);
		
		return "center/adoptionDetail";
	}
	
	@PostMapping("centerSearch")
	public String perSearch(String type,String searchColumn, String keyword,Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		List<CenterDTO> boards = centerService.centerSearch(searchColumn, keyword,page);
		
		Map<Integer, String> firstImagesMap = new HashMap<>();
		Map<Integer,String> imagePathMap = new HashMap<>();
		PageDTO pageDTO = centerService.pagingParam(page,type,searchColumn,keyword);
		
		for(CenterDTO board : boards) {
			List<String> images = centerService.animalImg(board.getAnimal_no());

			if(!images.isEmpty()) {
				String firstImage = images.get(0);
				firstImagesMap.put(board.getAnimal_no(),firstImage);
			}
			
		}
		
		model.addAttribute("firstImagesMap",firstImagesMap);
		model.addAttribute("boards",boards);
		model.addAttribute("paging",pageDTO);
		
		return "center/adoption";
	}
}

	