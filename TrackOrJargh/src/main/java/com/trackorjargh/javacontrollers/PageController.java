package com.trackorjargh.javacontrollers;

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
import com.trackorjargh.javaclass.CommentBook;
import com.trackorjargh.javaclass.CommentFilm;
import com.trackorjargh.javaclass.CommentShow;
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.ForgotPassword;
import com.trackorjargh.javaclass.GenerateURLPage;
import com.trackorjargh.javaclass.InterfaceMainItem;
import com.trackorjargh.javaclass.Lists;
import com.trackorjargh.javaclass.PointFilm;
import com.trackorjargh.javaclass.PreparateMessageShow;
import com.trackorjargh.javaclass.RandomGenerate;
import com.trackorjargh.javaclass.Show;
import com.trackorjargh.javaclass.User;
import com.trackorjargh.javarepository.BookRepository;
import com.trackorjargh.javarepository.CommentBookRepository;
import com.trackorjargh.javarepository.CommentFilmRepository;
import com.trackorjargh.javarepository.CommentShowRepository;
import com.trackorjargh.javarepository.FilmRepository;
import com.trackorjargh.javarepository.ForgotPasswordRepository;
import com.trackorjargh.javarepository.ListsRepository;
import com.trackorjargh.javarepository.PointBookRepository;
import com.trackorjargh.javarepository.PointFilmRepository;
import com.trackorjargh.javarepository.PointShowRepository;
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
	private CommentFilmRepository commentFilmRepository;
	@Autowired
	private CommentShowRepository commentShowRepository;
	@Autowired
	private CommentBookRepository commentBookRepository;
	@Autowired
	private PointFilmRepository pointFilmRepository;
	@Autowired
	private PointShowRepository pointShowRepository;
	@Autowired
	private PointBookRepository pointBookRepository;
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private MailComponent mailComponent;
	@Autowired
	private ForgotPasswordRepository forgotPasswordRepository;
	@Autowired
	private ListsRepository listsRepository;

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
	public String serveFilmProfile(Model model, @PathVariable String name, @RequestParam Optional<String> messageSent,
			@RequestParam Optional<String> pointsSent) {
		Film film = filmRepository.findByName(name);

		if (messageSent.isPresent()) {
			CommentFilm message = new CommentFilm(messageSent.get());
			message.setFilm(film);
			message.setUser(userComponent.getLoggedUser());

			commentFilmRepository.save(message);
		}

		if (pointsSent.isPresent()) {
			PointFilm pointFilm = pointFilmRepository.findByUser(userComponent.getLoggedUser());

			if (pointFilm == null) {
				pointFilm = new PointFilm(Long.parseLong(pointsSent.get().replaceAll("\"", "")));
				pointFilm.setFilm(film);
				pointFilm.setUser(userComponent.getLoggedUser());
			} else {
				pointFilm.setPoints(Long.parseLong(pointsSent.get().replaceAll("\"", "")));
			}

			pointFilmRepository.save(pointFilm);
		}

		List<PreparateMessageShow> listMessages = new LinkedList<>();
		for (CommentFilm cf : film.getCommentsFilm())
			listMessages.add(cf.preparateShowMessage());

		model.addAttribute("content", film);
		model.addAttribute("comments", listMessages);
		model.addAttribute("typeContent", "la película");
		model.addAttribute("actionMessage", "/pelicula/" + name);

		int points = 0;
		List<PointFilm> listPoints = pointFilmRepository.findAll();

		if (listPoints.size() > 0) {
			for (PointFilm pf : listPoints)
				points += pf.getPoints();
			points /= listPoints.size();
		}

		model.addAttribute("totalPoints", points);

		return "contentProfile";
	}

	@RequestMapping("/serie/{name}")
	public String serveShowProfile(Model model, @PathVariable String name, @RequestParam Optional<String> messageSent) {
		Show show = showRepository.findByName(name);

		if (messageSent.isPresent()) {
			CommentShow message = new CommentShow(messageSent.get());
			message.setShow(show);
			message.setUser(userComponent.getLoggedUser());

			commentShowRepository.save(message);
		}

		List<PreparateMessageShow> listMessages = new LinkedList<>();
		for (CommentShow ch : show.getCommentsShow())
			listMessages.add(ch.preparateShowMessage());

		model.addAttribute("content", show);
		model.addAttribute("comments", listMessages);
		model.addAttribute("firstSeason", showRepository.findByName(name).getFirstSeason());
		model.addAttribute("listNoFirstSeason", showRepository.findByName(name).getListNoFirst());
		model.addAttribute("typeContent", "la serie");
		model.addAttribute("episodeSection", true);
		model.addAttribute("actionMessage", "/serie/" + name);
		
		int points = 0;
		model.addAttribute("totalPoints", points);

		return "contentProfile";
	}

	@RequestMapping("/libro/{name}")
	public String serveProfile(Model model, @PathVariable String name, @RequestParam Optional<String> messageSent) {
		Book book = bookRepository.findByName(name);

		if (messageSent.isPresent()) {
			CommentBook message = new CommentBook(messageSent.get());
			message.setBook(book);
			message.setUser(userComponent.getLoggedUser());

			commentBookRepository.save(message);
		}

		List<PreparateMessageShow> listMessages = new LinkedList<>();
		for (CommentBook cb : book.getCommentsBook())
			listMessages.add(cb.preparateShowMessage());

		model.addAttribute("content", bookRepository.findByName(name));
		model.addAttribute("comments", listMessages);
		model.addAttribute("typeContent", "el libro");
		model.addAttribute("isBook", true);
		model.addAttribute("actionMessage", "/libro/" + name);
		
		int points = 0;
		model.addAttribute("totalPoints", points);

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

	// create empty list
	@RequestMapping("/listaNueva")
	public String modProfile(Model model, @RequestParam String listName) {
		listsRepository.save(new Lists(listName));
		// model.addAttribute("visibleCarousel", true);
		return "userProfile";
	}

	// @RequestMapping(/"borrarLista")
	// public String modProfile(Model model)

	@RequestMapping("/activarusuario/{name}")
	public String activatedUser(Model model, @PathVariable String name) {
		User user = userRepository.findByName(name);

		if (user != null) {
			if (user.isActivatedUser()) {
				model.addAttribute("errorActivatedUser", true);
			} else {
				user.setActivatedUser(true);
				userRepository.save(user);

				model.addAttribute("viewUser", true);
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
			GenerateURLPage url = new GenerateURLPage(request);
			mailComponent.sendVerificationEmail(newUser, url.generateURLActivateAccount(newUser));

			model.addAttribute("registered", true);
			userRepository.save(newUser);
		}

		return "login";
	}

	@RequestMapping("/cambiarcontra/{alphanumericCode}")
	public String changePass(Model model, HttpServletRequest request, @PathVariable String alphanumericCode,
			@RequestParam Optional<String> pass) {
		ForgotPassword forgotPass = forgotPasswordRepository.findBySecretAlphanumeric(alphanumericCode);

		if (forgotPass == null) {
			model.addAttribute("wrongCode", true);

			return "recoverPass";
		}

		User user = forgotPass.getUser();

		if (pass.isPresent()) {
			user.setPassword(pass.get());
			userRepository.save(user);
			forgotPasswordRepository.delete(forgotPass);

			model.addAttribute("changedPass", true);
			model.addAttribute("viewUser", true);
			model.addAttribute("name", user.getName());

			return "login";
		} else {
			model.addAttribute("user", user);

			return "changePass";
		}
	}

	@RequestMapping("/olvidocontra")
	public String forgetPass(Model model, HttpServletRequest request, @RequestParam Optional<String> email) {
		if (email.isPresent()) {
			User user = userRepository.findByEmail(email.get());

			if (user != null) {
				ForgotPassword forgotPass = forgotPasswordRepository.findByUser(user);
				if (forgotPass != null) {
					model.addAttribute("sentEmail", true);

					return "recoverPass";
				}

				RandomGenerate generateRandomString = new RandomGenerate();
				ForgotPassword newForgotPass = new ForgotPassword(generateRandomString.getRandomString(12));
				newForgotPass.setUser(user);
				forgotPasswordRepository.save(newForgotPass);

				GenerateURLPage url = new GenerateURLPage(request);
				mailComponent.sendChangePassEmail(user, url.generateURLChangePass(newForgotPass));

				model.addAttribute("sentChangePass", true);
				return "login";
			} else {
				model.addAttribute("wrongEmail", true);
				return "recoverPass";
			}
		} else {
			model.addAttribute("changePass", true);
			return "recoverPass";
		}
	}

	@RequestMapping("/error/{message}/{name}")
	public String serveLoginError(Model model, @PathVariable String message, @PathVariable String name) {
		switch (message) {
		case "noexiste":
			model.addAttribute("errorUser", true);
			break;
		case "errorcontra":
			model.addAttribute("errorWrongPass", true);
			model.addAttribute("viewUser", true);
			break;
		case "noactivado":
			model.addAttribute("errorNotActivatedUser", true);
			model.addAttribute("viewUser", true);
			break;
		}

		return "login";
	}

	@RequestMapping("/administracion")
	public String serveAdmin(Model model) {
		return "administration";
	}

	@RequestMapping("/adminUsuario")
	public String adminUser(Model model, @RequestParam String name, @RequestParam String email,
			@RequestParam Boolean confirmDelete, @RequestParam String deleteUser, @RequestParam String userType) {
		User user = userRepository.findByName(name);
		if (confirmDelete) {
			if (name.equals(deleteUser)) {

			}
		} else {
			user.setEmail(email);
			if (userType.equals("Usuario")) {
				user.setRoles(new LinkedList<String>());
				user.getRoles().add("ROLE_USER");
			} else {
				if (userType.equals("Moderador")) {
					user.getRoles().add("ROLE_MODERATOR");
				} else {
					user.getRoles().add("ROLE_MODERATOR");
					user.getRoles().add("ROLE_ADMINISTRATOR");
				}
			}
		}
		return "redirect:/administracion";
	}

	@RequestMapping("/adminPelis")
	public String adminFilms(Model model) {
		model.addAttribute("content", filmRepository.findByName("Guardianes de la galaxia 2"));

		return "redirect:/administracion";
	}
}
