package com.shelter_project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shelter_project.personal.PersonalDTO;
import com.shelter_project.personal.PersonalService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired PersonalService personalService;
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	@RequestMapping("header")
	public String header() {
		return "default/header";
	}
	@RequestMapping("main")
	public String top(Model model) {
		List<PersonalDTO> boards = personalService.getMainContent();
		
		Map<Integer,String> imagePathMap = new HashMap<>();
		
		for(PersonalDTO board : boards) {
			List<String> images = personalService.animalImg(board.getAnimal_no());
			
			if(!images.isEmpty()) {
				imagePathMap.put(board.getAnimal_no(), images.get(0));
			}
		}
		model.addAttribute("imagePathMap",imagePathMap);
		model.addAttribute("boards",boards);
		return "default/main";
	}
	@RequestMapping("footer")
	public String bottom() {
		return "default/footer";
	}
	
}
