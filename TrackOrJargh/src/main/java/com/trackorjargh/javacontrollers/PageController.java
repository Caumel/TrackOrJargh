package com.trackorjargh.javacontrollers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
import com.trackorjargh.javaclass.PointBook;
import com.trackorjargh.javaclass.PointFilm;
import com.trackorjargh.javaclass.PointShow;
import com.trackorjargh.javaclass.PreparateListsShow;
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
import com.trackorjargh.javarepository.GenderRepository;
import com.trackorjargh.javarepository.ListsRepository;
import com.trackorjargh.javarepository.PointBookRepository;
import com.trackorjargh.javarepository.PointFilmRepository;
import com.trackorjargh.javarepository.PointShowRepository;
import com.trackorjargh.javarepository.ShowRepository;
import com.trackorjargh.javarepository.UserRepository;
import com.trackorjargh.mail.MailComponent;
import com.trackorjargh.pdf.PdfCreate;

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
	private GenderRepository genderRepository;
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
	@Autowired
	private PdfCreate pdfCreate;

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

	@RequestMapping(value = { "/peliculas", "/peliculas/mejorvaloradas" })
	public String serveFilmList(Model model, HttpServletRequest request) {
		List<Film> films = filmRepository.findByLastAdded(5);
		films.get(0).setFirstInList(true);

		if (userComponent.isLoggedUser()) {
			User user = userRepository.findByName(userComponent.getLoggedUser().getName());

			model.addAttribute("userList", user.getLists());
		}

		model.addAttribute("content", filmRepository.findAll(new PageRequest(0, 10)));
		model.addAttribute("typePage", "peliculas");

		Page<Film> filmsPage;
		String typePage;
		if (request.getServletPath().equalsIgnoreCase("/peliculas")) {
			filmsPage = filmRepository.findAll(new PageRequest(0, 10));
			model.addAttribute("contentShowButton", true);
			typePage = "/rest/peliculas";
		} else {
			filmsPage = filmRepository.findBestPointFilm(new PageRequest(0, 10));
			model.addAttribute("bestPointContentShowButton", true);
			typePage = "/rest/peliculas/mejorvaloradas";
		}

		if (filmsPage.getNumberOfElements() > 0 && filmsPage.getNumberOfElements() < 10) {
			model.addAttribute("noElementsSearch", true);
		}

		model.addAttribute("linkContent", "/peliculas");
		model.addAttribute("linkBestPointContent", "/peliculas/mejorvaloradas");
		model.addAttribute("content", filmsPage);
		model.addAttribute("typePage", typePage);
		model.addAttribute("filmsActive", true);
		model.addAttribute("contentCarousel", films);
		model.addAttribute("loggedUserJS", userComponent.isLoggedUser());
		model.addAttribute("typePageAddList", "pelicula");

		return "contentList";
	}

	@RequestMapping({ "/series", "/series/mejorvaloradas" })
	public String serveShowList(Model model, HttpServletRequest request) {
		List<Show> shows = showRepository.findByLastAdded(5);
		shows.get(0).setFirstInList(true);

		if (userComponent.isLoggedUser()) {
			User user = userRepository.findByName(userComponent.getLoggedUser().getName());

			model.addAttribute("userList", user.getLists());
		}

		Page<Show> showsPage;
		String typePage;
		if (request.getServletPath().equalsIgnoreCase("/series")) {
			showsPage = showRepository.findAll(new PageRequest(0, 10));
			model.addAttribute("contentShowButton", true);
			typePage = "/rest/series";
		} else {
			showsPage = showRepository.findBestPointShow(new PageRequest(0, 10));
			model.addAttribute("bestPointContentShowButton", true);
			typePage = "/rest/series/mejorvaloradas";
		}

		if (showsPage.getNumberOfElements() > 0 && showsPage.getNumberOfElements() < 10) {
			model.addAttribute("noElementsSearch", true);
		}

		model.addAttribute("content", showRepository.findAll(new PageRequest(0, 10)));
		model.addAttribute("linkContent", "/series");
		model.addAttribute("linkBestPointContent", "/series/mejorvaloradas");
		model.addAttribute("content", showsPage);
		model.addAttribute("typePage", typePage);
		model.addAttribute("showsActive", true);
		model.addAttribute("contentCarousel", shows);
		model.addAttribute("loggedUserJS", userComponent.isLoggedUser());
		model.addAttribute("typePageAddList", "serie");

		return "contentList";
	}

	@RequestMapping({ "/libros", "/libros/mejorvalorados" })
	public String serveBookList(Model model, HttpServletRequest request) {
		List<Book> books = bookRepository.findByLastAdded(5);
		books.get(0).setFirstInList(true);

		if (userComponent.isLoggedUser()) {
			User user = userRepository.findByName(userComponent.getLoggedUser().getName());

			model.addAttribute("userList", user.getLists());
		}

		Page<Book> booksPage;
		String typePage;
		if (request.getServletPath().equalsIgnoreCase("/libros")) {
			booksPage = bookRepository.findAll(new PageRequest(0, 10));
			model.addAttribute("contentShowButton", true);
			typePage = "/rest/libros";
		} else {
			booksPage = bookRepository.findBestPointBook(new PageRequest(0, 10));
			model.addAttribute("bestPointContentShowButton", true);
			typePage = "/rest/libros/mejorvalorados";
		}

		if (booksPage.getNumberOfElements() > 0 && booksPage.getNumberOfElements() < 10) {
			model.addAttribute("noElementsSearch", true);
		}

		model.addAttribute("content", bookRepository.findAll(new PageRequest(0, 10)));
		model.addAttribute("linkContent", "/libros");
		model.addAttribute("linkBestPointContent", "/libros/mejorvalorados");
		model.addAttribute("content", booksPage);
		model.addAttribute("typePage", typePage);
		model.addAttribute("booksActive", true);
		model.addAttribute("contentCarousel", books);
		model.addAttribute("loggedUserJS", userComponent.isLoggedUser());
		model.addAttribute("typePageAddList", "libro");

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
			double points = Double.parseDouble(pointsSent.get());
			PointFilm pointFilm = pointFilmRepository.findByUserAndFilm(userComponent.getLoggedUser(), film);

			if (pointFilm == null) {
				pointFilm = new PointFilm(points);
				pointFilm.setFilm(film);
				pointFilm.setUser(userComponent.getLoggedUser());
			} else {
				pointFilm.setPoints(points);
			}

			pointFilmRepository.save(pointFilm);
		}

		List<PreparateMessageShow> listMessages = new LinkedList<>();
		for (CommentFilm cf : commentFilmRepository.findByFilm(film))
			listMessages.add(cf.preparateShowMessage());

		model.addAttribute("content", film);
		model.addAttribute("comments", listMessages);
		model.addAttribute("typeContent", "la película");
		model.addAttribute("actionMessage", "/pelicula/" + name);

		double points = 0;
		double userPoints = 0;

		List<PointFilm> listPoints = pointFilmRepository.findByFilm(film);

		if (listPoints.size() > 0) {
			for (PointFilm pf : listPoints)
				points += pf.getPoints();
			points /= listPoints.size();
		}

		PointFilm userPointFilm = pointFilmRepository.findByUserAndFilm(userComponent.getLoggedUser(), film);
		if (userPointFilm != null)
			if (userPointFilm != null)
				userPoints = userPointFilm.getPoints();

		model.addAttribute("totalPoints", points);
		model.addAttribute("userPoints", userPoints);

		model.addAttribute("contentRelation",
				filmRepository.findFilmsRelationsById(film.getId(), new PageRequest(0, 8)));
		model.addAttribute("iconFilmShow", true);

		return "contentProfile";
	}

	@RequestMapping("/serie/{name}")
	public String serveShowProfile(Model model, @PathVariable String name, @RequestParam Optional<String> messageSent,
			@RequestParam Optional<String> pointsSent) {
		Show show = showRepository.findByName(name);

		if (messageSent.isPresent()) {
			CommentShow message = new CommentShow(messageSent.get());
			message.setShow(show);
			message.setUser(userComponent.getLoggedUser());

			commentShowRepository.save(message);
		}

		if (pointsSent.isPresent()) {
			double points = Double.parseDouble(pointsSent.get());
			PointShow pointShow = pointShowRepository.findByUserAndShow(userComponent.getLoggedUser(), show);

			if (pointShow == null) {
				pointShow = new PointShow(points);
				pointShow.setShow(show);
				pointShow.setUser(userComponent.getLoggedUser());
			} else {
				pointShow.setPoints(points);
			}

			pointShowRepository.save(pointShow);
		}

		List<PreparateMessageShow> listMessages = new LinkedList<>();
		for (CommentShow ch : commentShowRepository.findByShow(show))
			listMessages.add(ch.preparateShowMessage());

		model.addAttribute("content", show);
		model.addAttribute("comments", listMessages);
		model.addAttribute("typeContent", "la serie");
		model.addAttribute("episodeSection", true);
		model.addAttribute("actionMessage", "/serie/" + name);

		double points = 0;
		double userPoints = 0;

		List<PointShow> listPoints = pointShowRepository.findByShow(show);

		if (listPoints.size() > 0) {
			for (PointShow ph : listPoints)
				points += ph.getPoints();
			points /= listPoints.size();
		}

		PointShow userPointShow = pointShowRepository.findByUserAndShow(userComponent.getLoggedUser(), show);
		if (userPointShow != null)
			if (userPointShow != null)
				userPoints = userPointShow.getPoints();

		model.addAttribute("totalPoints", points);
		model.addAttribute("userPoints", userPoints);

		model.addAttribute("contentRelation",
				showRepository.findShowsRelationsById(show.getId(), new PageRequest(0, 8)));
		model.addAttribute("iconFilmShow", true);

		return "contentProfile";
	}

	@RequestMapping("/libro/{name}")
	public String serveProfile(Model model, @PathVariable String name, @RequestParam Optional<String> messageSent,
			@RequestParam Optional<String> pointsSent) {
		Book book = bookRepository.findByName(name);

		if (messageSent.isPresent()) {
			CommentBook message = new CommentBook(messageSent.get());
			message.setBook(book);
			message.setUser(userComponent.getLoggedUser());

			commentBookRepository.save(message);
		}

		if (pointsSent.isPresent()) {
			double points = Double.parseDouble(pointsSent.get());
			PointBook pointBook = pointBookRepository.findByUserAndBook(userComponent.getLoggedUser(), book);

			if (pointBook == null) {
				pointBook = new PointBook(points);
				pointBook.setBook(book);
				pointBook.setUser(userComponent.getLoggedUser());
			} else {
				pointBook.setPoints(points);
			}

			pointBookRepository.save(pointBook);
		}

		List<PreparateMessageShow> listMessages = new LinkedList<>();
		for (CommentBook cb : commentBookRepository.findByBook(book))
			listMessages.add(cb.preparateShowMessage());

		model.addAttribute("content", bookRepository.findByName(name));
		model.addAttribute("comments", listMessages);
		model.addAttribute("typeContent", "el libro");
		model.addAttribute("isBook", true);
		model.addAttribute("actionMessage", "/libro/" + name);

		double points = 0;
		double userPoints = 0;

		List<PointBook> listPoints = pointBookRepository.findByBook(book);

		if (listPoints.size() > 0) {
			for (PointBook pb : listPoints)
				points += pb.getPoints();
			points /= listPoints.size();
		}

		PointBook userPointBook = pointBookRepository.findByUserAndBook(userComponent.getLoggedUser(), book);
		if (userPointBook != null)
			if (userPointBook != null)
				userPoints = userPointBook.getPoints();

		model.addAttribute("totalPoints", points);
		model.addAttribute("userPoints", userPoints);

		model.addAttribute("contentRelation",
				bookRepository.findBooksRelationsById(book.getId(), new PageRequest(0, 8)));
		model.addAttribute("iconBook", true);

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
		
		List<PreparateListsShow> listsUser = new LinkedList<>();
		for (Lists list : listsRepository.findByUser(userComponent.getLoggedUser()))
			listsUser.add(new PreparateListsShow(list.getName(), list.getFilms(), list.getBooks(), list.getShows()));
		
		model.addAttribute("listsUser", listsUser);

		if (userComponent.getLoggedUser().getRoles().size() == 3) {
			model.addAttribute("isAdmin", true);
		} else {
			if (userComponent.getLoggedUser().getRoles().size() == 2) {
				model.addAttribute("isModerator", true);
			}
		}
		
		//Create PDF
		pdfCreate.createPdfLists(userComponent.getLoggedUser(), listsRepository.findByUser(userComponent.getLoggedUser()));

		model.addAttribute("myProfile", true);
		return "userProfile";
	}

	// create empty list
	@RequestMapping("/listaNueva")
	public String modProfile(Model model, @RequestParam String listName) {
		Lists listUser = new Lists(listName);
		listUser.setUser(userComponent.getLoggedUser());
		listsRepository.save(listUser);

		return "redirect:/miperfil";
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

	@RequestMapping("/seleccionarUsuario")
	public ModelAndView userSelection(RedirectAttributes redir, @RequestParam String name) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/administracion");
		User user = userRepository.findByName(name);
		if (user.getRoles().size() == 3) {
			redir.addFlashAttribute("isAdmin", true);
		} else {
			if (user.getRoles().size() == 2) {
				redir.addFlashAttribute("isModerator", true);
			}
		}
		redir.addFlashAttribute("adminUser", true);
		redir.addFlashAttribute("user", user);
		return modelAndView;
	}

	@RequestMapping("/seleccionarPelicula")
	public ModelAndView filmSelection(RedirectAttributes redir, @RequestParam String name) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/administracion");
		Film film = filmRepository.findByName(name);
		redir.addFlashAttribute("adminFilm", true);
		redir.addFlashAttribute("film", film);
		redir.addFlashAttribute("genres", genderRepository.findByFilms(film));
		redir.addFlashAttribute("genresNotInFilm", genderRepository.findByNotInFilm(film.getId()));

		return modelAndView;
	}

	@RequestMapping("/seleccionarSerie")
	public ModelAndView showSelection(RedirectAttributes redir, @RequestParam String name) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/administracion");
		Show show = showRepository.findByName(name);
		redir.addFlashAttribute("adminShow", true);
		redir.addFlashAttribute("show", show);
		redir.addFlashAttribute("genres", genderRepository.findByShows(show));
		redir.addFlashAttribute("genresNotInShow", genderRepository.findByNotInShow(show.getId()));

		return modelAndView;
	}

	@RequestMapping("/seleccionarLibro")
	public ModelAndView bookSelection(RedirectAttributes redir, @RequestParam String name) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/administracion");
		Book book = bookRepository.findByName(name);
		redir.addFlashAttribute("adminBook", true);
		redir.addFlashAttribute("book", book);
		redir.addFlashAttribute("genres", genderRepository.findByBooks(book));
		redir.addFlashAttribute("genresNotInBook", genderRepository.findByNotInBook(book.getId()));

		return modelAndView;
	}

	@RequestMapping("/adminUsuario")
	public String adminUser(Model model, @RequestParam String name, @RequestParam String email,
			@RequestParam Optional<Boolean> confirmDelete, @RequestParam String deleteUser,
			@RequestParam String userType) {
		User user = userRepository.findByName(name);
		if (confirmDelete.isPresent() && confirmDelete.get()) {
			if (name.equals(deleteUser)) {

			}
		} else {
			user.setEmail(email);
			user.setRoles(new LinkedList<String>());
			if (userType.equals("Usuario")) {
				user.setRoles(new LinkedList<String>());
				user.getRoles().add("ROLE_USER");
			} else {
				if (userType.equals("Moderador")) {
					user.getRoles().add("ROLE_USER");
					user.getRoles().add("ROLE_MODERATOR");
				} else {
					user.getRoles().add("ROLE_USER");
					user.getRoles().add("ROLE_MODERATOR");
					user.getRoles().add("ROLE_ADMINISTRATOR");
				}
			}
			userRepository.save(user);
			userComponent.setLoggedUser(user);
		}

		return "redirect:/administracion";
	}

	@RequestMapping("/adminPelicula")
	public String adminFilm(Model model, @RequestParam String name, @RequestParam String newName,
			@RequestParam Optional<Boolean> confirmDelete, @RequestParam Optional<String> deleteFilm,
			@RequestParam String actors, @RequestParam String directors, @RequestParam String imageFilm,
			@RequestParam Optional<String[]> genreContent, @RequestParam Optional<String[]> newGenres,
			@RequestParam String synopsis, @RequestParam String trailer, @RequestParam String year) { // AQUI
		Film film = filmRepository.findByName(name);
		if (confirmDelete.isPresent() && confirmDelete.get()) {
			if (name.equals(deleteFilm.get())) {

			}
		} else {
			film.setName(newName);
			film.setActors(actors);
			film.setDirectors(directors);
			film.setSynopsis(synopsis);
			film.setTrailer(trailer);
			int yearInt = Integer.parseInt(year);
			film.setYear(yearInt);
		}
		if (genreContent.isPresent()) {
			for (String genre : genreContent.get()) {
				film.getGenders().clear();
				film.getGenders().add(genderRepository.findByName(genre));
			}
		}
		if (newGenres.isPresent()) {
			for (String genre : newGenres.get()) {
				film.getGenders().add(genderRepository.findByName(genre));
			}
		}
		filmRepository.save(film);

		return "redirect:/administracion";
	}

	@RequestMapping("/adminSerie")
	public String adminShow(Model model, @RequestParam String name, @RequestParam String newName,
			@RequestParam Optional<Boolean> confirmDelete, @RequestParam Optional<String> deleteShow,
			@RequestParam String actors, @RequestParam String directors, @RequestParam String imageShow,
			@RequestParam Optional<String[]> genreContent, @RequestParam Optional<String[]> newGenres,
			@RequestParam String synopsis, @RequestParam String trailer, @RequestParam String year) { // Y AQUI
		Show show = showRepository.findByName(name);
		if (confirmDelete.isPresent() && confirmDelete.get()) {
			if (name.equals(deleteShow.get())) {

			}
		} else {
			show.setName(newName);
			show.setActors(actors);
			show.setDirectors(directors);
			show.setSynopsis(synopsis);
			show.setTrailer(trailer);
			int yearInt = Integer.parseInt(year);
			show.setYear(yearInt);
		}
		if (genreContent.isPresent()) {
			for (String genre : genreContent.get()) {
				show.getGenders().clear();
				show.getGenders().add(genderRepository.findByName(genre));
			}
		}
		if (newGenres.isPresent()) {
			for (String genre : newGenres.get()) {
				show.getGenders().add(genderRepository.findByName(genre));
			}
		}
		showRepository.save(show);

		return "redirect:/administracion";
	}

	@RequestMapping("/adminLibro")
	public String adminBook(Model model, @RequestParam String name, @RequestParam String newName,
			@RequestParam Optional<Boolean> confirmDelete, @RequestParam Optional<String> deleteBook,
			@RequestParam String authors, @RequestParam String imageBook, @RequestParam Optional<String[]> genreContent,
			@RequestParam Optional<String[]> newGenres, @RequestParam String synopsis, @RequestParam String trailer,
			@RequestParam String year) { // Y AQUI
		Book book = bookRepository.findByName(name);
		if (confirmDelete.isPresent() && confirmDelete.get()) {
			if (name.equals(deleteBook.get())) {

			}
		} else {
			book.setName(newName);
			book.setAuthors(authors);
			book.setSynopsis(synopsis);
			int yearInt = Integer.parseInt(year);
			book.setYear(yearInt);
		}
		if (genreContent.isPresent()) {
			for (String genre : genreContent.get()) {
				book.getGenders().clear();
				book.getGenders().add(genderRepository.findByName(genre));
			}
		}
		if (newGenres.isPresent()) {
			for (String genre : newGenres.get()) {
				book.getGenders().add(genderRepository.findByName(genre));
			}
		}
		bookRepository.save(book);

		return "redirect:/administracion";
	}

	@RequestMapping("/subirContenido")
	public String newContent(Model model) {
		model.addAttribute("genres", genderRepository.findAll());
		return "newContent";
	}

	@RequestMapping("/nuevaPelicula")
	public String newFilm(@RequestParam String imageFilm, @RequestParam String newName, @RequestParam String actors,
			@RequestParam String directors, @RequestParam Optional<String[]> newGenres, @RequestParam String synopsis,
			@RequestParam String trailer, @RequestParam String year) {
		int yearInt = Integer.parseInt(year);
		Film film = new Film(newName, actors, directors, synopsis, "", trailer, yearInt);
		filmRepository.save(film);
		String name = film.getName();
		return "redirect:/pelicula/" + name;
	}

	@RequestMapping("/nuevaSerie")
	public String newShow(@RequestParam String imageShow, @RequestParam String newName, @RequestParam String actors,
			@RequestParam String directors, @RequestParam Optional<String[]> newGenres, @RequestParam String synopsis,
			@RequestParam String trailer, @RequestParam String year) {
		int yearInt = Integer.parseInt(year);
		Show show = new Show(newName, actors, directors, synopsis, "", trailer, yearInt);
		showRepository.save(show);
		String name = show.getName();
		return "redirect:/serie/" + name;
	}

	@RequestMapping("/nuevoLibro")
	public String newBook(@RequestParam String imageBook, @RequestParam String newName, @RequestParam String authors,
			@RequestParam Optional<String[]> newGenres, @RequestParam String synopsis, @RequestParam String year) {
		int yearInt = Integer.parseInt(year);
		Book book = new Book(newName, authors, synopsis, imageBook, yearInt);
		bookRepository.save(book);
		String name = book.getName();
		return "redirect:/libro/" + name;
	}
	
	public String uploadImage(String imageName, MultipartFile file) {
		Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "files");
		String fileName = "image-" + imageName + ".jpg";

		if (!file.isEmpty()) {
			try {
				if (!Files.exists(FILES_FOLDER)) {
					Files.createDirectories(FILES_FOLDER);
				}
				
				File uploadedFile = new File(FILES_FOLDER.toFile(), fileName);
				file.transferTo(uploadedFile);

				return "/imagen/" + fileName;

			} catch (Exception e) {
				return "Error Upload";
			}
		} else {
			return "Empty File";
		}
	}
	
	@RequestMapping("/imagen/{fileName:.+}")
	public void handleFileDownload(@PathVariable String fileName, HttpServletResponse res)
			throws FileNotFoundException, IOException {
		Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "files");
		Path image = FILES_FOLDER.resolve(fileName);

		if (Files.exists(image)) {
			res.setContentType("image/jpeg");
			res.setContentLength((int) image.toFile().length());
			FileCopyUtils.copy(Files.newInputStream(image), res.getOutputStream());

		} else {
			res.sendError(404);
		}
	}
}
