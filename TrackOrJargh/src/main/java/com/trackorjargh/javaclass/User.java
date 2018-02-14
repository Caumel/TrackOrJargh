package com.trackorjargh.javaclass;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nickname;
	private String password;
	private String email;
	private String image;
	private String type;
	
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

	public User(String nickname, String password, String email, String image, String type) {
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.image = image;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
}
