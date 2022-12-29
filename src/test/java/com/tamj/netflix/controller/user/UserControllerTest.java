package com.tamj.netflix.controller.user;

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

import com.tamj.netflix.service.user.UserService;
import com.tamj.netflix.service.user.entity.NetflixUser;

//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

//	@LocalServerPort
//	private int port;

//	@Autowired
//	private TestRestTemplate testRestTemplate;
	
	@Mock
	private UserService mockUserSvc;
	
	@InjectMocks
	private UserController userController = new UserController();

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
	void addUser_Success() {
		long userId = 2L;
		String login = "spippen";
		String firstName = "Scottie";
		String lastName = "Pippen";
		
		NetflixUser reqBody = new NetflixUser();
		reqBody.setLogin(login);
		reqBody.setFirstName(firstName);
		reqBody.setLastName(lastName);
		
		NetflixUser expUser = new NetflixUser();
		expUser.setId(userId);
		expUser.setLogin(login);
		expUser.setFirstName(firstName);
		expUser.setLastName(lastName);

		Mockito.when(this.mockUserSvc.add(reqBody.getLogin(), reqBody.getFirstName(), reqBody.getLastName())).thenReturn(expUser);
		
		ResponseEntity<?> responseEntity = this.userController.addUser(reqBody);
		
		Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		Assertions.assertEquals(expUser, responseEntity.getBody());
		
		Mockito.verify(this.mockUserSvc).add(reqBody.getLogin(), reqBody.getFirstName(), reqBody.getLastName());
	}
	
	@Test
	void addUser_Failure_Data() {
		String login = null;
		String firstName = null;
		String lastName = null;
		
		NetflixUser reqBody = new NetflixUser();
		reqBody.setLogin(login);
		reqBody.setFirstName(firstName);
		reqBody.setLastName(lastName);
		
		ResponseEntity<?> responseEntity = this.userController.addUser(reqBody);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	@Test
	void addUser_Failure_Duplicate() {
		long userId = 2L;
		String login = "spippen";
		String firstName = "Scottie";
		String lastName = "Pippen";
		
		NetflixUser reqBody = new NetflixUser();
		reqBody.setLogin(login);
		reqBody.setFirstName(firstName);
		reqBody.setLastName(lastName);
		
		NetflixUser expUser = new NetflixUser();
		expUser.setId(userId);
		expUser.setLogin(login);
		expUser.setFirstName(firstName);
		expUser.setLastName(lastName);

		Mockito.when(this.mockUserSvc.add(reqBody.getLogin(), reqBody.getFirstName(), reqBody.getLastName())).thenReturn(null);
		
		ResponseEntity<?> responseEntity = this.userController.addUser(reqBody);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		
		Mockito.verify(this.mockUserSvc).add(reqBody.getLogin(), reqBody.getFirstName(), reqBody.getLastName());
		
	}
	
	@Test
	void addUser_Failure_ServiceException() {
		long userId = 2L;
		String login = "spippen";
		String firstName = "Scottie";
		String lastName = "Pippen";
		
		NetflixUser reqBody = new NetflixUser();
		reqBody.setLogin(login);
		reqBody.setFirstName(firstName);
		reqBody.setLastName(lastName);
		
		NetflixUser expUser = new NetflixUser();
		expUser.setId(userId);
		expUser.setLogin(login);
		expUser.setFirstName(firstName);
		expUser.setLastName(lastName);

		Mockito.when(this.mockUserSvc.add(reqBody.getLogin(), reqBody.getFirstName(), reqBody.getLastName())).thenThrow(RuntimeException.class);
		
		ResponseEntity<?> responseEntity = this.userController.addUser(reqBody);
		
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		
		Mockito.verify(this.mockUserSvc).add(reqBody.getLogin(), reqBody.getFirstName(), reqBody.getLastName());
		
	}
	
	@Test
	void updateUser_Success() {
		long userId = 2L;
		String login = "spippen";
		String firstName = "Scottie";
		String lastName = "Pippen";
		
		NetflixUser expUser = new NetflixUser();
		expUser.setId(userId);
		expUser.setLogin(login);
		expUser.setFirstName(firstName);
		expUser.setLastName(lastName);

		Mockito.when(this.mockUserSvc.update(expUser)).thenReturn(expUser);
		
		ResponseEntity<?> responseEntity = this.userController.updateUser(expUser);
		
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assertions.assertEquals(expUser, responseEntity.getBody());
		
		Mockito.verify(this.mockUserSvc).update(expUser);
	}
	
	@Test
	void updateUser_Failure_Data() {
		NetflixUser expUser = null;

		ResponseEntity<?> responseEntity = this.userController.updateUser(expUser);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	@Test
	void updateUser_Failure_NotFound() {
		long userId = 2L;
		String login = "spippen";
		String firstName = "Scottie";
		String lastName = "Pippen";
		
		NetflixUser expUser = new NetflixUser();
		expUser.setId(userId);
		expUser.setLogin(login);
		expUser.setFirstName(firstName);
		expUser.setLastName(lastName);

		Mockito.when(this.mockUserSvc.update(expUser)).thenReturn(null);
		
		ResponseEntity<?> responseEntity = this.userController.updateUser(expUser);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		
		Mockito.verify(this.mockUserSvc).update(expUser);
	}
	
	@Test
	void updateUser_Failure_ServiceException() {
		long userId = 2L;
		String login = "spippen";
		String firstName = "Scottie";
		String lastName = "Pippen";
		
		NetflixUser expUser = new NetflixUser();
		expUser.setId(userId);
		expUser.setLogin(login);
		expUser.setFirstName(firstName);
		expUser.setLastName(lastName);

		Mockito.when(this.mockUserSvc.update(expUser)).thenThrow(RuntimeException.class);
		
		ResponseEntity<?> responseEntity = this.userController.updateUser(expUser);
		
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		
		Mockito.verify(this.mockUserSvc).update(expUser);
	}
	
	@Test
	void deleteUser_Success() {
		long userId = 2L;
		String login = "spippen";
		String firstName = "Scottie";
		String lastName = "Pippen";
		
		NetflixUser expUser = new NetflixUser();
		expUser.setId(userId);
		expUser.setLogin(login);
		expUser.setFirstName(firstName);
		expUser.setLastName(lastName);

		Mockito.when(this.mockUserSvc.delete(expUser)).thenReturn(true);
		
		ResponseEntity<?> responseEntity = this.userController.deleteUser(expUser);
		
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		Mockito.verify(this.mockUserSvc).delete(expUser);
	}
	
	@Test
	void deleteUser_Failure_Data() {
		NetflixUser expUser = null;

		ResponseEntity<?> responseEntity = this.userController.deleteUser(expUser);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
	@Test
	void deleteUser_Failure_NotFound() {
		long userId = 2L;
		String login = "spippen";
		String firstName = "Scottie";
		String lastName = "Pippen";
		
		NetflixUser expUser = new NetflixUser();
		expUser.setId(userId);
		expUser.setLogin(login);
		expUser.setFirstName(firstName);
		expUser.setLastName(lastName);

		Mockito.when(this.mockUserSvc.delete(expUser)).thenReturn(false);
		
		ResponseEntity<?> responseEntity = this.userController.deleteUser(expUser);
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		
		Mockito.verify(this.mockUserSvc).delete(expUser);
	}
	
	@Test
	void deleteUser_Failure_ServiceException() {
		long userId = 2L;
		String login = "spippen";
		String firstName = "Scottie";
		String lastName = "Pippen";
		
		NetflixUser expUser = new NetflixUser();
		expUser.setId(userId);
		expUser.setLogin(login);
		expUser.setFirstName(firstName);
		expUser.setLastName(lastName);

		Mockito.when(this.mockUserSvc.delete(expUser)).thenThrow(RuntimeException.class);
		
		ResponseEntity<?> responseEntity = this.userController.deleteUser(expUser);
		
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
		
		Mockito.verify(this.mockUserSvc).delete(expUser);
	}
	
	@Test
	void getUserByLogin() {
		long userId = 2L;
		String login = "spippen";
		String firstName = "Scottie";
		String lastName = "Pippen";
		
		NetflixUser expUser = new NetflixUser();
		expUser.setId(userId);
		expUser.setLogin(login);
		expUser.setFirstName(firstName);
		expUser.setLastName(lastName);

		Mockito.when(this.mockUserSvc.getByLogin(login)).thenReturn(expUser);
		
		NetflixUser actualUser = this.userController.getUserByLogin(login);
		
		Assertions.assertEquals(expUser, actualUser);
		
		Mockito.verify(this.mockUserSvc).getByLogin(login);
	}
	
	@Test
	void getAllUsers() {
		long userId = 2L;
		String login = "spippen";
		String firstName = "Scottie";
		String lastName = "Pippen";
		
		NetflixUser expUser = new NetflixUser();
		expUser.setId(userId);
		expUser.setLogin(login);
		expUser.setFirstName(firstName);
		expUser.setLastName(lastName);
		
		List<NetflixUser> expList = new ArrayList<NetflixUser>();
		expList.add(expUser);

		Mockito.when(this.mockUserSvc.getAll()).thenReturn(expList);
		
		List<NetflixUser> actualList = this.userController.getAllUsers();
		
		Assertions.assertEquals(expList, actualList);
		
		Mockito.verify(this.mockUserSvc).getAll();
	}
	
	
}
