package com.trackorjargh.javaclass;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Show {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String synopsis;	
	private String image;
	private String trailer;
	private int year;
	private boolean firstInList;
	
	@ManyToMany
	private List<Actor> actors;
	
	@ManyToMany
	private List<Director> directors;

	public Show() {
	}

	public Show(String name, String synopsis, String image, String trailer, int year, boolean firstInList,
			List<Actor> actors, List<Director> directors) {
		this.name = name;
		this.synopsis = synopsis;
		this.image = image;
		this.trailer = trailer;
		this.year = year;
		this.firstInList = firstInList;
		this.actors = actors;
		this.directors = directors;
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

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
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

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public List<Director> getDirectors() {
		return directors;
	}

	public void setDirectors(List<Director> directors) {
		this.directors = directors;
	}
}
