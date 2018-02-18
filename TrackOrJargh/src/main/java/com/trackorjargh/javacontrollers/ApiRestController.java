package com.trackorjargh.javacontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.Show;
import com.trackorjargh.javarepository.BookRepository;
import com.trackorjargh.javarepository.FilmRepository;
import com.trackorjargh.javarepository.ShowRepository;

@RestController
public class ApiRestController {

	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ShowRepository showRepository;

	
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
}
