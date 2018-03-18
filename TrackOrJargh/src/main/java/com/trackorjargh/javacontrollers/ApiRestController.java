package com.trackorjargh.javacontrollers;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.trackorjargh.component.UserComponent;
import com.trackorjargh.grafics.Grafics;
import com.trackorjargh.grafics.NumberItemByGende;
import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.CommentBook;
import com.trackorjargh.javaclass.CommentFilm;
import com.trackorjargh.javaclass.CommentShow;
import com.trackorjargh.javaclass.DeleteElementsOfBBDD;
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.Gender;
import com.trackorjargh.javaclass.Lists;
import com.trackorjargh.javaclass.PointBook;
import com.trackorjargh.javaclass.PointFilm;
import com.trackorjargh.javaclass.PointShow;
import com.trackorjargh.javaclass.Shows;
import com.trackorjargh.javaclass.User;
import com.trackorjargh.javarepository.BookRepository;
import com.trackorjargh.javarepository.CommentBookRepository;
import com.trackorjargh.javarepository.CommentFilmRepository;
import com.trackorjargh.javarepository.CommentShowRepository;
import com.trackorjargh.javarepository.FilmRepository;
import com.trackorjargh.javarepository.GenderRepository;
import com.trackorjargh.javarepository.ListsRepository;
import com.trackorjargh.javarepository.PointBookRepository;
import com.trackorjargh.javarepository.PointFilmRepository;
import com.trackorjargh.javarepository.PointShowRepository;
import com.trackorjargh.javarepository.ShowRepository;
import com.trackorjargh.javarepository.UserRepository;

@RestController
public class ApiRestController {

	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private PointFilmRepository pointFilmRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private PointShowRepository pointShowRepository;
	@Autowired
	private ShowRepository showRepository;
	@Autowired
	private PointBookRepository pointBookRepository;
	@Autowired
	private ListsRepository listsRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private GenderRepository genderRepository;
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private DeleteElementsOfBBDD deleteElementofBBDD;
	@Autowired
	private CommentFilmRepository commentFilmRepository;
	@Autowired
	private CommentShowRepository commentShowRepository;
	@Autowired
	private CommentBookRepository commentBookRepository;
	@Autowired
	private CommonCode commonCode;

	@RequestMapping(value = "/api/peliculas", method = RequestMethod.GET)
	@JsonView(Film.BasicInformation.class)
	public Page<Film> getPeliculas(Pageable page) {
		return filmRepository.findAll(page);
	}

	@RequestMapping(value = "/api/libros", method = RequestMethod.GET)
	@JsonView(Book.BasicInformation.class)
	public Page<Book> getLibros(Pageable page) {
		return bookRepository.findAll(page);
	}

	@RequestMapping(value = "/api/series", method = RequestMethod.GET)
	@JsonView(Shows.BasicInformation.class)
	public Page<Shows> getSeries(Pageable page) {
		return showRepository.findAll(page);
	}

	@RequestMapping(value = "/api/peliculas/mejorvaloradas", method = RequestMethod.GET)
	@JsonView(Film.BasicInformation.class)
	public Page<Film> getBestPointPeliculas(Pageable page) {
		return filmRepository.findBestPointFilm(page);
	}

	@RequestMapping(value = "/api/peliculas/graficomejorvaloradas", method = RequestMethod.GET)
	public List<Grafics> getBestPointFilms() {
		List<Film> films = filmRepository.findBestPointFilm(new PageRequest(0, 10)).getContent();
		List<PointFilm> listPoints;
		List<Grafics> graficFilms = new ArrayList<>();
		double points;

		for (Film film : films) {
			points = 0;

			listPoints = pointFilmRepository.findByFilm(film);

			if (listPoints.size() > 0) {
				for (PointFilm pf : listPoints)
					points += pf.getPoints();
				points /= listPoints.size();
			}

			graficFilms.add(new Grafics(film.getName(), points));
		}

		return graficFilms;
	}

