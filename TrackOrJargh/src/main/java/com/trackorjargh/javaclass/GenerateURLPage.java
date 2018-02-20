package com.trackorjargh.javaclass;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

public class GenerateURLPage {
	private HttpServletRequest request;
	private User user;
	
	public GenerateURLPage() {
	}
	
	public GenerateURLPage(HttpServletRequest request, User user) {
		this.request = request;
		this.user = user;
	}
	
	public String generateURLActivateAccount() {
		String url = "";
		
		try {
			URL urlPage = new URL(this.request.getRequestURL().toString());
			url = urlPage.getProtocol() + "://" +  urlPage.getHost() + ":" + urlPage.getPort() + "/activarusuario/" + this.user.getName();

		} catch (MalformedURLException exception) {
			exception.printStackTrace();
		}
		
		return url;
	}
	
	public String generateURLChangePass() {
		String url = "";
		
		try {
			URL urlPage = new URL(this.request.getRequestURL().toString());
			url = urlPage.getProtocol() + "://" +  urlPage.getHost() + ":" + urlPage.getPort() + "/cambiarcontra/" + this.user.getName();

		} catch (MalformedURLException exception) {
			exception.printStackTrace();
		}
		
		return url;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
