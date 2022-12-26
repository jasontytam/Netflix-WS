package com.tamj.netflix.service.fav;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.tamj.netflix.service.fav.entity.FavMovie;
import com.tamj.netflix.service.fav.repo.FavMovieRepository;
import com.tamj.netflix.service.user.entity.NetflixUser;

@SpringBootTest
public class FavouriteServiceRepoImplTest {
	
	@Mock
	private FavMovieRepository favMovieRepo;
	
	@InjectMocks
	private FavouriteServiceRepoImpl favSvcRepoImpl;
	
	@Test
	void getFavMovieListByUserId_OneFavMovie() {
		long userId = 2L;
		String netflixId = "60004484";
		String titleName = "The Lord of the Rings: The Return of the King";
		
		NetflixUser expectedUser = new NetflixUser();
		expectedUser.setId(userId);

		FavMovie expectedFavMovie1 = new FavMovie();
		expectedFavMovie1.setUser(expectedUser);
		expectedFavMovie1.setNetflixId(netflixId);
		expectedFavMovie1.setTitleName(titleName);
		
		List<FavMovie> expectedResult = new ArrayList<FavMovie>();
		expectedResult.add(expectedFavMovie1);
		
		Mockito.when(this.favMovieRepo.findByUserId(userId)).thenReturn(expectedResult);
		
		List<FavMovie> actualResult = this.favSvcRepoImpl.getList(userId);
		
		Assertions.assertEquals(expectedResult, actualResult);
		
		Mockito.verify(this.favMovieRepo).findByUserId(userId);
	}
	
	@Test
	void getFavMovieListByUserId_NoFavMovie() {
		long userId = 2L;
		
		Mockito.when(this.favMovieRepo.findByUserId(userId)).thenReturn(new ArrayList<FavMovie>());
		
		List<FavMovie> actualResult = this.favSvcRepoImpl.getList(userId);

		Assertions.assertEquals(0, actualResult.size());
		
		Mockito.verify(this.favMovieRepo).findByUserId(userId);
	}
	
	@Test
	void addFav_Success() {
		long userId = 2L;
		String netflixId = "60004484";
		String titleName = "The Lord of the Rings: The Return of the King";
		
		NetflixUser expectedUser = new NetflixUser();
		expectedUser.setId(userId);

		FavMovie expectedFavMovie1 = new FavMovie();
		expectedFavMovie1.setUser(expectedUser);
		expectedFavMovie1.setNetflixId(netflixId);
		expectedFavMovie1.setTitleName(titleName);
		
		Mockito.when(this.favMovieRepo.save(expectedFavMovie1)).thenReturn(expectedFavMovie1);
		
		FavMovie actualResult = this.favSvcRepoImpl.addToFavourite(userId, netflixId, titleName);
		
		Assertions.assertEquals(expectedFavMovie1, actualResult);
		
		Mockito.verify(this.favMovieRepo).save(expectedFavMovie1);
	}
	
	@Test
	void addFav_Failure() {
		long userId = 2L;
		String netflixId = "60004484";
		String titleName = "The Lord of the Rings: The Return of the King";
		
		NetflixUser expectedUser = new NetflixUser();
		expectedUser.setId(userId);

		FavMovie expectedFavMovie1 = new FavMovie();
		expectedFavMovie1.setUser(expectedUser);
		expectedFavMovie1.setNetflixId(netflixId);
		expectedFavMovie1.setTitleName(titleName);
		
		Mockito.when(this.favMovieRepo.save(expectedFavMovie1)).thenThrow(DataIntegrityViolationException.class);
		
		FavMovie actualResult = this.favSvcRepoImpl.addToFavourite(userId, netflixId, titleName);

		Assertions.assertNull(actualResult);
		
		Mockito.verify(this.favMovieRepo).save(expectedFavMovie1);
	}
	
	@Test
	void removeFav_Success() {
		long userId = 2L;
		String netflixId = "60004484";
		
		NetflixUser expectedUser = new NetflixUser();
		expectedUser.setId(userId);

		FavMovie expectedFavMovie1 = new FavMovie();
		expectedFavMovie1.setUser(expectedUser);
		expectedFavMovie1.setNetflixId(netflixId);
		
		List<FavMovie> expectedFindList = new ArrayList<FavMovie>();
		expectedFindList.add(expectedFavMovie1);
		
		Mockito.when(this.favMovieRepo.findByUserIdAndNetflixId(userId, netflixId)).thenReturn(expectedFindList);
		
		boolean result = this.favSvcRepoImpl.removeFavourite(userId, netflixId);
		
		Assertions.assertEquals(true, result);
		
		Mockito.verify(this.favMovieRepo).findByUserIdAndNetflixId(userId, netflixId);
	}
	
	@Test
	void removeFav_Failure() {
		long userId = 2L;
		String netflixId = "60004484";
		
		NetflixUser expectedUser = new NetflixUser();
		expectedUser.setId(userId);

		FavMovie expectedFavMovie1 = new FavMovie();
		expectedFavMovie1.setUser(expectedUser);
		expectedFavMovie1.setNetflixId(netflixId);
		
		List<FavMovie> expectedFindList = new ArrayList<FavMovie>();
		expectedFindList.add(expectedFavMovie1);
		
		Mockito.when(this.favMovieRepo.findByUserIdAndNetflixId(userId, netflixId)).thenReturn(null);
		
		boolean result = this.favSvcRepoImpl.removeFavourite(userId, netflixId);
		
		Assertions.assertEquals(false, result);
		
		Mockito.verify(this.favMovieRepo).findByUserIdAndNetflixId(userId, netflixId);
	}
	
}
