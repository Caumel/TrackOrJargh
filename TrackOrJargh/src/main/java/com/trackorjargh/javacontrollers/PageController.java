package com.trackorjargh.javacontrollers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trackorjargh.component.UserComponent;
import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.InterfaceMainItem;
import com.trackorjargh.javaclass.Show;
import com.trackorjargh.javaclass.User;
import com.trackorjargh.javarepository.BookRepository;
import com.trackorjargh.javarepository.FilmRepository;
import com.trackorjargh.javarepository.ShowRepository;
import com.trackorjargh.javarepository.UserRepository;
import com.trackorjargh.mail.MailComponent;

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
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private MailComponent mailComponent;

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
		model.addAttribute("content", filmRepository.findByName(name));
		model.addAttribute("directors", filmRepository.findByName(name).getDirectors());
		model.addAttribute("actors", filmRepository.findByName(name).getActors());
		model.addAttribute("comments", filmRepository.findByName(name).getCommentsFilm());
		model.addAttribute("typeContent", "la película");

		System.out.println(filmRepository.findByName(name).getCommentsFilm().size());
		return "contentProfile";
	}

	@RequestMapping("/serie/{name}")
	public String serveShowProfile(Model model, @PathVariable String name) {
		model.addAttribute("content", showRepository.findByName(name));
		model.addAttribute("directors", showRepository.findByName(name).getDirectors());
		model.addAttribute("actors", showRepository.findByName(name).getActors());
		model.addAttribute("comments", showRepository.findByName(name).getCommentsShow());
		model.addAttribute("typeContent", "la serie");
		model.addAttribute("episodeSection", true);

		return "contentProfile";
	}

	@RequestMapping("/libro/{name}")
	public String serveProfile(Model model, @PathVariable String name) {
		model.addAttribute("content", bookRepository.findByName(name));
		model.addAttribute("authors", bookRepository.findByName(name).getAuthors());
		model.addAttribute("comments", bookRepository.findByName(name).getCommentsBook());
		model.addAttribute("typeContent", "el libro");
		model.addAttribute("isBook", true);

		return "contentProfile";
	}

	@RequestMapping("/miperfil")
	public String serveUserProfile(Model model, @RequestParam Optional<String> emailUser,
			@RequestParam Optional<String> passUser, @RequestParam Optional<Boolean> sent) {
		if (sent.isPresent()) {
			if (emailUser.isPresent()) {
				userComponent.getLoggedUser().setEmail(emailUser.get());
			}
			if (!passUser.get().equals("")) {
				userComponent.getLoggedUser().setPassword(passUser.get());
			}
			userRepository.save(userComponent.getLoggedUser());
		}

		return "userProfile";
	}

	@RequestMapping("/guardarLogin")
	public void saveLogin(Model model, User user) {

	}

	@RequestMapping("/guardarRegister")
	public void saveRegister(Model model, User user) {

		model.addAttribute(user);

	}

	@RequestMapping("/activarusuario/{name}")
	public String activatedUser(Model model, @PathVariable String name) {
		User user = userRepository.findByName(name);

		if (user != null) {
			if (user.isActivatedUser()) {
				model.addAttribute("errorActivatedUser", true);
			} else {
				user.setActivatedUser(true);
				userRepository.save(user);

				model.addAttribute("activatedUser", true);
			}
		}

		return "login";
	}

	@RequestMapping("/login")
	public String serveLogin(Model model, HttpServletRequest request, @RequestParam Optional<String> name,
			@RequestParam Optional<String> email, @RequestParam Optional<String> pass,
			@RequestParam Optional<Boolean> registerUser) {
		if (registerUser.isPresent()) {
			User newUser = new User(name.get(), pass.get(), email.get(), "", false, "ROLE_USER");

			try {
				URL url = new URL(request.getRequestURL().toString());
				String urlRegister = "http://" + url.getHost() + ":" + url.getPort() + "/activarusuario/" + name.get();

				mailComponent.sendVerificationEmail(newUser, urlRegister);
			} catch (MalformedURLException exception) {
				exception.printStackTrace();
			}

			model.addAttribute("registered", true);
			userRepository.save(newUser);
		}

		return "login";
	}

	@RequestMapping("/olvidocontra")
	public String forgetPass(Model model) {
		return "recoverPass";
	}

	@RequestMapping("/loginerror")
	public String serveLoginError(Model model) {
		return "loginerror";
	}

	@RequestMapping("/error_te")
	public String paginaError(Model model) {

		return "error_template";

	}
}
