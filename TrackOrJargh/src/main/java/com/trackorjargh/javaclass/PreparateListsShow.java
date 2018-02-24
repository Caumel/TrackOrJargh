package com.trackorjargh.javaclass;

import java.util.ArrayList;
import java.util.List;

public class PreparateListsShow {
	
	private String name;
	private List<InterfaceMainItem> printList = new ArrayList<>();
	
	private List<Film> films;
	private List<Book> books;
	private List<Show> shows;
	
	public PreparateListsShow(String name, List<Film> films, List<Book> books,
			List<Show> shows) {
		this.name = name;
		this.films = films;
		this.books = books;
		this.shows = shows;
		
		preparatePrintList();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<InterfaceMainItem> getPrintList() {
		return printList;
	}

	public void setPrintList(List<InterfaceMainItem> printList) {
		this.printList = printList;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}
	
	public void preparatePrintList() {
		this.printList = new ArrayList<>();
		for(Film f:films) 
			this.printList.add(f);
		
		for(Show s:shows) 
			this.printList.add(s);
		
		for(Book b:books) 
			this.printList.add(b);
	}
}