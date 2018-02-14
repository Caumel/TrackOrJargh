package com.trackorjargh.javaclass;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Director {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String lastName;
	private Integer birthDay;
	
	@ManyToMany
	private List<Film> films;
	
	@ManyToMany
	private List<Film> shows;

	public Director() {
	}

	public Director(Long id, String name, String lastName, Integer birthDay, List<Film> films, List<Film> shows) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.birthDay = birthDay;
		this.films = films;
		this.shows = shows;
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
