package com.trackorjargh.javaclass;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String synopsis;	
	private String image;
	private int year;
	private boolean firstInList;
	private double points;

	@ManyToMany
	private List<Author> authors;

	public Book() {
	}

	public Book(Long id, String name, String synopsis, String image, int year, boolean firstInList, double points,
			List<Author> authors) {
		this.id = id;
		this.name = name;
		this.synopsis = synopsis;
		this.image = image;
		this.year = year;
		this.firstInList = firstInList;
		this.points = points;
		this.authors = authors;
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

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
}
