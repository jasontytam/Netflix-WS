package com.tamj.netflix.service.fav;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tamj.netflix.service.fav.entity.FavMovie;
import com.tamj.netflix.service.user.UserService;
import com.tamj.netflix.service.user.entity.NetflixUser;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)
public class FavouriteServiceTest {
	
	@Autowired
	private FavouriteService favService;
	
	@Autowired
	private UserService userService;
	
	private NetflixUser testUser;
	
	@BeforeAll
	public void setUp() {
		testUser = new NetflixUser();
		testUser.setLogin("unittestlogin");
		testUser.setFirstName("Unit");
		testUser.setLastName("Test");
		
//		this.testUser = this.userSvc.add(this.testUser.getLogin(), this.testUser.getFirstName(), this.testUser.getLastName());
	}
	
	@Test
	void test1Context() {
		assertNotNull(favService);
	}

	@Test
	void test2AddFav() {
		FavMovie favMovie = null;
//		try {
//			favMovie = this.favService.addToFavourite(-1, "ABCD", "ABCD");
//		} finally {
//			assertNull(favMovie);
//		}
		
		this.testUser = this.userService.add(this.testUser.getLogin(), this.testUser.getFirstName(), this.testUser.getLastName());
		
//		try {
//			favMovie = this.favService.addToFavourite(this.testUser.getId(), null, null);
//		} finally {
//			assertNull(favMovie);
//		}
		
		favMovie = this.favService.addToFavourite(this.testUser.getId(), "1234", "Test Movie");
		assertNotNull(favMovie);
		assertNotNull(favMovie.getUser());
		
//		try {
//			favMovie = this.favService.addToFavourite(this.testUser.getId(), "1234", "Test Movie");
//		} finally {
//			assertNull(favMovie);
//		}
		
		favMovie = this.favService.addToFavourite(this.testUser.getId(), "1235", "Test Movie 2");
		assertNotNull(favMovie);
		assertNotNull(favMovie.getUser());
	}
	
	@Test
	void test3GetFavList() {
		List<FavMovie> favList = this.favService.getList(-1);
//		assertNotNull(favList);
//		assertEquals(favList.size(), 0);
		assertNull(favList);
		
		favList = this.favService.getList(this.testUser.getId());
		assertNotNull(favList);
		assertEquals(favList.size(), 2);
	}
	
	@Test
	void test4RemoveFav() {
		
		this.favService.removeFavourite(this.testUser.getId(), "1234");
		
		List<FavMovie> favList = this.favService.getList(this.testUser.getId());
		assertNotNull(favList);
		assertEquals(favList.size(), 1);
		
		this.favService.removeFavourite(this.testUser.getId(), "1235");
		
		favList = this.favService.getList(this.testUser.getId());
//		assertNotNull(favList);
//		assertEquals(favList.size(), 0);
		assertNull(favList);
		
		this.userService.delete(this.testUser);
		
	}
}
