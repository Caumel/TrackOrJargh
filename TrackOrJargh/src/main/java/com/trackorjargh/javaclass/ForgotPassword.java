package com.trackorjargh.javaclass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ForgotPassword {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private User user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSecretAlphanumeric() {
		return secretAlphanumeric;
	}

	public void setSecretAlphanumeric(String secretAlphanumeric) {
		this.secretAlphanumeric = secretAlphanumeric;
	}
}
