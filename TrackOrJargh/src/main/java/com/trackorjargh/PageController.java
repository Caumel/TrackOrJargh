package com.trackorjargh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@Autowired
	private Slide slide;
	private List<Slide> slides = new ArrayList<Slide>();
	
	public void initialize() {
		Slide slide1 = new Slide();
		Slide slide2 = new Slide();
		Slide slide3 = new Slide();
		Slide slide4 = new Slide();
		slides.add(slide1);
		for (Slide x : slides) {
			x.setTitle("TrackOrJargh");
			x.setContent("TrackOrJargh es una web para compartir opiniones sobre tu contenido favorito.");
			x.setImage("img/carousel-index/serie.jpg");
			x.setButton("Conócenos!");
			x.setLink("#");
			x.setActive(" ");
		}
		slides.get(0).setActive("active");
	}
	
	@RequestMapping("/")
	public String serveIndex(Model model) {
		model.addAttribute("slide", slides);
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
