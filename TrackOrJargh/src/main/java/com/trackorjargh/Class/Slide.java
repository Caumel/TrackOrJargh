package com.trackorjargh.Class;

public class Slide {
	private String title;
	private String content;
	private String image;
	private String button;
	private String link;
	private String active;
	private boolean firstInList;

	public Slide(String title, String content, String image, String button, String link, String active) {
		this.title = title;
		this.content = content;
		this.image = image;
		this.button = button;
		this.link = link;
		this.active = active;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isPositionList() {
		return firstInList;
	}

	public void setFirstInList(boolean isFirst) {
		this.firstInList = isFirst;
	}
}
