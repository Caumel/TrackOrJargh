package com.trackorjargh.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trackorjargh.Class.Film;
import com.trackorjargh.Class.Lists;
import com.trackorjargh.Class.User;

@Controller
public class PageController {

	/*
	private List<Slide> slides = new ArrayList<>();
	private List<Film> films = new ArrayList<>();
	private User user;
	private List<ListContent> list = new ArrayList<>();
	private List<Content> contents = new ArrayList<>();*/
	
	public PageController() {	
		/*
		slides.add(new Slide("TrackOrJargh", "TrackOrJargh es una web para compartir opiniones sobre tu contenido favorito.", "img/carousel-index/serie.jpg", "#", " ", ""));
		slides.add(new Slide("TrackOrJargh", "TrackOrJargh es una web para compartir opiniones sobre tu contenido favorito.", "img/carousel-index/serie.jpg", "#", " ", ""));
		
		films.add(new Film(1, "Guardianes de la Galaxia 2", "Guardianes de la Galaxia Vol. 2 continúa las aventuras del equipo a medida que viajan cruzando\n los confines del cosmos. Los Guardianes tendrán que luchar para\n mantener su recién...", "img/Guardianes2.jpg", 2017, true, "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0", 1.6, null, null, null));
		films.add(new Film(2, "El Instante Más Oscuro", "Gran Bretaña, Segunda Guerra Mundial. Pocos días después\n de convertirse en Primer Ministro, Winston Churchill (Gary Oldman) debe tomar una difícil decisión. En pleno avance de las tropas nazis por toda Europa Occidental...", "img/portfolio/ElInstanteMasOscuro.jpg", 2010, true, "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0", 1.6, null, null, null));
		
		contents.add(new Film(1, "Guardianes de la Galaxia 2", "Guardianes de la Galaxia Vol. 2 continúa las aventuras del equipo a medida que viajan cruzando\n los confines del cosmos. Los Guardianes tendrán que luchar para\n mantener su recién...", "img/Guardianes2.jpg", 2017, true, "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0", 1.6, null, null, null));
		
		list.add(new ListContent("lista", true, contents));
		
		user = new User(1, "Oscar", "1234", "mail@mail.com", list, "img/userFoto.jpg", "User");
		*/
	}

	@RequestMapping("/")
	public String serveIndex(Model model) {		
		
		//slides.get(0).setFirstInList(true);
		//model.addAttribute("slide", slides);
		
		return "index";
	}
	
	@RequestMapping("/contentList")
	public String serveList(Model model) {
		
		//films.get(0).setFirstInList(true);
		//model.addAttribute("filmsCarousel", films);
		//model.addAttribute("films", films);
		
		return "contentList";
	}
	
	@RequestMapping("/contentProfile")
	public String serveProfile(Model model) {
		//model.addAttribute("film", new Film(1, "Guardianes de la Galaxia 2", "Guardianes de la Galaxia Vol. 2 continúa las aventuras del equipo a medida que viajan cruzando\n los confines del cosmos. Los Guardianes tendrán que luchar para\n mantener su recién...", "img/Guardianes2.jpg", 2017, true, "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0", 1.6, null, null, null));
		
		return "contentProfile";
	}
	
	@RequestMapping("/userProfile")
	public String serveUserProfile(Model model) {
		//model.addAttribute("user", user);
		//model.addAttribute("lists", user.getLists());
		
		return "userProfile";
	}
	
	@RequestMapping("/login")
	public String serveLogin(Model model) {
		
		return "login";
	}
}
