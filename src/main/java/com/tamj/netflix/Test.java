package com.tamj.netflix;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tamj.netflix.service.user.entity.NetflixUser;

public class Test {

	public static void main(String[] args) {
		NetflixUser newUser = new NetflixUser();
		newUser.setLogin("rharper");
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

}
