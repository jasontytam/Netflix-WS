package com.tamj.netflix.service.fav.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tamj.netflix.service.user.entity.NetflixUser;

@Entity
public class FavMovie {
//	CREATE TABLE FAV_MOVIE
//	(
//	   FAVID       BIGINT       NOT NULL GENERATED ALWAYS AS IDENTITY,
//	   USERID      BIGINT       NOT NULL,
//	   NETFLIXID   VARCHAR(40)  NOT NULL,
//	   TITLENAME   VARCHAR(100) NOT NULL,
//	   CONSTRAINT  PK_FAV_MOVIE       PRIMARY KEY(FAVID),
//	   CONSTRAINT  FK_FAV_MOVIE_NETFLIX_USER  FOREIGN KEY(USERID) REFERENCES NETFLIX_USER(USERID),
//	   CONSTRAINT  UK_FAV_MOVIE_USERID_NETFLIXID  UNIQUE(USERID, NETFLIXID)
//	);
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="FAVID")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	private NetflixUser user;
	
	@Column(name="NETFLIXID")
	private String netflixId;
	@Column(name="TITLENAME")
	private String titleName;

	public FavMovie() {
		super();
	}

	@Override
	public String toString() {
		return "FavMovie [id=" + id + ", user=" + user + ", netflixId=" + netflixId + ", titleName=" + titleName + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public NetflixUser getUser() {
		return user;
	}

	public void setUser(NetflixUser user) {
		this.user = user;
	}

	public String getNetflixId() {
		return netflixId;
	}

	public void setNetflixId(String netflixId) {
		this.netflixId = netflixId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, netflixId, titleName, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FavMovie other = (FavMovie) obj;
		return id == other.id && Objects.equals(netflixId, other.netflixId)
				&& Objects.equals(titleName, other.titleName) && Objects.equals(user, other.user);
	}
	
	
	
}
