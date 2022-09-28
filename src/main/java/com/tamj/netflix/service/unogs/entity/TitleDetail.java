package com.tamj.netflix.service.unogs.entity;

public class TitleDetail {
//
//	{
//	    "title": "The Mummy",
//	    "maturity_label": "Recommended for ages 16 and up",
//	    "maturity_level": "",
//	    "synopsis": "An ancient princess angers her father and gets mummified. When modern military men find her, they unleash lust, power and the anger of the gods.",
//	    "title_type": "movie",
//	    "default_image": "https://occ-0-114-116.1.nflxso.net/dnm/api/v6/evlCitJPPCVCry0BZlEFb5-QjKc/AAAABRxJxDu43NrIqPKxGIfnKmXxOdiq1eyGkZjSrb6nzgAqWFgP3qaTi82AfmwCbb-ghOK9hsp6_pBtNX1r4vG4_j58Dw.jpg?r=016",
//	    "large_image": "https://occ-0-2773-1490.1.nflxso.net/dnm/api/v6/evlCitJPPCVCry0BZlEFb5-QjKc/AAAABYhIJs3X2qQxFTzQaGYkQix7a5GJHfzVr3xJbPj_5BfiGmLvHr5C4D9RPUAXI6VtB5rf2mWZvspjCMC6mz7qFbNGmB28.jpg?r=78a",
//	    "netflix_id": "80161352",
//	    "start_date": "2018-12-20",
//	    "latest_date": "2022-09-03",
//	    "year": "2017",
//	    "poster": "",
//	    "runtime": "",
//	    "awards": "",
//	    "origin_country": "",
//	    "rating": "",
//	    "alt_id": "tt2345759",
//	    "alt_plot": "",
//	    "alt_metascore": "",
//	    "alt_votes": "163725",
//	    "alt_runtime": "6605",
//	    "alt_image": ""
//	  }
//	
	private String title;
	private String maturity_label;
	private String maturity_level;
	private String synopsis;
	private String title_type;
	private String default_image;
	private String large_image;
	private String netflix_id;
	private String start_date;
	private String latest_date;
	private String year;
	private String poster;
	private String runtime;
	private String awards;
	private String origin_country;
	private String rating;
	private String alt_id;
	private String alt_plot;
	private String alt_metascore;
	private String alt_votes;
	private String alt_runtime;
	private String alt_image;
	
	
	
	@Override
	public String toString() {
		return "TitleDetail [title=" + title + ", maturity_label=" + maturity_label + ", maturity_level="
				+ maturity_level + ", synopsis=" + synopsis + ", title_type=" + title_type + ", default_image="
				+ default_image + ", large_image=" + large_image + ", netflix_id=" + netflix_id + ", start_date="
				+ start_date + ", latest_date=" + latest_date + ", year=" + year + ", poster=" + poster + ", runtime="
				+ runtime + ", awards=" + awards + ", origin_country=" + origin_country + ", rating=" + rating
				+ ", alt_id=" + alt_id + ", alt_plot=" + alt_plot + ", alt_metascore=" + alt_metascore + ", alt_votes="
				+ alt_votes + ", alt_runtime=" + alt_runtime + ", alt_image=" + alt_image + "]";
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMaturity_label() {
		return maturity_label;
	}
	public void setMaturity_label(String maturity_label) {
		this.maturity_label = maturity_label;
	}
	public String getMaturity_level() {
		return maturity_level;
	}
	public void setMaturity_level(String maturity_level) {
		this.maturity_level = maturity_level;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getTitle_type() {
		return title_type;
	}
	public void setTitle_type(String title_type) {
		this.title_type = title_type;
	}
	public String getDefault_image() {
		return default_image;
	}
	public void setDefault_image(String default_image) {
		this.default_image = default_image;
	}
	public String getLarge_image() {
		return large_image;
	}
	public void setLarge_image(String large_image) {
		this.large_image = large_image;
	}
	public String getNetflix_id() {
		return netflix_id;
	}
	public void setNetflix_id(String netflix_id) {
		this.netflix_id = netflix_id;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getLatest_date() {
		return latest_date;
	}
	public void setLatest_date(String latest_date) {
		this.latest_date = latest_date;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public String getAwards() {
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
	}
	public String getOrigin_country() {
		return origin_country;
	}
	public void setOrigin_country(String origin_country) {
		this.origin_country = origin_country;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getAlt_id() {
		return alt_id;
	}
	public void setAlt_id(String alt_id) {
		this.alt_id = alt_id;
	}
	public String getAlt_plot() {
		return alt_plot;
	}
	public void setAlt_plot(String alt_plot) {
		this.alt_plot = alt_plot;
	}
	public String getAlt_metascore() {
		return alt_metascore;
	}
	public void setAlt_metascore(String alt_metascore) {
		this.alt_metascore = alt_metascore;
	}
	public String getAlt_votes() {
		return alt_votes;
	}
	public void setAlt_votes(String alt_votes) {
		this.alt_votes = alt_votes;
	}
	public String getAlt_runtime() {
		return alt_runtime;
	}
	public void setAlt_runtime(String alt_runtime) {
		this.alt_runtime = alt_runtime;
	}
	public String getAlt_image() {
		return alt_image;
	}
	public void setAlt_image(String alt_image) {
		this.alt_image = alt_image;
	}
	
}
