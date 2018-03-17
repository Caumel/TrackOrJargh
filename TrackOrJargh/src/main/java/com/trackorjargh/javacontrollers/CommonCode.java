package com.trackorjargh.javacontrollers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.trackorjargh.component.UserComponent;
import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.CommentBook;
import com.trackorjargh.javaclass.CommentFilm;
import com.trackorjargh.javaclass.CommentShow;
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.Gender;
import com.trackorjargh.javaclass.GenerateURLPage;
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
import com.trackorjargh.javarepository.ListsRepository;
import com.trackorjargh.javarepository.PointBookRepository;
import com.trackorjargh.javarepository.PointFilmRepository;
import com.trackorjargh.javarepository.PointShowRepository;
import com.trackorjargh.javarepository.ShowRepository;
import com.trackorjargh.javarepository.UserRepository;
import com.trackorjargh.mail.MailComponent;

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
	@Autowired
	private MailComponent mailComponent;
	@Autowired
	private ListsRepository listsRepository;

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
		if (newName != null) {
			film.setName(newName);
		}
		if (actors != null) {
			film.setActors(actors);
		}
		if (directors != null) {
			film.setDirectors(directors);
		}
		if (synopsis != null) {
			film.setSynopsis(synopsis);
		}
		if (trailer != null) {
			film.setTrailer(trailer);
		}
		if ((year >= 0)) {
			film.setYear(year);
		}
		if (!genders.isEmpty()) {
			film.setGenders(genders);
		}
		if (imageFilm != null) {
			film.setImage(imageFilm);
		}

		filmRepository.save(film);
		return film;
	}

	public Shows editShow(Shows show, String newName, String actors, String directors, String imageShow,
			List<Gender> genders, String synopsis, String trailer, int year) {
		if (newName != null) {
			show.setName(newName);
		}
		if (actors != null) {
			show.setActors(actors);
		}
		if (directors != null) {
			show.setDirectors(directors);
		}
		if (synopsis != null) {
			show.setSynopsis(synopsis);
		}
		if (trailer != null) {
			show.setTrailer(trailer);
		}
		if ((year >= 0)) {
			show.setYear(year);
		}
		if (!genders.isEmpty()) {
			show.setGenders(genders);
		}
		if (imageShow != null) {
			show.setImage(imageShow);
		}
		showRepository.save(show);
		return show;
	}

	public Book editBook(Book book, String newName, String authors, String imageBook, List<Gender> genders,
			String synopsis, int year) {
		if (newName != null) {
			book.setName(newName);
		}
		if (authors != null) {
			book.setAuthors(authors);
		}
		if (synopsis != null) {
			book.setSynopsis(synopsis);
		}
		if (year >= 0) {
			book.setYear(year);
		}
		if (!genders.isEmpty()) {
			book.setGenders(genders);
		}
		if (imageBook != null) {
			book.setImage(imageBook);
		}
		bookRepository.save(book);
		return book;
	}

	public User editUser(User user, String email, String password, List<String> roles, String imageUser) {
		if (email != null) {
			user.setEmail(email);
		}
		if (password != null) {
			user.setPassword(password);
		}
		if (!roles.isEmpty()) {
			user.setRoles(roles);
		}
		if (imageUser != null) {
			user.setImage(imageUser);
		}
		userRepository.save(user);
		return user;
	}

	public User newUser(User user, HttpServletRequest request) {
		return newUser(request, user.getName(), user.getPassword(), user.getEmail(), user.getImage(),
				user.isActivatedUser(), user.getRoles().toString());
	}

	public User newUser(HttpServletRequest request, String name, String pass, String email, String image,
			boolean activate, String... role) {
		if (image != null) {
			if (image.equals("")) {
				image = "/img/default-user.png";
			}
		} else {
			image = "/img/default-user.png";
		}

		User newUser = new User(name, pass, email, image, activate, role);

		if (!activate) {
			GenerateURLPage url = new GenerateURLPage(request);
			mailComponent.sendVerificationEmail(newUser, url.generateURLActivateAccount(newUser));
		}

		userRepository.save(newUser);
		return newUser;
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

	public Lists addEmptyListInUser(String name) {
		if (listsRepository.findByUserAndName(userComponent.getLoggedUser(), name) == null) {
			Lists listUser = new Lists(name);
			listUser.setUser(userComponent.getLoggedUser());
			listsRepository.save(listUser);

			return listUser;
		} else {
			return null;
		}
	}
}
