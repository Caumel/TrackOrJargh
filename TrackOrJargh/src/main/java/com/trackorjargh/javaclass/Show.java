package com.trackorjargh.javaclass;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Show implements InterfaceMainItem{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	static final String url = "/serie/";
	
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
	
	@OneToMany
	private List<Season> seasons = new LinkedList<>();
	
	@ManyToMany(mappedBy="shows")
	private List<Lists> lists = new LinkedList<>();
	
	@OneToOne(mappedBy="show")
	private CommentShow commentShow;
	
	@OneToOne(mappedBy="show")
	private PointShow pointShow;

	public Show() {
	}

	public Show(String name, String synopsis, String image, String trailer, int year) {
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

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

	public List<Lists> getLists() {
		return lists;
	}

	public void setLists(List<Lists> lists) {
		this.lists = lists;
	}

	public CommentShow getCommentShow() {
		return commentShow;
	}

	public void setCommentShow(CommentShow commentShow) {
		this.commentShow = commentShow;
	}

	public PointShow getPointShow() {
		return pointShow;
	}

	public void setPointShow(PointShow pointShow) {
		this.pointShow = pointShow;
	}
}
