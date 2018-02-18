package com.trackorjargh.javacontrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trackorjargh.javaclass.User;
import com.trackorjargh.javarepository.BookRepository;
import com.trackorjargh.javarepository.FilmRepository;
import com.trackorjargh.javarepository.ShowRepository;
import com.trackorjargh.javarepository.UserRepository;

@Controller
public class PageController {

	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShowRepository showRepository;

	@RequestMapping("/")
	public String serveIndex(Model model) {	
		model.addAttribute("lastFilm", filmRepository.findById(filmRepository.findLastId()));
		model.addAttribute("lastShow", showRepository.findById(showRepository.findLastId()));	
		return "index";
	}
	
	@RequestMapping("/peliculas")
	public String serveFilmList(Model model) {
		model.addAttribute("content", filmRepository.findAll(new PageRequest(0, 10)));
		model.addAttribute("typePage", "peliculas");
		
		return "contentList";
	}
	
	@RequestMapping("/series")
	public String serveShowList(Model model) {
<<<<<<< HEAD
		model.addAttribute("showList", showRepository.findAll());
		//films.get(0).setFirstInList(true);
		//model.addAttribute("filmsCarousel", films);
		//model.addAttribute("films", films);g
=======
		model.addAttribute("content", showRepository.findAll(new PageRequest(0, 10)));
		model.addAttribute("typePage", "series");

>>>>>>> dbe058ebc680f82f41b28d641b5edacaccfef29d
		return "contentList";
	}
	
	@RequestMapping("/libros")
	public String serveBookList(Model model) {
		model.addAttribute("content", bookRepository.findAll(new PageRequest(0, 10)));
		model.addAttribute("typePage", "libros");
		
		return "contentList";
	}
	
	@RequestMapping("/peliculas/{name}")
	public String serveFilmProfile(Model model, @PathVariable String name) {
		model.addAttribute("film", filmRepository.findByName(name));
		
		return "contentProfile";
	}
	
	@RequestMapping("/series/{name}")
	public String serveShowProfile(Model model, @PathVariable String name) {
		model.addAttribute("show", showRepository.findByName(name));
		
		return "contentProfile";
	}
	
	@RequestMapping("/libro/{name}")
	public String serveProfile(Model model, @PathVariable String name) {
		model.addAttribute("book", bookRepository.findByName(name));
		return "contentProfile";
	}
	@RequestMapping("/miperfil/{nickname}")
	public String serveUserProfile(Model model, @PathVariable String nickname) {
		User user = userRepository.findByName(nickname);
		model.addAttribute("user", user);
		return "userProfile";
	}
	
	@RequestMapping("/guardarLogin")
	public void guardarLogin(Model model, User user) {
		
	}
	
	@RequestMapping("/guardarRegister")
	public void guardarRegister(Model model, User user) {

		model.addAttribute(user);
				
	}
	
	@RequestMapping("/serveLogin")
	public String serveLogin(Model model) {
		
		
		return "login";
	}
}
