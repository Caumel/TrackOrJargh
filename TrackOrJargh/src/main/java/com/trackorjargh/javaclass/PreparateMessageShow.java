package com.trackorjargh.javaclass;

public class PreparateMessageShow {
	private String name;
	private String message;
	
	public PreparateMessageShow() {
	}

	public PreparateMessageShow(String name, String message) {
		this.name = name;
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
