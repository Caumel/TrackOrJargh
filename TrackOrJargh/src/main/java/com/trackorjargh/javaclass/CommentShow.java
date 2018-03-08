package com.trackorjargh.javaclass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;
import com.trackorjargh.javaclass.CommentFilm.BasicInformation;

@Entity
public class CommentShow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Shows show;
	
	public interface BasicInformation {}
	
	@OneToOne
	@JsonView(BasicInformation.class)
	private User user;
	
	@JsonView(BasicInformation.class)
	private String comment;

	public CommentShow() {
	}

	public CommentShow(String comment) {
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Shows getShow() {
		return show;
	}

	public void setShow(Shows show) {
		this.show = show;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public PreparateMessageShow preparateShowMessage() {
		return new PreparateMessageShow(this.id, this.user.getName(), this.comment);
	}
}
