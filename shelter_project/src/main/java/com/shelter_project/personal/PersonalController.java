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

import com.shelter_project.PageDTO;

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
	
	@GetMapping("/personalBoards")
	private String personalBoards(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) throws Exception {
		
		List<PersonalDTO> boards;
		PageDTO pageDTO;
		
		boards = personalService.getBoards(page);	
		pageDTO = personalService.pagingParam(page);
		
		model.addAttribute("boards",boards);
		model.addAttribute("paging",pageDTO);
		
		return "personal/personalBoards";
	}
	
	@GetMapping("/personalWrite")
	private String personalWrite() {
		return "personal/personalWrite";
	}
	
}

	