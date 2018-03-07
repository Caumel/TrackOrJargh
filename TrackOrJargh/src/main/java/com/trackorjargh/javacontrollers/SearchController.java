package com.trackorjargh.javacontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trackorjargh.component.UserComponent;
import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.Shows;
import com.trackorjargh.javaclass.User;
import com.trackorjargh.javarepository.BookRepository;
import com.trackorjargh.javarepository.FilmRepository;
import com.trackorjargh.javarepository.ShowRepository;
import com.trackorjargh.javarepository.UserRepository;

@Controller
public class SearchController {

	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ShowRepository showRepository;
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/busqueda")
	public String search(Model model) {	
		model.addAttribute("searchActive", true);
		model.addAttribute("noElementsSearch", true);
		model.addAttribute("index", true);
		model.addAttribute("loggedUserJS", userComponent.isLoggedUser());
		model.addAttribute("typePageAddList", "null");
		
		return "search";
	}
	
	@RequestMapping("/busqueda/{optionSearch}/pelicula/{name}")
	public String searchFilms(Model model, @PathVariable String optionSearch, @PathVariable String name) {
		Page<Film> films;

		if(optionSearch.equalsIgnoreCase("titulo")) {
			films = filmRepository.findByNameContainingIgnoreCase(name, new PageRequest(0, 10));
			model.addAttribute("searchTitle", true);
		} else {
			films = filmRepository.findFilmsByGender("%" + name + "%", new PageRequest(0, 10));
			model.addAttribute("searchGende", true);
		}
			
		model.addAttribute("search", name);
		model.addAttribute("searchActive", true);
		model.addAttribute("content", films);
		model.addAttribute("typeFilm", true);
		model.addAttribute("inputSearch", name);
		model.addAttribute("typeSearch", "/api/busqueda/" + optionSearch +"/peliculas/" + name + "/page");
		model.addAttribute("loggedUserJS", userComponent.isLoggedUser());
		model.addAttribute("typePageAddList", "pelicula");
		
		if(films.getNumberOfElements() == 0) {
			model.addAttribute("noElementsSearch", true);
			model.addAttribute("noResult", true);
		}
		
		if(films.getNumberOfElements() > 0 && films.getNumberOfElements() < 10) {
			model.addAttribute("noElementsSearch", true);
		}
		
		if (userComponent.isLoggedUser()) {
			User user = userRepository.findByNameIgnoreCase(userComponent.getLoggedUser().getName());

			model.addAttribute("userList", user.getLists());
		}
		
		return "search";
	}
	
	@RequestMapping("/busqueda/{optionSearch}/serie/{name}")
	public String searchShows(Model model, @PathVariable String optionSearch, @PathVariable String name) {		
		Page<Shows> shows;
		
		if(optionSearch.equalsIgnoreCase("titulo")) {
			shows = showRepository.findByNameContainingIgnoreCase(name, new PageRequest(0, 10));
			model.addAttribute("searchTitle", true);
		} else {
			shows = showRepository.findShowsByGender("%" + name + "%", new PageRequest(0, 10));
			model.addAttribute("searchGende", true);
		}
		
		model.addAttribute("search", name);
		model.addAttribute("searchActive", true);
		model.addAttribute("content", shows);
		model.addAttribute("typeShow", true);
		model.addAttribute("inputSearch", name);
		model.addAttribute("typeSearch", "/api/busqueda/" + optionSearch +"/series/" + name + "/page");
		model.addAttribute("loggedUserJS", userComponent.isLoggedUser());
		model.addAttribute("typePageAddList", "serie");
		
		if(shows.getNumberOfElements() == 0) {
			model.addAttribute("noElementsSearch", true);
			model.addAttribute("noResult", true);
		}
		
		if(shows.getNumberOfElements() > 0 && shows.getNumberOfElements() < 10) {
			model.addAttribute("noElementsSearch", true);
		}
		
		if (userComponent.isLoggedUser()) {
			User user = userRepository.findByNameIgnoreCase(userComponent.getLoggedUser().getName());

			model.addAttribute("userList", user.getLists());
		}
		
		return "search";
	}
	
	@RequestMapping("/busqueda/{optionSearch}/libro/{name}")
	public String searchBooks(Model model, @PathVariable String optionSearch, @PathVariable String name) {		
		Page<Book> books;
		
		if(optionSearch.equalsIgnoreCase("titulo")) {
			books = bookRepository.findByNameContainingIgnoreCase(name, new PageRequest(0, 10));
			model.addAttribute("searchTitle", true);
		} else {
			books = bookRepository.findBooksByGender("%" + name + "%", new PageRequest(0, 10));
			model.addAttribute("searchGende", true);
		}
		
		model.addAttribute("search", name);
		model.addAttribute("searchActive", true);
		model.addAttribute("content", books);		
		model.addAttribute("typeBook", true);
		model.addAttribute("inputSearch", name);
		model.addAttribute("typeSearch", "/api/busqueda/" + optionSearch +"/libros/" + name + "/page");
		model.addAttribute("loggedUserJS", userComponent.isLoggedUser());
		model.addAttribute("typePageAddList", "libro");
		
		if(books.getNumberOfElements() == 0) {
			model.addAttribute("noElementsSearch", true);
			model.addAttribute("noResult", true);
		}
		
		if(books.getNumberOfElements() > 0 && books.getNumberOfElements() < 10) {
			model.addAttribute("noElementsSearch", true);
		}
		
		if (userComponent.isLoggedUser()) {
			User user = userRepository.findByNameIgnoreCase(userComponent.getLoggedUser().getName());

			model.addAttribute("userList", user.getLists());
		}
		
		return "search";
	}

}
