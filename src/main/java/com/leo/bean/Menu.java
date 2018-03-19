package com.leo.bean;

public class Menu {
	private String url;
	private Integer index;
	private String title;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Menu(String url, Integer index, String title) {
		super();
		this.url = url;
		this.index = index;
		this.title = title;
	}
	public Menu() {
		super();
	}
	
}
