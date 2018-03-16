package com.trackorjargh.javacontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackorjargh.component.UserComponent;
import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.CommentBook;
import com.trackorjargh.javaclass.CommentFilm;
import com.trackorjargh.javaclass.CommentShow;
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.Gender;
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
import com.trackorjargh.javarepository.PointBookRepository;
import com.trackorjargh.javarepository.PointFilmRepository;
import com.trackorjargh.javarepository.PointShowRepository;
import com.trackorjargh.javarepository.ShowRepository;
import com.trackorjargh.javarepository.UserRepository;


@Service
public class CommonCode {
	
	@Autowired
	private UserComponent userComponent;
	@Autowired
	private PointFilmRepository pointFilmRepository;
	@Autowired
	private PointShowRepository pointShowRepository;
	@Autowired
	private PointBookRepository pointBookRepository;
	@Autowired
	private CommentFilmRepository commentFilmRepository;
	@Autowired
	private CommentShowRepository commentShowRepository;
	@Autowired
	private CommentBookRepository commentBookRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private ShowRepository showRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;
	
	public PointFilm updatePointsFilm(Film film, double points) {
		PointFilm pointFilm = pointFilmRepository.findByUserAndFilm(userComponent.getLoggedUser(), film);

		if (pointFilm == null) {
			pointFilm = new PointFilm(points);
			pointFilm.setFilm(film);
			pointFilm.setUser(userComponent.getLoggedUser());
		} else {
			pointFilm.setPoints(points);
		}

		pointFilmRepository.save(pointFilm);
		return pointFilm;
	}
	
	public PointShow updatePointsShow(Shows show, double points) {
		PointShow pointShow = pointShowRepository.findByUserAndShow(userComponent.getLoggedUser(), show);

		if (pointShow == null) {
			pointShow = new PointShow(points);
			pointShow.setShow(show);
			pointShow.setUser(userComponent.getLoggedUser());
		} else {
			pointShow.setPoints(points);
		}

		pointShowRepository.save(pointShow);
		return pointShow;
	}
	
	public PointBook updatePointsBook(Book book, double points) {
		PointBook pointBook = pointBookRepository.findByUserAndBook(userComponent.getLoggedUser(), book);

		if (pointBook == null) {
			pointBook = new PointBook(points);
			pointBook.setBook(book);
			pointBook.setUser(userComponent.getLoggedUser());
		} else {
			pointBook.setPoints(points);
		}

		pointBookRepository.save(pointBook);
		return pointBook;
	}
	
	public CommentFilm addCommentFilm(Film film, String messageUser) {
		CommentFilm message = new CommentFilm(messageUser);
		message.setFilm(film);
		message.setUser(userComponent.getLoggedUser());

		commentFilmRepository.save(message);
		return message;
	}
	
	public CommentShow addCommentShow(Shows show, String messageUser) {
		CommentShow message = new CommentShow(messageUser);
		message.setShow(show);
		message.setUser(userComponent.getLoggedUser());

		commentShowRepository.save(message);
		return message;
	}
	
	public CommentBook addCommentBook(Book book, String messageUser) {
		CommentBook message = new CommentBook(messageUser);
		message.setBook(book);
		message.setUser(userComponent.getLoggedUser());

		commentBookRepository.save(message);
		return message;
	}
	
	public CommentFilm deleteCommentFilm(Long id) {
		CommentFilm comment = commentFilmRepository.findById(new Long(id));
		commentFilmRepository.delete(comment);
		
		return comment;
	}
	
	public CommentShow deleteCommentShow(Long id) {
		CommentShow comment = commentShowRepository.findById(new Long(id));
		commentShowRepository.delete(comment);
		
		return comment;
	}
	
	public CommentBook deleteCommentBook(Long id) {
		CommentBook comment = commentBookRepository.findById(new Long(id));
		commentBookRepository.delete(comment);
		
		return comment;
	}
	
	public Film editFilm(Film film, String newName, String actors, String directors, String imageFilm,
		List<Gender> genders, String synopsis, String trailer, int year) {
		film.setName(newName);
		film.setActors(actors);
		film.setDirectors(directors);
		film.setSynopsis(synopsis);
		film.setTrailer(trailer);
		film.setYear(year);
		film.setGenders(genders);
		if (!imageFilm.isEmpty()) {
			film.setImage(imageFilm);
		}
		
		filmRepository.save(film);
		return film;
	}
	
	public Shows editShow(Shows show, String newName, String actors, String directors, String imageShow,
		List<Gender> genders, String synopsis, String trailer, int year) {
		if(!newName.equals(" ")) {
			show.setName(newName);
		}
		if(!actors.equals(" ")) {
			show.setActors(actors);
		}
		if(!directors.equals(" ")) {
			show.setDirectors(directors);
		}
		if(!synopsis.equals(" ")) {
			show.setSynopsis(synopsis);
		}
		if(!trailer.equals(" ")) {
			show.setTrailer(trailer);
		}
		if(!(year == 0)) {
			show.setYear(year);
		}
		if(!genders.equals(" ")) {
			show.setGenders(genders);
		}
		if(!imageShow.equals(" ")) {
			show.setImage(imageShow);
		}
		showRepository.save(show);
		return show;
	}
	
	public Book editBook(Book book, String newName, String authors, String imageBook,
		List<Gender> genders, String synopsis, int year) {
		book.setName(newName);
		book.setAuthors(authors);
		book.setSynopsis(synopsis);
		book.setYear(year);
		book.setGenders(genders);
		book.setImage(imageBook);
		bookRepository.save(book);
		return book;
	}
	
	public User editUser(User user, String email,String password, List<String> roles, String imageUser) {

		user.setEmail(email);
		if(!password.equals("false")) {
			user.setPassword(password);
		}
		user.setRoles(roles);
		user.setImage(imageUser);
		userRepository.save(user);
		return user;
	}
	
}
