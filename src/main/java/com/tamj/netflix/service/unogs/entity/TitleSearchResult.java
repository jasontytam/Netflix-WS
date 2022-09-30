package com.tamj.netflix.service.unogs.entity;

public class TitleSearchResult extends Title {

//    {
//        "title": "The Scorpion King 3: Battle for Redemption",
//        "img": "https://occ-0-2717-360.1.nflxso.net/art/385bd/8d2c562fbec2260ce2a0cc0e088955ee3b4385bd.jpg",
//        "title_type": "movie",
//        "netflix_id": 70221031,
//        "synopsis": "The Mummy franchise expands with this prequel-sequel that follows fallen king Mathayus into his new life as a deadly assassin.",
//        "rating": "3.142248",
//        "year": "2011",
//        "runtime": "6319",
//        "imdb_id": "tt1781896",
//        "poster": "http://ia.media-imdb.com/images/M/MV5BMjMyNTM5MjA5OV5BMl5BanBnXkFtZTcwNzM2ODIyNw@@._V1_SX300.jpg",
//        "top250": 0,
//        "top250tv": 0,
//        "title_date": "2015-04-14"
//      }

	private String img;
	private String imdb_id;
	private String top250;
	private String top250tv;
	private String title_date;
	
	@Override
	public String toString() {
		return "TitleSearchResult [img=" + img + ", imdb_id=" + imdb_id + ", top250=" + top250 + ", top250tv="
				+ top250tv + ", title_date=" + title_date + ", getTitle()=" + getTitle() + ", getTitle_type()="
				+ getTitle_type() + ", getSynopsis()=" + getSynopsis() + ", getRating()=" + getRating() + ", getYear()="
				+ getYear() + ", getNetflix_id()=" + getNetflix_id() + ", getRuntime()=" + getRuntime() + ", getPoster()=" + getPoster() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getImdb_id() {
		return imdb_id;
	}
	public void setImdb_id(String imdb_id) {
		this.imdb_id = imdb_id;
	}
	public String getTop250() {
		return top250;
	}
	public void setTop250(String top250) {
		this.top250 = top250;
	}
	public String getTop250tv() {
		return top250tv;
	}
	public void setTop250tv(String top250tv) {
		this.top250tv = top250tv;
	}
	public String getTitle_date() {
		return title_date;
	}
	public void setTitle_date(String title_date) {
		this.title_date = title_date;
	}
	
	

}
