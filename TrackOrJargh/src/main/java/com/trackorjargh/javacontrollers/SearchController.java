package com.trackorjargh.javacontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.Show;
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
		model.addAttribute("noElementsSearch", true);
		model.addAttribute("index", true);
		return "search";
	}
	
	@RequestMapping("/busqueda/{optionSearch}/pelicula/{name}")
	public String searchFilms(Model model, @PathVariable String optionSearch, @PathVariable String name) {
		Page<Film> films;

		if(optionSearch.equalsIgnoreCase("titulo")) {
			films = filmRepository.findByNameContainingIgnoreCase(name, new PageRequest(0, 10));
		} else {
			films = filmRepository.findFilmsByGender("%" + name + "%", new PageRequest(0, 10));
		}
			
		model.addAttribute("content", films);
		model.addAttribute("typeFilm", true);
		model.addAttribute("inputSearch", name);
		model.addAttribute("typeSearch", "/rest/busqueda/" + optionSearch +"/peliculas/" + name + "/page");
				
		if(films.getNumberOfElements() == 0) {
			model.addAttribute("noElementsSearch", true);
			model.addAttribute("noResult", true);
		}
		
		if(films.getNumberOfElements() > 0 && films.getNumberOfElements() < 10) {
			model.addAttribute("noElementsSearch", true);
		}
		
		return "search";
	}
	
	@RequestMapping("/busqueda/{optionSearch}/serie/{name}")
	public String searchShows(Model model, @PathVariable String optionSearch, @PathVariable String name) {		
		Page<Show> shows = showRepository.findByNameContainingIgnoreCase(name, new PageRequest(0, 10));
		
		model.addAttribute("content", shows);
		model.addAttribute("typeShow", true);
		model.addAttribute("inputSearch", name);
		model.addAttribute("typeSearch", "/rest/busqueda/" + optionSearch +"/series/" + name + "/page");
		
		if(shows.getNumberOfElements() == 0) {
			model.addAttribute("noElementsSearch", true);
			model.addAttribute("noResult", true);
		}
		
		if(shows.getNumberOfElements() > 0 && shows.getNumberOfElements() < 10) {
			model.addAttribute("noElementsSearch", true);
		}
		
		return "search";
	}
	
	@RequestMapping("/busqueda/{optionSearch}/libro/{name}")
	public String searchBooks(Model model, @PathVariable String optionSearch, @PathVariable String name) {		
		Page<Book> books = bookRepository.findByNameContainingIgnoreCase(name, new PageRequest(0, 10));
		
		model.addAttribute("content", books);		
		model.addAttribute("typeBook", true);
		model.addAttribute("inputSearch", name);
		model.addAttribute("typeSearch", "/rest/busqueda/" + optionSearch +"/libros/" + name + "/page");
		
		if(books.getNumberOfElements() == 0) {
			model.addAttribute("noElementsSearch", true);
			model.addAttribute("noResult", true);
		}
		
		if(books.getNumberOfElements() > 0 && books.getNumberOfElements() < 10) {
			model.addAttribute("noElementsSearch", true);
		}
		
		return "search";
	}

}
