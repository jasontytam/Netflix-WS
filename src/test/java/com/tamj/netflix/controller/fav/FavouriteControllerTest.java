package com.tamj.netflix.controller.fav;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tamj.netflix.service.fav.FavouriteService;
import com.tamj.netflix.service.fav.entity.FavMovie;
import com.tamj.netflix.service.user.entity.NetflixUser;

@ExtendWith(MockitoExtension.class)
public class FavouriteControllerTest {

	@Mock
	private FavouriteService mockFavSvc;
	
	@InjectMocks
	private FavouriteController favController;
	
	@Test
	void getFavListByUserId_OneRecord() {
		long userId = 2L;
		String netflixId = "60004484";
		String titleName = "The Lord of the Rings: The Return of the King";
		
		NetflixUser user = new NetflixUser();
		user.setId(userId);
		
		FavMovie favMovie1 = new FavMovie();
		favMovie1.setUser(user);
		favMovie1.setNetflixId(netflixId);
		favMovie1.setTitleName(titleName);

		List<FavMovie> expFavMovieList = new ArrayList<FavMovie>();
		expFavMovieList.add(favMovie1);
		
		Mockito.when(this.mockFavSvc.getList(userId)).thenReturn(expFavMovieList);
		
		List<FavMovie> actualMovieList = this.favController.getFavMovieListByUserId(userId);
		
		Assertions.assertEquals(expFavMovieList, actualMovieList);
		
		Mockito.verify(this.mockFavSvc).getList(userId);
	}
	
	@Test
	void getFavListByUserId_NoRecord() {
		long userId = 2L;

		List<FavMovie> expFavMovieList = new ArrayList<FavMovie>();
		
		Mockito.when(this.mockFavSvc.getList(userId)).thenReturn(expFavMovieList);
		
		List<FavMovie> actualMovieList = this.favController.getFavMovieListByUserId(userId);
		
		Assertions.assertEquals(expFavMovieList, actualMovieList);
		
		Mockito.verify(this.mockFavSvc).getList(userId);
	}
	
	@Test
	void addFavMovie_Success() {
		long userId = 2L;
		String netflixId = "60004484";
		String titleName = "The Lord of the Rings: The Return of the King";
		
		NetflixUser user = new NetflixUser();
		user.setId(userId);
		
		FavMovie favMovie1 = new FavMovie();
		favMovie1.setId(3L);
		favMovie1.setUser(user);
		favMovie1.setNetflixId(netflixId);
		favMovie1.setTitleName(titleName);
		
		Mockito.when(this.mockFavSvc.addToFavourite(userId, netflixId, titleName)).thenReturn(favMovie1);
		
		ResponseEntity<?> responseEntity = this.favController.addFavMovie(favMovie1);
		
		Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		Assertions.assertEquals(favMovie1, responseEntity.getBody());
		
		Mockito.verify(this.mockFavSvc).addToFavourite(userId, netflixId, titleName);
	}
	
	@Test
	void addFavMovie_Failure_Data() {
		FavMovie expectedFavMovie1 = null;
		
		ResponseEntity<?> responseEntity = this.favController.addFavMovie(expectedFavMovie1);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	@Test
	void addFavMovie_Failure_Duplicate() {
		long userId = 2L;
		String netflixId = "60004484";
		String titleName = "The Lord of the Rings: The Return of the King";
		
		NetflixUser user = new NetflixUser();
		user.setId(userId);
		
		FavMovie favMovie1 = new FavMovie();
		favMovie1.setUser(user);
		favMovie1.setNetflixId(netflixId);
		favMovie1.setTitleName(titleName);
		
		Mockito.when(this.mockFavSvc.addToFavourite(userId, netflixId, titleName)).thenReturn(null);
		
		ResponseEntity<?> responseEntity = this.favController.addFavMovie(favMovie1);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		
		Mockito.verify(this.mockFavSvc).addToFavourite(userId, netflixId, titleName);
	}
	
	@Test
	void addFavMovie_Failure_ServiceException() {
		long userId = 2L;
		String netflixId = "60004484";
		String titleName = "The Lord of the Rings: The Return of the King";
		
		NetflixUser user = new NetflixUser();
		user.setId(userId);
		
		FavMovie favMovie1 = new FavMovie();
		favMovie1.setUser(user);
		favMovie1.setNetflixId(netflixId);
		favMovie1.setTitleName(titleName);
		
		Mockito.when(this.mockFavSvc.addToFavourite(userId, netflixId, titleName)).thenThrow(RuntimeException.class);
		
		ResponseEntity<?> responseEntity = this.favController.addFavMovie(favMovie1);
		
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		
		Mockito.verify(this.mockFavSvc).addToFavourite(userId, netflixId, titleName);
	}
	
	@Test
	void removeFavMovie_Success() {
		long userId = 2L;
		String netflixId = "60004484";
		String titleName = "The Lord of the Rings: The Return of the King";
		
		NetflixUser user = new NetflixUser();
		user.setId(userId);
		
		FavMovie favMovie1 = new FavMovie();
		favMovie1.setUser(user);
		favMovie1.setNetflixId(netflixId);
		favMovie1.setTitleName(titleName);
		
		Mockito.when(this.mockFavSvc.removeFavourite(userId, netflixId)).thenReturn(true);
		
		ResponseEntity<?> responseEntity = this.favController.removeFavMovie(favMovie1);
		
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		Mockito.verify(this.mockFavSvc).removeFavourite(userId, netflixId);
	}
	
	@Test
	void removeFavMovie_Failure_Data() {
		FavMovie favMovie1 = null;
		
		ResponseEntity<?> responseEntity = this.favController.removeFavMovie(favMovie1);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	@Test
	void removeFavMovie_Failure_NoRecord() {
		long userId = 2L;
		String netflixId = "60004484";
		String titleName = "The Lord of the Rings: The Return of the King";
		
		NetflixUser user = new NetflixUser();
		user.setId(userId);
		
		FavMovie favMovie1 = new FavMovie();
		favMovie1.setUser(user);
		favMovie1.setNetflixId(netflixId);
		favMovie1.setTitleName(titleName);
		
		Mockito.when(this.mockFavSvc.removeFavourite(userId, netflixId)).thenReturn(false);
		
		ResponseEntity<?> responseEntity = this.favController.removeFavMovie(favMovie1);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		
		Mockito.verify(this.mockFavSvc).removeFavourite(userId, netflixId);
	}
	
	@Test
	void removeFavMovie_Failure_ServiceException() {
		long userId = 2L;
		String netflixId = "60004484";
		String titleName = "The Lord of the Rings: The Return of the King";
		
		NetflixUser user = new NetflixUser();
		user.setId(userId);
		
		FavMovie favMovie1 = new FavMovie();
		favMovie1.setUser(user);
		favMovie1.setNetflixId(netflixId);
		favMovie1.setTitleName(titleName);
		
		Mockito.when(this.mockFavSvc.removeFavourite(userId, netflixId)).thenThrow(RuntimeException.class);
		
		ResponseEntity<?> responseEntity = this.favController.removeFavMovie(favMovie1);
		
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		
		Mockito.verify(this.mockFavSvc).removeFavourite(userId, netflixId);
	}
}
