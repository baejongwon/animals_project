package com.shelter_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	
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
		return "default/main";
	}
	@RequestMapping("footer")
	public String bottom() {
		return "default/footer";
	}
	
}
