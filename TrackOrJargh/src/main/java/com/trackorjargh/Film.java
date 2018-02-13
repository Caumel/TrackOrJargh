package com.trackorjargh;

import java.util.List;

public class Film implements Content{
	private Integer id;
	private String name;
	private String synopsis;	
	private String image;
	private int year;
	private boolean firstInList;
	private String trailer;
	private double points;
	private List<String> actors;
	private List<String> directors;
	private List<String> comments;

	public Film(Integer id, String name, String synopsis, String image, int year, boolean firstInList, String trailer,
			double points, List<String> actors, List<String> directors, List<String> comments) {
		this.id = id;
		this.name = name;
		this.synopsis = synopsis;
		this.image = image;
		this.year = year;
		this.firstInList = firstInList;
		this.trailer = trailer;
		this.points = points;
		this.actors = actors;
		this.directors = directors;
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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

	public boolean isPositionList() {
		return firstInList;
	}

	public void setFirstInList(boolean isFirst) {
		this.firstInList = isFirst;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(float points) {
		this.points = points;
	}

	public List<String> getActors() {
		return actors;
	}

	public void setActors(List<String> actors) {
		this.actors = actors;
	}

	public List<String> getDirectors() {
		return directors;
	}

	public void setDirectors(List<String> directors) {
		this.directors = directors;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	
}