	@RequestMapping(value = "/api/libros/mejorvalorados", method = RequestMethod.GET)
	@JsonView(Book.BasicInformation.class)
	public Page<Book> getBestPointLibros(Pageable page) {
		return bookRepository.findBestPointBook(page);
	}

	@RequestMapping(value = "/api/libros/graficomejorvaloradas", method = RequestMethod.GET)
	public List<Grafics> getBestPointBooks() {
		List<Book> books = bookRepository.findBestPointBook(new PageRequest(0, 10)).getContent();
		List<PointBook> listPoints;
		List<Grafics> graficShows = new ArrayList<>();
		double points;

		for (Book book : books) {
			points = 0;

			listPoints = pointBookRepository.findByBook(book);

			if (listPoints.size() > 0) {
				for (PointBook sb : listPoints)
					points += sb.getPoints();
				points /= listPoints.size();
			}

			graficShows.add(new Grafics(book.getName(), points));
		}

		return graficShows;
	}

	@RequestMapping(value = "/api/series/graficomejorvaloradas", method = RequestMethod.GET)
	public List<Grafics> getBestPointShows() {
		List<Shows> shows = showRepository.findBestPointShow(new PageRequest(0, 10)).getContent();
		List<PointShow> listPoints;
		List<Grafics> graficShows = new ArrayList<>();
		double points;

		for (Shows show : shows) {
			points = 0;

			listPoints = pointShowRepository.findByShow(show);

			if (listPoints.size() > 0) {
				for (PointShow sf : listPoints)
					points += sf.getPoints();
				points /= listPoints.size();
			}

			graficShows.add(new Grafics(show.getName(), points));
		}

		return graficShows;
	}

	@RequestMapping(value = "/api/graficogeneros", method = RequestMethod.GET)
	public List<NumberItemByGende> getGraphicGende() {
		List<NumberItemByGende> listGende = new ArrayList<>();

		int sumGende;
		for (Gender gende : genderRepository.findAll()) {
			sumGende = 0;
			sumGende += gende.getFilms().size();
			sumGende += gende.getBooks().size();
			sumGende += gende.getShows().size();

			listGende.add(new NumberItemByGende(gende.getName(), sumGende));
		}

		return listGende;
	}

	@RequestMapping(value = "/api/series/mejorvaloradas", method = RequestMethod.GET)
	@JsonView(Shows.BasicInformation.class)
	public Page<Shows> getBestPointSeries(Pageable page) {
		return showRepository.findBestPointShow(page);
	}

	@RequestMapping(value = "/api/busqueda/{optionSearch}/series/{name}/page", method = RequestMethod.GET)
	@JsonView(Shows.BasicInformation.class)
	public Page<Shows> getSearchSeries(Pageable page, @PathVariable String optionSearch, @PathVariable String name) {
		if (optionSearch.equalsIgnoreCase("titulo")) {
			return showRepository.findByNameContainingIgnoreCase(name, page);
		} else {
			return showRepository.findShowsByGender("%" + name + "%", page);
		}
	}

	@RequestMapping(value = "/api/busqueda/{optionSearch}/peliculas/{name}/page", method = RequestMethod.GET)
	@JsonView(Film.BasicInformation.class)
	public Page<Film> getSearchPeliculas(Pageable page, @PathVariable String optionSearch, @PathVariable String name) {
		if (optionSearch.equalsIgnoreCase("titulo")) {
			return filmRepository.findByNameContainingIgnoreCase(name, page);
		} else {
			return filmRepository.findFilmsByGender("%" + name + "%", page);
		}
	}

	@RequestMapping(value = "/api/busqueda/{optionSearch}/libros/{name}/page", method = RequestMethod.GET)
	@JsonView(Book.BasicInformation.class)
	public Page<Book> getSearchLibros(Pageable page, @PathVariable String optionSearch, @PathVariable String name) {
		if (optionSearch.equalsIgnoreCase("titulo")) {
			return bookRepository.findByNameContainingIgnoreCase(name, page);
		} else {
			return bookRepository.findBooksByGender("%" + name + "%", page);
		}
	}

