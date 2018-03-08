package com.trackorjargh.javacontrollers;

import java.util.ArrayList;
import java.util.List;

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
		
		for(Film film : films) {
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
		
		for(Book book : books) {
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
		
		for(Shows show : shows) {
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
		for(Gender gende : genderRepository.findAll()) {
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
		if(!userComponent.isLoggedUser()) {
			return null;
		} else {
			User user = userRepository.findByNameIgnoreCase(userComponent.getLoggedUser().getName());
			
			return user.getLists();
		}		
	}

	@RequestMapping(value = "/api/agregarlista/{nameList}/{typeContent}/{nameContent}", method = RequestMethod.PUT)
	public boolean addedListInUser(@PathVariable String nameList,@PathVariable String typeContent , @PathVariable String nameContent) {
		Lists listUser = listsRepository.findByName(nameList);
		
		if (typeContent.equalsIgnoreCase("pelicula")) {
			Film film = filmRepository.findByNameIgnoreCase(nameContent);
			if(listUser.getFilms().contains(film)) {
				return false;
			}
			
			listUser.getFilms().add(film);
		}else if (typeContent.equalsIgnoreCase("serie")){
			Shows show = showRepository.findByNameIgnoreCase(nameContent);
			if(listUser.getShows().contains(show)) {
				return false;
			}
			
			listUser.getShows().add(show);
		}else if (typeContent.equalsIgnoreCase("libro")){
			Book book = bookRepository.findByNameIgnoreCase(nameContent);
			if(listUser.getBooks().contains(book)) {
				return false;
			}
			
			listUser.getBooks().add(book);
		}
		
		listsRepository.save(listUser);
		return true;
	}
	
	@RequestMapping(value = "/api/borrarLista/{nameList}", method = RequestMethod.DELETE)
	public boolean deletedListInUser(@PathVariable String nameList) {
		Lists listUser = listsRepository.findByName(nameList);
		System.out.println(nameList);
		listsRepository.delete(listUser);
		
		return true;
	}
	
	@RequestMapping(value = "/api/borrarContenido/{nameList}/{typeContent}/{nameContent}", method = RequestMethod.DELETE)
	public boolean deletedContentInList(@PathVariable String nameList,@PathVariable String typeContent, @PathVariable String nameContent) {
		Lists listUser = listsRepository.findByName(nameList);
		
		if (typeContent.equalsIgnoreCase("pelicula")) {
			Film film = filmRepository.findByNameIgnoreCase(nameContent);
			listUser.getFilms().remove(film);
			
		}else if (typeContent.equalsIgnoreCase("serie")){
			Shows show = showRepository.findByNameIgnoreCase(nameContent);
			listUser.getShows().remove(show);
			
		}else if(typeContent.equalsIgnoreCase("libro")){
			Book book = bookRepository.findByNameIgnoreCase(nameContent);
			listUser.getBooks().remove(book);
		}
		
		listsRepository.save(listUser);
		return true;
	}
	
	@RequestMapping(value = "/api/comprobarusuario/{name}/", method = RequestMethod.GET)
	public boolean checkUser(@PathVariable String name) {
		User user = userRepository.findByNameIgnoreCase(name);
		
		if(user != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value = "/api/comprobaremail/{email}/", method = RequestMethod.GET)
	public boolean checkEmail(@PathVariable String email) {
		User user = userRepository.findByEmail(email);
		
		if(user != null) {
			return true;
		} else {
			return false;
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

	@RequestMapping(value = "/api/agregarpelicula", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Film> addFilm(@RequestBody Film film) {
		if (filmRepository.findByNameIgnoreCase(film.getName())== null){
			filmRepository.save(film);
			return new ResponseEntity<>(film, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.IM_USED);
		}
	}

	@RequestMapping(value = "/api/agregarlibro", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		if (bookRepository.findByNameIgnoreCase(book.getName())== null){
			bookRepository.save(book);
			return new ResponseEntity<>(book, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.IM_USED);
		}
	}	

	@RequestMapping(value = "/api/borrarpelicula/{name}", method = RequestMethod.DELETE)
	@JsonView(Film.BasicInformation.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Film> deleteFilm(@PathVariable String name){
		if (filmRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			Film deletedFilm = filmRepository.findByNameIgnoreCase(name);
			deleteElementofBBDD.deleteFilm(filmRepository.findByNameIgnoreCase(name));
			return new ResponseEntity<>(deletedFilm, HttpStatus.OK);
		}
	}

		
	@RequestMapping(value = "/api/borrarlibro/{name}", method = RequestMethod.DELETE)
	@JsonView(Book.BasicInformation.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Book> deleteBook(@PathVariable String name){
		if (bookRepository.findByNameIgnoreCase(name) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			Book deletedBook = bookRepository.findByNameIgnoreCase(name);
			deleteElementofBBDD.deleteBook(bookRepository.findByNameIgnoreCase(name));
			return new ResponseEntity<>(deletedBook, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/editarpelicula", method = RequestMethod.PUT)
	@JsonView(Film.BasicInformation.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Film> editFilm(@RequestBody Film film){
		if (filmRepository.findByNameIgnoreCase(film.getName()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			Film editedFilm = filmRepository.findByNameIgnoreCase(film.getName());		
			editedFilm.setYear(film.getYear());				
			editedFilm.setTrailer(film.getTrailer());				
			editedFilm.setSynopsis(film.getSynopsis());				
			editedFilm.setImage(film.getImage());				
			editedFilm.setGenders(film.getGenders());				
			editedFilm.setDirectors(film.getDirectors());				
			editedFilm.setActors(film.getActors());

			filmRepository.save(editedFilm);
			return new ResponseEntity<>(editedFilm, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/editarlibro", method = RequestMethod.PUT)
	@JsonView(Book.BasicInformation.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Book> editBook(@RequestBody Book book){
		if(bookRepository.findByNameIgnoreCase(book.getName()) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			Book editedBook = bookRepository.findByNameIgnoreCase(book.getName());
			editedBook.setYear(book.getYear());
			editedBook.setSynopsis(book.getSynopsis());
			editedBook.setImage(book.getImage());
			editedBook.setGenders(book.getGenders());
			editedBook.setAuthors(book.getAuthors());

			bookRepository.save(editedBook);
			return new ResponseEntity<>(editedBook, HttpStatus.OK);
		}	
	}
	
	public interface basicInfoComentFilm extends CommentFilm.BasicInformation, User.BasicInformation{}
	
	@RequestMapping(value = "/api/pelicula/comentarios/{name}", method = RequestMethod.GET)
	@JsonView(basicInfoComentFilm.class)
	public List<CommentFilm> getCommentsFilm(@PathVariable String name) {
		
		return filmRepository.findByNameIgnoreCase(name).getCommentsFilm();
	}
	
	public interface basicInfoComentShow extends CommentShow.BasicInformation, User.BasicInformation{}
	
	@RequestMapping(value = "/api/serie/comentarios/{name}", method = RequestMethod.GET)
	@JsonView(basicInfoComentShow.class)
	public List<CommentShow> getCommentsShow(@PathVariable String name) {
		
		return showRepository.findByNameIgnoreCase(name).getCommentsShow();
	}
	
	public interface basicInfoComentBook extends CommentBook.BasicInformation, User.BasicInformation{}
	
	@RequestMapping(value = "/api/libro/comentarios/{name}", method = RequestMethod.GET)
	@JsonView(basicInfoComentShow.class)
	public List<CommentBook> getCommentsBook(@PathVariable String name) {
		
		return bookRepository.findByNameIgnoreCase(name).getCommentsBook();
	}
	
	
}
