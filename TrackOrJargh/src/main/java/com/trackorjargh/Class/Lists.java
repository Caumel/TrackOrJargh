package com.trackorjargh.Class;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Lists {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@OneToMany
	private List<Film> fimls;
	
	@OneToMany
	private List<Show> shows;
	
	@OneToMany
	private List<Book> books;

	public Lists() {
	}

	public Lists(Long id, String name, List<Film> fimls, List<Show> shows, List<Book> books) {
		this.id = id;
		this.name = name;
		this.fimls = fimls;
		this.shows = shows;
		this.books = books;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Film> getFimls() {
		return fimls;
	}

	public void setFimls(List<Film> fimls) {
		this.fimls = fimls;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