	@RequestMapping(value = "/api/listasusuario", method = RequestMethod.GET)
	public List<Lists> getListsUser() {
		if (!userComponent.isLoggedUser()) {
			return null;
		} else {
			User user = userRepository.findByNameIgnoreCase(userComponent.getLoggedUser().getName());

			return user.getLists();
		}
	}

	@RequestMapping(value = "/api/agregarlistausuario/{name}", method = RequestMethod.POST)
	public ResponseEntity<Lists> addEmptyListInUser(@PathVariable String name) {
		Lists listUser = listsRepository.findByUserAndName(userComponent.getLoggedUser(), name);

		if (listUser == null) {
			return new ResponseEntity<>(commonCode.addEmptyListInUser(name), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.IM_USED);
		}
	}

	@RequestMapping(value = "/api/agregarcontenidolista/{nameList}/{typeContent}/{nameContent}", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> addedListInUser(@PathVariable String nameList, @PathVariable String typeContent,
			@PathVariable String nameContent) {
		Lists listUser = listsRepository.findByUserAndName(userComponent.getLoggedUser(), nameList);

		if (!listUser.getUser().getName().equals(userComponent.getLoggedUser().getName())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (typeContent.equalsIgnoreCase("pelicula")) {
			Film film = filmRepository.findByNameIgnoreCase(nameContent);
			if (listUser.getFilms().contains(film)) {
				return new ResponseEntity<>(false, HttpStatus.OK);
			}

			listUser.getFilms().add(film);
		} else if (typeContent.equalsIgnoreCase("serie")) {
			Shows show = showRepository.findByNameIgnoreCase(nameContent);
			if (listUser.getShows().contains(show)) {
				return new ResponseEntity<>(false, HttpStatus.OK);
			}

			listUser.getShows().add(show);
		} else if (typeContent.equalsIgnoreCase("libro")) {
			Book book = bookRepository.findByNameIgnoreCase(nameContent);
			if (listUser.getBooks().contains(book)) {
				return new ResponseEntity<>(false, HttpStatus.OK);
			}

			listUser.getBooks().add(book);
		}

		listsRepository.save(listUser);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/borrarlista/{nameList}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deletedListInUser(@PathVariable String nameList) {
		Lists listUser = listsRepository.findByUserAndName(userComponent.getLoggedUser(), nameList);

		if (!listUser.getUser().getName().equals(userComponent.getLoggedUser().getName())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		listsRepository.delete(listUser);

		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/borrarcontenido/{nameList}/{typeContent}/{nameContent}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deletedContentInList(@PathVariable String nameList, @PathVariable String typeContent,
			@PathVariable String nameContent) {
		Lists listUser = listsRepository.findByUserAndName(userComponent.getLoggedUser(), nameList);

		if (!listUser.getUser().getName().equals(userComponent.getLoggedUser().getName())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		if (typeContent.equalsIgnoreCase("pelicula")) {
			Film film = filmRepository.findByNameIgnoreCase(nameContent);
			listUser.getFilms().remove(film);

		} else if (typeContent.equalsIgnoreCase("serie")) {
			Shows show = showRepository.findByNameIgnoreCase(nameContent);
			listUser.getShows().remove(show);

		} else if (typeContent.equalsIgnoreCase("libro")) {
			Book book = bookRepository.findByNameIgnoreCase(nameContent);
			listUser.getBooks().remove(book);
		}

		listsRepository.save(listUser);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/comprobarusuario/{name}/", method = RequestMethod.GET)
	public boolean checkUser(@PathVariable String name) {
		User user = userRepository.findByNameIgnoreCase(name);

		if (user != null) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/api/comprobaremail/{email}/", method = RequestMethod.GET)
	public boolean checkEmail(@PathVariable String email) {
		User user = userRepository.findByEmail(email);

		if (user != null) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/api/serie/{name}")
	@JsonView(Shows.BasicInformation.class)
	public ResponseEntity<Shows> getShow(@PathVariable("name") String name) {
		Shows show = showRepository.findByNameIgnoreCase(name);
		if (show == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(show, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/usuario/{name}", method = RequestMethod.GET)
	@JsonView(User.BasicInformation.class)
	public ResponseEntity<User> getUser(@PathVariable String name) {
		User user = userRepository.findByNameIgnoreCase(name);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/pelicula/{name}", method = RequestMethod.GET)
	@JsonView(Film.BasicInformation.class)
	public Film getFilm(@PathVariable String name) {

		return filmRepository.findByNameIgnoreCase(name);
	}

	@RequestMapping(value = "/api/libro/{name}", method = RequestMethod.GET)
	@JsonView(Book.BasicInformation.class)
	public Book getBook(@PathVariable String name) {

		return bookRepository.findByNameIgnoreCase(name);
	}

	@RequestMapping(value = "/api/agregarususario", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> addUser(HttpServletRequest request, @RequestBody User user) {
		if (userRepository.findByNameIgnoreCase(user.getName()) == null
				&& userRepository.findByEmail(user.getEmail()) == null) {
			return new ResponseEntity<>(commonCode.newUser(user, request), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.IM_USED);
		}
	}

	@RequestMapping(value = "/api/agregarserie", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Shows> addShow(@RequestBody Shows show) {
		if (showRepository.findByNameIgnoreCase(show.getName()) == null) {
			showRepository.save(show);

			return new ResponseEntity<>(show, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.IM_USED);
		}

	}

	@RequestMapping(value = "/api/agregarpelicula", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Film> addFilm(@RequestBody Film film) {
		if (filmRepository.findByNameIgnoreCase(film.getName()) == null) {
			filmRepository.save(film);
			return new ResponseEntity<>(film, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.IM_USED);
		}
	}

	@RequestMapping(value = "/api/agregarlibro", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		if (bookRepository.findByNameIgnoreCase(book.getName()) == null) {
			bookRepository.save(book);
			return new ResponseEntity<>(book, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.IM_USED);
		}
	}

	@RequestMapping(value = "/api/borrarusuario/{name}", method = RequestMethod.DELETE)
	@JsonView(User.BasicInformation.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<User> deleteUser(@PathVariable("name") String name) {
		User user = userRepository.findByNameIgnoreCase(name);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			deleteElementofBBDD.deleteUser(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/borrarserie/{name}", method = RequestMethod.DELETE)
	@JsonView(Shows.BasicInformation.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Shows> deleteShow(@PathVariable("name") String name) {
		Shows show = showRepository.findByNameIgnoreCase(name);
		if (show == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			deleteElementofBBDD.deleteShow(show);
			return new ResponseEntity<>(show, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/api/borrarpelicula/{name}", method = RequestMethod.DELETE)
	@JsonView(Film.BasicInformation.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Film> deleteFilm(@PathVariable String name) {
		if (filmRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			Film deletedFilm = filmRepository.findByNameIgnoreCase(name);
			deleteElementofBBDD.deleteFilm(filmRepository.findByNameIgnoreCase(name));
			return new ResponseEntity<>(deletedFilm, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/borrarlibro/{name}", method = RequestMethod.DELETE)
	@JsonView(Book.BasicInformation.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Book> deleteBook(@PathVariable String name) {
		if (bookRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			Book deletedBook = bookRepository.findByNameIgnoreCase(name);
			deleteElementofBBDD.deleteBook(bookRepository.findByNameIgnoreCase(name));
			return new ResponseEntity<>(deletedBook, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/editarusuario/{name}", method = RequestMethod.PUT)
	@JsonView(User.BasicInformation.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<User> editUser(@PathVariable String name, @RequestBody User user,
			HttpServletRequest request) {
		if (userRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			User editedUser = userRepository.findByNameIgnoreCase(name);

			return new ResponseEntity<>(commonCode.editUser(editedUser, user.getEmail(), user.getPassword(),
					user.getRoles(), user.getImage()), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/editarserie/{name}", method = RequestMethod.PUT)
	@JsonView(Shows.BasicInformation.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Shows> editShow(@PathVariable String name, @RequestBody Shows show) {
		if (showRepository.findByNameIgnoreCase(name) == null) { // if the film does not exists, then i return
																	// a not found statement
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else { // if it exists, then i modify the item
			Shows editedShow = showRepository.findByNameIgnoreCase(name);

			return new ResponseEntity<>(
					commonCode.editShow(editedShow, show.getName(), show.getActors(), show.getDirectors(),
							show.getImage(), show.getGenders(), show.getSynopsis(), show.getTrailer(), show.getYear()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/editarpelicula/{name}", method = RequestMethod.PUT)
	@JsonView(Film.BasicInformation.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Film> editFilm(@PathVariable String name, @RequestBody Film film) {
		if (filmRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			Film editedFilm = filmRepository.findByNameIgnoreCase(name);

			return new ResponseEntity<>(
					commonCode.editFilm(editedFilm, film.getName(), film.getActors(), film.getDirectors(),
							film.getImage(), film.getGenders(), film.getSynopsis(), film.getTrailer(), film.getYear()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/editarlibro/{name}", method = RequestMethod.PUT)
	@JsonView(Book.BasicInformation.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Book> editBook(@PathVariable String name, @RequestBody Book book) {
		if (bookRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			Book editedBook = bookRepository.findByNameIgnoreCase(name);

			return new ResponseEntity<>(commonCode.editBook(editedBook, book.getName(), book.getAuthors(),
					book.getImage(), book.getGenders(), book.getSynopsis(), book.getYear()), HttpStatus.OK);
		}
	}

	public interface basicInfoCommentFilm extends CommentFilm.BasicInformation, User.BasicInformation {
	}

	@RequestMapping(value = "/api/pelicula/comentarios/{name}", method = RequestMethod.GET)
	@JsonView(basicInfoCommentFilm.class)
	public List<CommentFilm> getCommentsFilm(@PathVariable String name) {

		return filmRepository.findByNameIgnoreCase(name).getCommentsFilm();
	}

	public interface basicInfoCommentShow extends CommentShow.BasicInformation, User.BasicInformation {
	}

	@RequestMapping(value = "/api/serie/comentarios/{name}", method = RequestMethod.GET)
	@JsonView(basicInfoCommentShow.class)
	public List<CommentShow> getCommentsShow(@PathVariable String name) {

		return showRepository.findByNameIgnoreCase(name).getCommentsShow();
	}

	public interface basicInfoCommentBook extends CommentBook.BasicInformation, User.BasicInformation {
	}

	@RequestMapping(value = "/api/libro/comentarios/{name}", method = RequestMethod.GET)
	@JsonView(basicInfoCommentShow.class)
	public List<CommentBook> getCommentsBook(@PathVariable String name) {

		return bookRepository.findByNameIgnoreCase(name).getCommentsBook();
	}

	@RequestMapping(value = "/api/pelicula/agregarcomentario/{name}", method = RequestMethod.POST)
	@JsonView(basicInfoCommentFilm.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommentFilm> addComentFilm(@PathVariable String name, @RequestBody CommentFilm comment) {
		if (filmRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(
					commonCode.addCommentFilm(filmRepository.findByNameIgnoreCase(name), comment.getComment()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/serie/agregarcomentario/{name}", method = RequestMethod.POST)
	@JsonView(basicInfoCommentShow.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommentShow> addComentShow(@PathVariable String name, @RequestBody CommentShow comment) {
		if (showRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(
					commonCode.addCommentShow(showRepository.findByNameIgnoreCase(name), comment.getComment()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/libro/agregarcomentario/{name}", method = RequestMethod.POST)
	@JsonView(basicInfoCommentBook.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommentBook> addComentBook(@PathVariable String name, @RequestBody CommentBook comment) {
		if (bookRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(
					commonCode.addCommentBook(bookRepository.findByNameIgnoreCase(name), comment.getComment()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/pelicula/borrarcomentario/{id}", method = RequestMethod.DELETE)
	@JsonView(basicInfoCommentFilm.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommentFilm> deleteFilmComent(@PathVariable Long id) {
		if (commentFilmRepository.findById(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(commonCode.deleteCommentFilm(id), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/serie/borrarcomentario/{id}", method = RequestMethod.DELETE)
	@JsonView(basicInfoCommentShow.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommentShow> deleteShowComent(@PathVariable Long id) {
		if (commentShowRepository.findById(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(commonCode.deleteCommentShow(id), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/libro/borrarcomentario/{id}", method = RequestMethod.DELETE)
	@JsonView(basicInfoCommentBook.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommentBook> deleteBookComent(@PathVariable Long id) {
		if (commentBookRepository.findById(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(commonCode.deleteCommentBook(id), HttpStatus.OK);
		}
	}

	public interface joinedPointBookUserInfo extends PointBook.BasicInformation, Book.NameBookInfo, User.NameUserInfo {
	}

	@RequestMapping(value = "/api/libro/agregarpuntos/{name}", method = RequestMethod.POST)
	@JsonView(joinedPointBookUserInfo.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PointBook> addBookPoint(@PathVariable String name, @RequestBody PointBook bookPoint) {
		if (bookRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(
					commonCode.updatePointsBook(bookRepository.findByNameIgnoreCase(name), bookPoint.getPoints()),
					HttpStatus.OK);
		}
	}

	public interface joinedPointFilmUserInfo extends PointFilm.BasicInformation, Film.NameFilmInfo, User.NameUserInfo {
	}

	@RequestMapping(value = "/api/pelicula/agregarpuntos/{name}", method = RequestMethod.POST)
	@JsonView(joinedPointFilmUserInfo.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PointFilm> addfilmPoint(@PathVariable String name, @RequestBody PointFilm filmPoint) {
		if (filmRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(
					commonCode.updatePointsFilm(filmRepository.findByNameIgnoreCase(name), filmPoint.getPoints()),
					HttpStatus.OK);
		}
	}

	public interface joinedPointShowUserInfo extends PointShow.BasicInformation, Shows.NameShowInfo, User.NameUserInfo {
	}

	@RequestMapping(value = "/api/serie/agregarpuntos/{name}", method = RequestMethod.POST)
	@JsonView(joinedPointShowUserInfo.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PointShow> addShowPoint(@PathVariable String name, @RequestBody PointShow showPoint) {
		if (showRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(
					commonCode.updatePointsShow(showRepository.findByNameIgnoreCase(name), showPoint.getPoints()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/libro/obtenerpuntos/{name}", method = RequestMethod.GET)
	@JsonView(joinedPointBookUserInfo.class)
	public List<PointBook> getBookPoint(@PathVariable String name) {

		return bookRepository.findByNameIgnoreCase(name).getPointsBook();
	}

	@RequestMapping(value = "/api/pelicula/obtenerpuntos/{name}", method = RequestMethod.GET)
	@JsonView(joinedPointFilmUserInfo.class)
	public List<PointFilm> getFilmPoint(@PathVariable String name) {

		return filmRepository.findByNameIgnoreCase(name).getPointsFilm();
	}

	@RequestMapping(value = "/api/serie/obtenerpuntos/{name}", method = RequestMethod.GET)
	@JsonView(joinedPointShowUserInfo.class)
	public List<PointShow> getShowPoint(@PathVariable String name) {

		return showRepository.findByNameIgnoreCase(name).getPointsShow();
	}

	@RequestMapping(value = "/api/subirimagen", method = RequestMethod.POST, headers = { "content-type=multipart/mixed",
			"content-type=multipart/form-data" })
	public String putUpload(MultipartFile image) {
		return commonCode.uploadImage(
				image.getOriginalFilename().substring(0, image.getOriginalFilename().lastIndexOf('.')), image);
	}
}
