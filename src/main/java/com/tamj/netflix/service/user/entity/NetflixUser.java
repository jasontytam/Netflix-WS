package com.tamj.netflix.service.user.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NetflixUser {
//	CREATE TABLE USER
//	(
//	   USERID      BIGINT       NOT NULL GENERATED ALWAYS AS IDENTITY,
//	   LOGIN       VARCHAR(20)  NOT NULL,
//	   FIRSTNAME   VARCHAR(40)  NOT NULL,
//	   LASTNAME    VARCHAR(40)  NOT NULL,
//	   CONSTRAINT  PK_USER            PRIMARY KEY(USERID)
//	   CONSTRAINT  UK_NETFLIX_USER_LOGIN  UNIQUE(LOGIN)
//	);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USERID")
	private long id;
	@Column(name="LOGIN")
	private String login;
	@Column(name="FIRSTNAME")
	private String firstName;
	@Column(name="LASTNAME")
	private String lastName;
	
	public NetflixUser() {
		super();
	}
	
	public NetflixUser(String login, String firstName, String lastName) {
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName, login);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NetflixUser other = (NetflixUser) obj;
		return Objects.equals(firstName, other.firstName) && id == other.id && Objects.equals(lastName, other.lastName)
				&& Objects.equals(login, other.login);
	}
	
	
	
}
