package com.trackorjargh.javacontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.Lists;
import com.trackorjargh.javaclass.Show;
import com.trackorjargh.javarepository.BookRepository;
import com.trackorjargh.javarepository.FilmRepository;
import com.trackorjargh.javarepository.ListsRepository;
import com.trackorjargh.javarepository.ShowRepository;

@RestController
public class ApiRestController {

	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ShowRepository showRepository;
	@Autowired
	private ListsRepository listsRepository;
	
	@RequestMapping(value = "/rest/peliculas", method = RequestMethod.GET)
	@JsonView(Film.BasicInformation.class)
	public Page<Film> getPeliculas(Pageable page) {
		return filmRepository.findAll(page);
	}
	
	@RequestMapping(value = "/rest/libros", method = RequestMethod.GET)
	@JsonView(Book.BasicInformation.class)
	public Page<Book> getLibros(Pageable page) {
		return bookRepository.findAll(page);
	}
	
	@RequestMapping(value = "/rest/series", method = RequestMethod.GET)
	@JsonView(Show.BasicInformation.class)
	public Page<Show> getSeries(Pageable page) {
		return showRepository.findAll(page);
	}
	
	@RequestMapping(value = "/rest/busqueda/{optionSearch}/series/{name}/page", method = RequestMethod.GET)
	@JsonView(Show.BasicInformation.class)
	public Page<Show> getSearchSeries(Pageable page, @PathVariable String optionSearch, @PathVariable String name) {
		return showRepository.findByNameContainingIgnoreCase(name, page);
	}
	
	@RequestMapping(value = "/rest/busqueda/{optionSearch}/peliculas/{name}/page", method = RequestMethod.GET)
	@JsonView(Film.BasicInformation.class)
	public Page<Film> getSearchPeliculas(Pageable page, @PathVariable String optionSearch, @PathVariable String name) {
		return filmRepository.findByNameContainingIgnoreCase(name, page);
	}
	
	@RequestMapping(value = "/rest/busqueda/{optionSearch}/libros/{name}/page", method = RequestMethod.GET)
	@JsonView(Book.BasicInformation.class)
	public Page<Book> getSearchLibros(Pageable page, @PathVariable String optionSearch, @PathVariable String name) {
		return bookRepository.findByNameContainingIgnoreCase(name, page);
	}
	
	@RequestMapping(value = "/rest/agregarlista/{nameList}/{nameContent}", method = RequestMethod.PUT)
	public void addedListInUser(@PathVariable String nameList, @PathVariable String nameContent) {
		Lists listUser = listsRepository.findByName(nameList); 
		Film film = filmRepository.findByName(nameContent);
		
		listUser.getFilms().add(film);
		listsRepository.save(listUser);		
	}
}
