package com.trackorjargh.javaclass;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Actor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String lastName;
	private Integer birthDay;	
	
	@ManyToMany(mappedBy="actors")
	private List<Film> films = new LinkedList<>();
	
	@ManyToMany(mappedBy="actors")
	private List<Film> shows = new LinkedList<>();

	public Actor() {
	}

	public Actor(String name, String lastName, Integer birthDay) {
		this.name = name;
		this.lastName = lastName;
		this.birthDay = birthDay;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Integer birthDay) {
		this.birthDay = birthDay;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public List<Film> getShows() {
		return shows;
	}

	public void setShows(List<Film> shows) {
		this.shows = shows;
	}
}
