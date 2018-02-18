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
public class Film implements InterfaceMainItem{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	public interface BasicInformation {}
	
	@JsonView(BasicInformation.class)
	private String name;
	@JsonView(BasicInformation.class)
	private String synopsis;
	@JsonView(BasicInformation.class)
	private String image;
	@JsonView(BasicInformation.class)
	private String trailer;
	@JsonView(BasicInformation.class)
	private int year;
	private boolean firstInList;
	
	@ManyToMany
	private List<Actor> actors = new LinkedList<>();
	
	@ManyToMany
	private List<Director> directors = new LinkedList<>();
	
	@ManyToMany
	private List<Gender> genders = new LinkedList<>();
	
	@ManyToMany(mappedBy="films")
	private List<Lists> lists = new LinkedList<>();
	
	@OneToOne(mappedBy="film")
	private CommentFilm commentFilm;
	
	@OneToOne(mappedBy="film")
	private PointFilm pointFilm;

	public Film() {
	}

	public Film(String name, String synopsis, String image, String trailer, int year) {
		this.name = name;
		this.synopsis = synopsis;
		this.image = image;
		this.trailer = trailer;
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

	public CommentFilm getCommentFilm() {
		return commentFilm;
	}

	public void setCommentFilm(CommentFilm commentFilm) {
		this.commentFilm = commentFilm;
	}

	public PointFilm getPointFilm() {
		return pointFilm;
	}

	public void setPointFilm(PointFilm pointFilm) {
		this.pointFilm = pointFilm;
	}
}
