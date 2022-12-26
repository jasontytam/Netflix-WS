package com.tamj.netflix.service.fav;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.defaultanswers.ReturnsEmptyValues;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tamj.netflix.service.fav.entity.FavMovie;
import com.tamj.netflix.service.user.entity.NetflixUser;

@SpringBootTest
//@TestInstance(Lifecycle.PER_CLASS)
//@TestMethodOrder(MethodOrderer.MethodName.class)
public class FavouriteServiceTest {
	
	@Mock
	private EntityManager mockEM;
	
	@Autowired
//	@InjectMocks
	private FavouriteService favService;
	
//	@Autowired
//	private UserService userService;
	
//	private NetflixUser testUser;
//
    public static <T> T fluentMock(final Class<T> type) {
        return Mockito.mock(type, Mockito.withSettings().defaultAnswer(
                new ReturnsEmptyValues() {
                    @Override
                    public Object answer(InvocationOnMock invocation) {
                        Object defaultReturnValue = super.answer(invocation);
                        if (type.equals(invocation.getMethod().getReturnType())) {
                            return invocation.getMock();
                        } else {
                            return defaultReturnValue;
                        }
                    }
                }));
    }
	
	@BeforeEach
	void setUp() {
		this.favService.setEntityManager(this.mockEM);
		
//		testUser = new NetflixUser();
//		testUser.setId(1L);
//		testUser.setLogin("unittestlogin");
//		testUser.setFirstName("Unit");
//		testUser.setLastName("Test");
		
//		this.testUser = this.userSvc.add(this.testUser.getLogin(), this.testUser.getFirstName(), this.testUser.getLastName());
	}
	
	@Test
	void test1Context() {
		assertNotNull(favService);
	}
	
	@Test
	void getFavList() {
//		INSERT INTO FAV_MOVIE VALUES(DEFAULT, '2','60004484','The Lord of the Rings: The Return of the King');
		NetflixUser expUser = new NetflixUser();
		expUser.setId(2);
		FavMovie expFavMov = new FavMovie();
		expFavMov.setNetflixId("60004484");
		expFavMov.setTitleName("The Lord of the Rings: The Return of the King");
		expFavMov.setUser(expUser);
		
		List<FavMovie> expFavMovList = new ArrayList<FavMovie>();
		expFavMovList.add(expFavMov);
		
//		Query query = Mockito.mock(Query.class);
////		Mockito.when(query.setParameter("userid", expUser.getId())).thenReturn(query);
////		Mockito.when(query.getResultList()).thenReturn(expFavMovList);
//		Mockito.when(query.setParameter(Mockito.anyString(), Mockito.anyString())).then(
//				new Answer<Query>() {
//					public Query answer(InvocationOnMock invocation) {
//						Mockito.when(query.getResultList()).thenReturn(expFavMovList);
//						return query;
//					}
//				}
//				);
		Query query = fluentMock(Query.class);
		Mockito.when(query.getResultList()).thenReturn(expFavMovList);
		
		Mockito.when(this.mockEM.createQuery(Mockito.anyString())).thenReturn(query);
		
		List<FavMovie> resultList = this.favService.getList(expUser.getId());
		assertEquals(expFavMovList, resultList);
	}
	
	@Test
	void addFav() {
		long userId = 1000L;
		String netflixId = "11111";
		String titleName = "Test TItle";
		
		NetflixUser user = new NetflixUser();
		user.setId(userId);
		
		FavMovie expFavMovie = new FavMovie();
		expFavMovie.setUser(user);
		expFavMovie.setNetflixId(netflixId);
		expFavMovie.setTitleName(titleName);
		
		FavMovie resultFavMovie = this.favService.addToFavourite(userId, netflixId, titleName);
		
		assertNotNull(resultFavMovie);
		assertNotNull(resultFavMovie.getUser());
		assertEquals(expFavMovie, resultFavMovie);
	}

//	@Test
//	void test2AddFav() {
//		FavMovie favMovie = null;
////		try {
////			favMovie = this.favService.addToFavourite(-1, "ABCD", "ABCD");
////		} finally {
////			assertNull(favMovie);
////		}
//		
//		this.testUser = this.userService.add(this.testUser.getLogin(), this.testUser.getFirstName(), this.testUser.getLastName());
//		
////		try {
////			favMovie = this.favService.addToFavourite(this.testUser.getId(), null, null);
////		} finally {
////			assertNull(favMovie);
////		}
//		
//		favMovie = this.favService.addToFavourite(this.testUser.getId(), "1234", "Test Movie");
//		assertNotNull(favMovie);
//		assertNotNull(favMovie.getUser());
//		
////		try {
////			favMovie = this.favService.addToFavourite(this.testUser.getId(), "1234", "Test Movie");
////		} finally {
////			assertNull(favMovie);
////		}
//		
//		favMovie = this.favService.addToFavourite(this.testUser.getId(), "1235", "Test Movie 2");
//		assertNotNull(favMovie);
//		assertNotNull(favMovie.getUser());
//	}
	
//	@Test
//	void test3GetFavList() {
//		List<FavMovie> favList = this.favService.getList(-1);
////		assertNotNull(favList);
////		assertEquals(favList.size(), 0);
//		assertNull(favList);
//		
//		favList = this.favService.getList(this.testUser.getId());
//		assertNotNull(favList);
//		assertEquals(favList.size(), 2);
//	}
	
//	@Test
//	void test4RemoveFav() {
//		
//		this.favService.removeFavourite(this.testUser.getId(), "1234");
//		
//		List<FavMovie> favList = this.favService.getList(this.testUser.getId());
//		assertNotNull(favList);
//		assertEquals(favList.size(), 1);
//		
//		this.favService.removeFavourite(this.testUser.getId(), "1235");
//		
//		favList = this.favService.getList(this.testUser.getId());
////		assertNotNull(favList);
////		assertEquals(favList.size(), 0);
//		assertNull(favList);
//		
//		this.userService.delete(this.testUser);
//		
//	}
}
