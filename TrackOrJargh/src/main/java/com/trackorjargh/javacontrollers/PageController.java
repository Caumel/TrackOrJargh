package com.trackorjargh.javacontrollers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trackorjargh.javaclass.Actor;
import com.trackorjargh.javaclass.Author;
import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.Director;
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javarepository.ActorRepository;
import com.trackorjargh.javarepository.AuthorRepository;
import com.trackorjargh.javarepository.BookRepository;
import com.trackorjargh.javarepository.DirectorRepository;
import com.trackorjargh.javarepository.FilmRepository;

@Controller
public class PageController {

	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private ActorRepository actorRepository;
	@Autowired
	private DirectorRepository directorRepository;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;
	
	
	@PostConstruct
	public void init() {
		Actor a1 = new Actor("Chiss", "Patt", 1979);
		actorRepository.save(a1);
		
		Director d1 = new Director("James", "Gunn", 1970);
		directorRepository.save(d1);
		
		Film f1 = new Film("Guardianes de la Galaxia 2", "Guardianes de la Galaxia Vol. 2 continúa las aventuras del equipo a medida que viajan cruzando\n los confines del cosmos. Los Guardianes tendrán que luchar para\n mantener su recién...", "img/Guardianes2.jpg", "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0", 2017);
		f1.getActors().add(a1);
		f1.getDirectors().add(d1);
		filmRepository.save(f1);
		
		Author au1 = new Author("Suzanne", "Collins", 1964);
		authorRepository.save(au1);
		
		Book b1 = new Book("Los Juegos del Hambre", "Los juegos del hambre se desarrolla en un país llamado Panem, lo que es en realidad una civilización postapocalíptica ubicada en lo que antes era América del Norte.", "", 2008);
		b1.getAuthors().add(au1);
		bookRepository.save(b1);
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
