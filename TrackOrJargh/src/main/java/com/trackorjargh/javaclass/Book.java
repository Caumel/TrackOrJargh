package com.trackorjargh.javaclass;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Book implements InterfaceMainItem{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	static final String url = "/libro/";
	
	public interface BasicInformation {}
	
	@JsonView(BasicInformation.class)
	private String name;
	@JsonView(BasicInformation.class)
	private String synopsis;	
	@JsonView(BasicInformation.class)
	private String image;
	@JsonView(BasicInformation.class)
	private int year;
	private boolean firstInList;

	@ManyToMany
	private List<Author> authors = new LinkedList<>();
	
	@ManyToMany
	private List<Gender> genders = new LinkedList<>();
	
	@ManyToMany(mappedBy="books")
	private List<Lists> lists = new LinkedList<>();
	
	@OneToOne(mappedBy="book")
	private CommentBook commentBook;
	
	@OneToOne(mappedBy="book")
	private PointBook pointBook;

	public Book() {
	}

	public Book(String name, String synopsis, String image, int year) {
		this.name = name;
		this.synopsis = synopsis;
		this.image = image;
		this.year = year;
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

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isFirstInList() {
		return firstInList;
	}

	public void setFirstInList(boolean firstInList) {
		this.firstInList = firstInList;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Gender> getGenders() {
		return genders;
	}

	public void setGenders(List<Gender> genders) {
		this.genders = genders;
	}

	public List<Lists> getLists() {
		return lists;
	}

	public void setLists(List<Lists> lists) {
		this.lists = lists;
	}

	public CommentBook getCommentBook() {
		return commentBook;
	}

	public void setCommentBook(CommentBook commentBook) {
		this.commentBook = commentBook;
	}

	public PointBook getPointBook() {
		return pointBook;
	}

	public void setPointBook(PointBook pointBook) {
		this.pointBook = pointBook;
	}
}
