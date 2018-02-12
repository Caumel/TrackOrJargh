package com.trackorjargh;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/")
	public String serveIndex(Model model) {
		
		return "index";
	}
	
	@RequestMapping("/contentList")
	public String serveList(Model model) {
		
		return "contentList";
	}
	
	@RequestMapping("/contentProfile")
	public String serveProfile(Model model) {
		
		return "contentProfile";
	}
	
	@RequestMapping("/userProfile")
	public String serveUserProfile(Model model) {
		
		return "userProfile";
	}
	
	@RequestMapping("/login")
	public String serveLogin(Model model) {
		
		return "login";
	}
}
