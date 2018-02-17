package com.trackorjargh.javaclass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String password;
	private String email;
	private String image;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	@OneToMany
	private List<Lists> lists = new LinkedList<>();
	
	@OneToOne(mappedBy="user")
	private CommentBook commentBook;
	
	@OneToOne(mappedBy="user")
	private PointBook pointBook;
	
	@OneToOne(mappedBy="user")
	private CommentFilm commentFilm;
	
	@OneToOne(mappedBy="user")
	private PointFilm pointFilm;
	
	@OneToOne(mappedBy="user") 
	private CommentShow commentShow;
	
	@OneToOne(mappedBy="user")
	private PointShow pointShow;

	public User() {
	}

	public User(String nickname, String password, String email, String image, String... roles) {
		this.name = nickname;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.email = email;
		this.image = image;
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return name;
	}

	public void setNickname(String nickname) {
		this.name = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
