package com.trackorjargh;

public class Film {
	private Integer id;
	private String name;
	private String synopsis;	
	private String image;
	private int year;
	private boolean positionList;
	
	public Film(Integer id, String name, String synopsis, String image, int year) {
		this.id = id;
		this.name = name;
		this.synopsis = synopsis;
		this.image = image;
		this.year = year;
		
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isPositionList() {
		return positionList;
	}

	public void setPositionList(boolean positionList) {
		this.positionList = positionList;
	}
	
}
