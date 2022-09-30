package com.tamj.netflix.service.unogs.entity;

public abstract class Title {

	private String title;
	private String title_type;
	private String synopsis;
	private String rating;
	private String year;
	private String runtime;
	private String poster;
	private String netflix_id;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle_type() {
		return title_type;
	}
	public void setTitle_type(String title_type) {
		this.title_type = title_type;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	public String getNetflix_id() {
		return netflix_id;
	}
	public void setNetflix_id(String netflix_id) {
		this.netflix_id = netflix_id;
	}
	
}
