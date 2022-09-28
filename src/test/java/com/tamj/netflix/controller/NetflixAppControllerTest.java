package com.tamj.netflix.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tamj.netflix.service.unogs.NetflixSearchService;
import com.tamj.netflix.service.user.UserService;
import com.tamj.netflix.service.user.entity.NetflixUser;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NetflixAppControllerTest {

//	@LocalServerPort
//	private int port;

//	@Autowired
//	private TestRestTemplate testRestTemplate;
	
	@Mock
	private UserService mockUserSvc;
	@Mock
	private NetflixSearchService mockNfxSearchSvc;
	
//	@Autowired
	@InjectMocks
	private NetflixAppController controller;
	
	@Test
	void testContext() {
		assertNotNull(this.controller);
	}

//	@Test
//	public void testAddPost() throws Exception {
//		NetflixUser newUser = new NetflixUser();
//		newUser.setLogin("skerr");
//		newUser.setFirstName("Steve");
//		newUser.setLastName("Kerr");
//		
//		ResponseEntity<NetflixUser> responseEntity 
//			= this.testRestTemplate.exchange(
//				"http://localhost:" + port + "/netflix/user/add", 
//				HttpMethod.POST, 
//				new HttpEntity<NetflixUser>(newUser),
//				NetflixUser.class);
//		
//		assertNotNull(responseEntity);
//		assertNotNull(responseEntity.getBody());
//		assertNotEquals(responseEntity.getBody().getId(), 0);
//		
//	}
	
	@Test
	void testAddUser() {
		NetflixUser steveKerr = new NetflixUser();
		steveKerr.setLogin("skerr");
		steveKerr.setFirstName("Steve");
		steveKerr.setLastName("Kerr");
		
		NetflixUser scottiePippen = new NetflixUser();
		scottiePippen.setLogin("spippen");
		scottiePippen.setFirstName("Scottie");
		scottiePippen.setLastName("Pippen");
		
		NetflixUser addedSteveKerr = new NetflixUser();
		addedSteveKerr.setId(4);
		addedSteveKerr.setLogin("skerr");
		addedSteveKerr.setFirstName("Steve");
		addedSteveKerr.setLastName("Kerr");
		
		Mockito.when(this.mockUserSvc.add(steveKerr.getLogin(), steveKerr.getFirstName(), steveKerr.getLastName())).thenReturn(addedSteveKerr);
		Mockito.when(this.mockUserSvc.add(scottiePippen.getLogin(), scottiePippen.getFirstName(), scottiePippen.getLastName())).thenReturn(null);
		
		ResponseEntity<NetflixUser> responseEntity = this.controller.addUser(steveKerr);
		assertNotNull(responseEntity);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
		assertEquals(responseEntity.getBody(), addedSteveKerr);
		
		responseEntity = this.controller.addUser(scottiePippen);
		assertNotNull(responseEntity);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
		assertNull(responseEntity.getBody());
	}
	
//	@Test
//	void testUpdateUser() {
//		NetflixUser scottiePippen = this.controller.getUserByLogin("spippen");
//		scottiePippen.setFirstName("Scotty");
//		
//		
//	}
}
