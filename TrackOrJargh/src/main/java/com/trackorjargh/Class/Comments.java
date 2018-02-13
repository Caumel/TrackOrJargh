package com.trackorjargh.Class;

public class Comments {
	public User user;
	public String comment;
	public float points;
	public boolean report;
	
	public Comments() {
		this.user = null;
		this.comment = null;
		this.points = 0;
		this.report = false;
	}

	public Comments(User user, String comment, float points, boolean report) {
		this.user = user;
		this.comment = comment;
		this.points = points;
		this.report = report;
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

	public float getPoints() {
		return points;
	}

	public void setPoints(float points) {
		this.points = points;
	}

	public boolean isReport() {
		return report;
	}

	public void setReport(boolean report) {
		this.report = report;
	}
}
