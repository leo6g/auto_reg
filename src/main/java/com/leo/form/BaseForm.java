package com.leo.form;

public class BaseForm {

	private int rows;
	private int page;
	private int limit;
	private int start;
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
		
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
		this.limit = rows;
		this.start = (this.page-1)*this.rows;
	}

	
	

}
