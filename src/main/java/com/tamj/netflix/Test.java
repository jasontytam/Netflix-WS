package com.tamj.netflix;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tamj.netflix.service.fav.entity.FavMovie;
import com.tamj.netflix.service.user.entity.NetflixUser;

public class Test {

	public static void main(String[] args) {
//		testUser();
//		testFavMovie();
		testGetFavList();
		
	}
	
	private static void testUser() {
		NetflixUser newUser = new NetflixUser();
		newUser.setLogin("mjordan");
		newUser.setFirstName("Ron");
		newUser.setLastName("Harper");
		
		ResponseEntity<NetflixUser> responseEntity 
			= new RestTemplate().exchange(
				"http://localhost:8080/netflix/user/add", 
				HttpMethod.POST, 
				new HttpEntity<NetflixUser>(newUser),
				NetflixUser.class);
		
		System.out.println(responseEntity.getStatusCode());
		System.out.println(responseEntity.getBody());
	}
		
		
	private static void testFavMovie() {

		long userId = 2L;
		String netflixId = "60004484";
		String titleName = "The Lord of the Rings: The Return of the King";
		NetflixUser user = new NetflixUser();
		user.setId(userId);
		FavMovie favMovie = new FavMovie();
		favMovie.setUser(user);
		favMovie.setNetflixId(netflixId);
		favMovie.setTitleName(titleName);
		
		ResponseEntity<FavMovie> rspEntity 
			= new RestTemplate().exchange(
				"http://localhost:8080/netflix/movie/fav/add", 
				HttpMethod.POST, 
				new HttpEntity<FavMovie>(favMovie),
				FavMovie.class);
		
		System.out.println(rspEntity.getStatusCode());
		System.out.println(rspEntity.getBody());
	}
	
	private static void testGetFavList() {

		long userId = 3L;
		ResponseEntity<List> rspEntity 
		= new RestTemplate().exchange(
			"http://localhost:8080/netflix/movie/fav/get/{userid}", 
			HttpMethod.GET, 
			null,
			List.class, 
			userId);
	
	System.out.println(rspEntity.getStatusCode());
	System.out.println(rspEntity.getBody());
		
	}

}
