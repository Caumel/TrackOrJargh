package com.trackorjargh.javacontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackorjargh.component.UserComponent;
import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.CommentBook;
import com.trackorjargh.javaclass.CommentFilm;
import com.trackorjargh.javaclass.CommentShow;
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.PointBook;
import com.trackorjargh.javaclass.PointFilm;
import com.trackorjargh.javaclass.PointShow;
import com.trackorjargh.javaclass.Shows;
import com.trackorjargh.javarepository.CommentBookRepository;
import com.trackorjargh.javarepository.CommentFilmRepository;
import com.trackorjargh.javarepository.CommentShowRepository;
import com.trackorjargh.javarepository.PointBookRepository;
import com.trackorjargh.javarepository.PointFilmRepository;
import com.trackorjargh.javarepository.PointShowRepository;

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
	
}
