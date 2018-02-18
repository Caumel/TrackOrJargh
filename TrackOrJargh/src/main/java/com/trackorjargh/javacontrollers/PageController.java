package com.trackorjargh.javacontrollers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.InterfaceMainItem;
import com.trackorjargh.javaclass.Show;
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
		List<InterfaceMainItem> listGeneric = new LinkedList<>();
		listGeneric.add(filmRepository.findById(filmRepository.findLastId()));
		listGeneric.add(showRepository.findById(showRepository.findLastId()));
		listGeneric.add(bookRepository.findById(bookRepository.findLastId()));
		listGeneric.get(0).setFirstInList(true);

		model.addAttribute("contentCarousel", listGeneric);
		model.addAttribute("indexActive", true);
		
		return "index";
	}

	@RequestMapping("/peliculas")
	public String serveFilmList(Model model) {
		List<Film> films = filmRepository.findByLastAdded(5);
		films.get(0).setFirstInList(true);

		model.addAttribute("content", filmRepository.findAll(new PageRequest(0, 10)));
		model.addAttribute("typePage", "peliculas");
		model.addAttribute("filmsActive", true);
		model.addAttribute("contentCarousel", films);

		return "contentList";
	}

	@RequestMapping("/series")
	public String serveShowList(Model model) {
		List<Show> shows = showRepository.findByLastAdded(5);
		shows.get(0).setFirstInList(true);

		model.addAttribute("content", showRepository.findAll(new PageRequest(0, 10)));
		model.addAttribute("typePage", "series");
		model.addAttribute("showsActive", true);
		model.addAttribute("contentCarousel", shows);

		return "contentList";
	}

	@RequestMapping("/libros")
	public String serveBookList(Model model) {
		List<Book> books = bookRepository.findByLastAdded(5);
		books.get(0).setFirstInList(true);

		model.addAttribute("content", bookRepository.findAll(new PageRequest(0, 10)));
		model.addAttribute("typePage", "libros");
		model.addAttribute("booksActive", true);
		model.addAttribute("contentCarousel", books);

		return "contentList";
	}

	@RequestMapping("/pelicula/{name}")
	public String serveFilmProfile(Model model, @PathVariable String name) {
		model.addAttribute("film", filmRepository.findByName(name));

		return "contentProfile";
	}

	@RequestMapping("/serie/{name}")
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
