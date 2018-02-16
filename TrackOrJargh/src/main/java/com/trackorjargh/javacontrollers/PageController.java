package com.trackorjargh.javacontrollers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trackorjargh.javaclass.Actor;
import com.trackorjargh.javaclass.Author;
import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.CommentBook;
import com.trackorjargh.javaclass.CommentFilm;
import com.trackorjargh.javaclass.CommentShow;
import com.trackorjargh.javaclass.Director;
import com.trackorjargh.javaclass.Episode;
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.Gender;
import com.trackorjargh.javaclass.Lists;
import com.trackorjargh.javaclass.PointBook;
import com.trackorjargh.javaclass.PointFilm;
import com.trackorjargh.javaclass.PointShow;
import com.trackorjargh.javaclass.Season;
import com.trackorjargh.javaclass.Show;
import com.trackorjargh.javaclass.User;
import com.trackorjargh.javarepository.ActorRepository;
import com.trackorjargh.javarepository.AuthorRepository;
import com.trackorjargh.javarepository.BookRepository;
import com.trackorjargh.javarepository.CommentBookRepository;
import com.trackorjargh.javarepository.CommentFilmRepository;
import com.trackorjargh.javarepository.CommentShowRepository;
import com.trackorjargh.javarepository.DirectorRepository;
import com.trackorjargh.javarepository.EpisodeRepository;
import com.trackorjargh.javarepository.FilmRepository;
import com.trackorjargh.javarepository.GenderRepository;
import com.trackorjargh.javarepository.ListsRepository;
import com.trackorjargh.javarepository.PointBookRepository;
import com.trackorjargh.javarepository.PointFilmRepository;
import com.trackorjargh.javarepository.PointShowRepository;
import com.trackorjargh.javarepository.SeasonRepository;
import com.trackorjargh.javarepository.ShowRepository;
import com.trackorjargh.javarepository.UserRepository;

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
	@Autowired
	private GenderRepository genderRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShowRepository showRepository;
	@Autowired
	private SeasonRepository seasonRepository;
	@Autowired
	private EpisodeRepository episodeRepository;
	@Autowired
	private ListsRepository listsRepository;
	@Autowired
	private CommentFilmRepository commentFilmRepository;
	@Autowired
	private CommentBookRepository commentBookRepository;
	@Autowired
	private CommentShowRepository commentShowRepository;
	@Autowired
	private PointFilmRepository pointFilmRepository;
	@Autowired
	private PointBookRepository pointBookRepository;
	@Autowired
	private PointShowRepository pointShowRepository;
	
	
	@PostConstruct
	public void init() {
		//Test Data User
<<<<<<< HEAD
		User u1 = new User("Oscar", "1234", "oscarmola@gmail.com", "", "Usuario");
		userRepository.save(u1);
=======
>>>>>>> 8696be9e1f208627cb1e593adec614df392e8370
		
		//Test Data Actor
		Actor a1 = new Actor("Chiss", "Patt", 1979);
		actorRepository.save(a1);
		
		//Test Data Director
		Director d1 = new Director("James", "Gunn", 1970);
		directorRepository.save(d1);
		
		//Test Data Author
		Author au1 = new Author("Suzanne", "Collins", 1964);
		authorRepository.save(au1);
		
		//Test Data Gender
		Gender g1 = new Gender("Aventuras");
		genderRepository.save(g1);
		
		//Test Data Film
		Film f1 = new Film("Guardianes de la Galaxia 2", "Guardianes de la Galaxia Vol. 2 continúa las aventuras del equipo a medida que viajan cruzando\n los confines del cosmos. Los Guardianes tendrán que luchar para\n mantener su recién...", "img/Guardianes2.jpg", "https://www.youtube.com/embed/12gvJgLE4us?rel=0&amp;controls=0&amp;showinfo=0", 2017);
		f1.getActors().add(a1);
		f1.getDirectors().add(d1);
		f1.getGenders().add(g1);
		filmRepository.save(f1);
	
		//Test Data Book
		Book b1 = new Book("Los Juegos del Hambre", "Los juegos del hambre se desarrolla en un país llamado Panem, lo que es en realidad una civilización postapocalíptica ubicada en lo que antes era América del Norte.", "", 2008);
		b1.getAuthors().add(au1);
		b1.getGenders().add(g1);
		bookRepository.save(b1);
		
		//Test Data Episodie
		Episode ep1 = new Episode("Episodio 1");
		episodeRepository.save(ep1);
		
		//Test Data Season
		Season s1 = new Season("Temporada 1");
		s1.getEpisodes().add(ep1);
		seasonRepository.save(s1);
		
		//Test Data Show
		Show sh1 = new Show("The Big Bang Theory", "La serie comienza con la llegada de Penny, aspirante a actriz, al apartamento vecino, que comparten Sheldon y Leonard, dos físicos que trabajan en el Instituto Tecnológico de California (Caltech). Leonard se enamora desde el primer momento de Penny.", "", "", 2007);
		sh1.getSeasons().add(s1);
		showRepository.save(sh1);
		
		//Test Data Lists
		Lists l1 = new Lists("Mi Lista Molona");
		l1.getFimls().add(f1);
		l1.getBooks().add(b1);
		l1.getShows().add(sh1);
		listsRepository.save(l1);
		
		//Test Data Comment Film
		CommentFilm cf1 = new CommentFilm("Esta pelicula es muy buena");
		cf1.setFilm(f1);
		commentFilmRepository.save(cf1);
		
		//Test Data Comment Show
		CommentShow cs1 = new CommentShow("Esta serie es muy buena");
		cs1.setShow(sh1);
		cs1.setUser(u1);
		cs1.setUser(users);
		commentShowRepository.save(cs1);
		
		//Test Data Comment Book
		CommentBook cb1 = new CommentBook("Este libro es muy bueno");
		cb1.setBook(b1);
		cb1.setUser(u1);
		cb1.setUser(users);
		commentBookRepository.save(cb1);
		
		//Test Data Point Film
		PointFilm pf1 = new PointFilm((long) 5);
		pf1.setFilm(f1);
		pf1.setUser(u1);
		pf1.setUser(users);
		pointFilmRepository.save(pf1);
		
		//Test Data Point Show
		PointShow ps1 = new PointShow((long) 5);
		ps1.setShow(sh1);
		ps1.setUser(u1);
		ps1.setUser(users);
		pointShowRepository.save(ps1);
		
		//Test Data Point Book
		PointBook pb1 = new PointBook((long) 5);
		pb1.setBook(b1);
		pb1.setUser(u1);
		pb1.setUser(users);
		pointBookRepository.save(pb1);
	}

	@RequestMapping("/")
		
		//slides.get(0).setFirstInList(true);
		//model.addAttribute("slide", slides);
		
		return "index";
	}
	
	@RequestMapping("/libros")
	public String serverBookList(Model model) {
		model.addAttribute("content", bookRepository.findAll());
		return "contentList";
	}
	@RequestMapping("/series")
	public String serverShowList(Model model) {
		model.addAttribute("shows", showRepository.findAll());
	@RequestMapping("/contentList")
	public String serveList(Model model) {
		
		return "contentList";
	}
	@RequestMapping("/peliculas")
	public String serveFilmList(Model model) {
		model.addAttribute("content", filmRepository.findAll());
		//films.get(0).setFirstInList(true);
		//model.addAttribute("filmsCarousel", films);
		//model.addAttribute("films", films);
		
		return "contentList";
	}
	
	@RequestMapping("/contentProfile/{id}")
	public String serveProfile(Model model, @PathVariable Long id) {
		
		return "contentProfile";
	}
	
	@RequestMapping("/userProfile/{nickname}")
	public String serveUserProfile(Model model, @PathVariable String nickname) {
		User user = userRepository.findByName(nickname);
		model.addAttribute("user", user);
		return "userProfile";
	}
	
	public String serveLogin(Model model) {
		
		
		return "login";
	}
}
