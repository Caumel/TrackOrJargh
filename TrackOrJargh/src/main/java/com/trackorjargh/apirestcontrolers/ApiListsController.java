package com.trackorjargh.apirestcontrolers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonView;
import com.trackorjargh.commoncode.CommonCodeUser;
import com.trackorjargh.component.UserComponent;
import com.trackorjargh.javaclass.Book;
import com.trackorjargh.javaclass.Film;
import com.trackorjargh.javaclass.Lists;
import com.trackorjargh.javaclass.Shows;
import com.trackorjargh.javaclass.User;
import com.trackorjargh.javarepository.BookRepository;
import com.trackorjargh.javarepository.FilmRepository;
import com.trackorjargh.javarepository.ListsRepository;
import com.trackorjargh.javarepository.ShowRepository;
import com.trackorjargh.javarepository.UserRepository;

public class ApiListsController {	
	private final ListsRepository listsRepository;
	private final UserRepository userRepository;
	private final UserComponent userComponent;
	private final CommonCodeUser commonCodeUser;
	private final FilmRepository filmRepository;
	private final ShowRepository showRepository;
	private final BookRepository bookRepository;
		
	@Autowired
	public ApiListsController(ListsRepository listsRepository, UserRepository userRepository,
			UserComponent userComponent, CommonCodeUser commonCodeUser, FilmRepository filmRepository,
			ShowRepository showRepository, BookRepository bookRepository) {
		this.listsRepository = listsRepository;
		this.userRepository = userRepository;
		this.userComponent = userComponent;
		this.commonCodeUser = commonCodeUser;
		this.filmRepository = filmRepository;
		this.showRepository = showRepository;
		this.bookRepository = bookRepository;
	}

	@RequestMapping(value = "/api/listasusuario", method = RequestMethod.GET)
	@JsonView(Lists.BasicInformation.class)
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
			return new ResponseEntity<>(commonCodeUser.addEmptyListInUser(name), HttpStatus.OK);
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
}
