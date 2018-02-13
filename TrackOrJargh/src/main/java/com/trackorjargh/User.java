package com.trackorjargh;

import java.util.List;

public class User {
	private Integer id;
	private String nickname;
	private String password;
	private String email;
	private List<ListContent> lists;
	private String image;
	
	public User() {
		this.id = null;
		this.nickname = null;
		this.password = null;
		this.email = null;
		this.lists = null;
		this.image = null;
	}
	
	public User(Integer id, String nickname, String password, String email, List<ListContent> lists, String image) {
		this.id = id;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.lists = lists;
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public List<ListContent> getLists() {
		return lists;
	}

	public void setLists(List<ListContent> lists) {
		this.lists = lists;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}	
}
