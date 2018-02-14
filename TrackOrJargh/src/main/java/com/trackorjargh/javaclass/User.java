package com.trackorjargh.javaclass;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	private List<Lists> lists;

	public User() {
	}

	public User(String nickname, String password, String email, String image, String type, List<Lists> lists) {
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.image = image;
		this.type = type;
		this.lists = lists;
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
}
