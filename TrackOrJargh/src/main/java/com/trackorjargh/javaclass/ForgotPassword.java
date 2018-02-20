package com.trackorjargh.javaclass;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ForgotPassword {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany
	private List<User> user = new LinkedList<>();
	private String secretAlphanumeric;
	
	public ForgotPassword() {
	}
	
	public ForgotPassword(String secretAlphanumeric) {
		this.secretAlphanumeric = secretAlphanumeric;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

	public String getSecretAlphanumeric() {
		return secretAlphanumeric;
	}

	public void setSecretAlphanumeric(String secretAlphanumeric) {
		this.secretAlphanumeric = secretAlphanumeric;
	}
}
