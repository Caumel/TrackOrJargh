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
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.ForgotPassword;
import com.trackorjargh.javaclass.GenerateURLPage;
import com.trackorjargh.javaclass.InterfaceMainItem;
import com.trackorjargh.javaclass.Lists;
import com.trackorjargh.javaclass.RandomGenerate;
import com.trackorjargh.javaclass.Show;
import com.trackorjargh.javaclass.User;
import com.trackorjargh.javarepository.BookRepository;
import com.trackorjargh.javarepository.FilmRepository;
import com.trackorjargh.javarepository.ForgotPasswordRepository;
import com.trackorjargh.javarepository.ListsRepository;
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
		model.addAttribute("firstSeason", showRepository.findByName(name).getFirstSeason());
		model.addAttribute("listNoFirstSeason", showRepository.findByName(name).getListNoFirst());
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
	
	
	
	//create empty list 
	@RequestMapping("/listaNueva")
	public String modProfile(Model model,@RequestParam String listName) {
		listsRepository.save(new Lists(listName));
		
		return "redirect:/miperfil";
	}

	//@RequestMapping(/"borrarLista")
	//public String modProfile(Model model)
	
	
	
	
	
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
	public String changePass(Model model, HttpServletRequest request, @PathVariable String alphanumericCode, @RequestParam Optional<String> pass) {
		ForgotPassword forgotPass = forgotPasswordRepository.findBySecretAlphanumeric(alphanumericCode);
	
		if(forgotPass == null) {
			model.addAttribute("wrongCode", true);
			
			return "recoverPass";
		}
		
		User user = forgotPass.getUser();
		
		if(pass.isPresent()) {
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
				if(forgotPass != null) {
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
	
}
