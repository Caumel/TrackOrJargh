package com.trackorjargh.javacontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trackorjargh.javarepository.BookRepository;
import com.trackorjargh.javarepository.FilmRepository;
import com.trackorjargh.javarepository.ShowRepository;

@Controller
public class SearchController {

	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ShowRepository showRepository;

	@RequestMapping("/busqueda")
	public String search(Model model) {		
		return "search";
	}
	
	@RequestMapping("/busqueda/{optionSearch}/pelicula/{name}")
	public String searchFilms(Model model, @PathVariable String optionSearch, @PathVariable String name) {		
		model.addAttribute("content", filmRepository.findByNameContainingIgnoreCase(name, new PageRequest(0, 10)));
		model.addAttribute("typeFilm", true);
		model.addAttribute("inputSearch", name);
		model.addAttribute("typeSearch", "/rest/busqueda/" + optionSearch +"/peliculas/" + name + "/page");
		
		return "search";
	}
	
	@RequestMapping("/busqueda/{optionSearch}/serie/{name}")
	public String searchShows(Model model, @PathVariable String optionSearch, @PathVariable String name) {		
		model.addAttribute("content", showRepository.findByNameContainingIgnoreCase(name, new PageRequest(0, 10)));
		model.addAttribute("typeShow", true);
		model.addAttribute("inputSearch", name);
		model.addAttribute("typeSearch", "/rest/busqueda/" + optionSearch +"/series/" + name + "/page");
		
		return "search";
	}
	
	@RequestMapping("/busqueda/{optionSearch}/libro/{name}")
	public String searchBooks(Model model, @PathVariable String optionSearch, @PathVariable String name) {		
		model.addAttribute("content", bookRepository.findByNameContainingIgnoreCase(name, new PageRequest(0, 10)));		
		model.addAttribute("typeBook", true);
		model.addAttribute("inputSearch", name);
		model.addAttribute("typeSearch", "/rest/busqueda/" + optionSearch +"/libros/" + name + "/page");
		
		return "search";
	}

}
