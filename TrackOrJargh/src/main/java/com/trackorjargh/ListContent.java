package com.trackorjargh;

import java.util.List;

public class ListContent {
	private String name;
	private Boolean predefined;
	private List<Content> content;
	

	public ListContent() {
		this.name = null;
		this.predefined = null;
		this.content = null;
	}
	
	public ListContent(String name, Boolean predefined, List<Content> content) {
		this.name = name;
		this.predefined = predefined;
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getPredefined() {
		return predefined;
	}

	public void setPredefined(Boolean predefined) {
		this.predefined = predefined;
	}

	public List<Content> getContent() {
		return content;
	}

	public void setContent(List<Content> content) {
		this.content = content;
	}
	
	

}
