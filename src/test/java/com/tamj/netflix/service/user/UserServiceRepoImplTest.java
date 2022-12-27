package com.tamj.netflix.service.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.tamj.netflix.service.user.entity.NetflixUser;
import com.tamj.netflix.service.user.repo.UserRepo;

@SpringBootTest
public class UserServiceRepoImplTest {

	@Mock
	private UserRepo usrRepo;
	
	@InjectMocks
	private UserServiceRepoImpl usrSvcRepoImp;
	
	@Test
	void getById_Success() {
		long userId = 2L;
		
		NetflixUser expectedUser = new NetflixUser();
		expectedUser.setId(userId);
		
		Mockito.when(this.usrRepo.findById(userId)).thenReturn(expectedUser);
		
		NetflixUser actualUser = this.usrSvcRepoImp.get(userId);
		
		Assertions.assertEquals(expectedUser, actualUser);
		
		Mockito.verify(this.usrRepo).findById(userId);
	}
	
	@Test
	void getById_Failure() {
		long userId = 2L;
		
		NetflixUser expectedUser = new NetflixUser();
		expectedUser.setId(userId);
		
		Mockito.when(this.usrRepo.findById(userId)).thenReturn(null);
		
		NetflixUser actualUser = this.usrSvcRepoImp.get(userId);
		
		Assertions.assertNull(actualUser);
		
		Mockito.verify(this.usrRepo).findById(userId);	}
	
	@Test
	void getByLogin_Success() {
		long userId = 2L;
		String login = "spippen";
		
		NetflixUser expectedUser = new NetflixUser();
		expectedUser.setId(userId);
		expectedUser.setLogin(login);
		
		Mockito.when(this.usrRepo.findByLogin(login)).thenReturn(expectedUser);
		
		NetflixUser actualUser = this.usrSvcRepoImp.getByLogin(login);
		
		Assertions.assertEquals(expectedUser, actualUser);
		
		Mockito.verify(this.usrRepo).findByLogin(login);
	}
	
	@Test
	void getByLogin_Failure() {
		long userId = 2L;
		String login = "spippen";
		
		NetflixUser expectedUser = new NetflixUser();
		expectedUser.setId(userId);
		expectedUser.setLogin(login);
		
		Mockito.when(this.usrRepo.findByLogin(login)).thenReturn(null);
		
		NetflixUser actualUser = this.usrSvcRepoImp.getByLogin(login);
		
		Assertions.assertNull(actualUser);
		
		Mockito.verify(this.usrRepo).findByLogin(login);
	}
	
	@Test
	void getAll() {
		long userId = 2L;
		String login = "spippen";
		
		NetflixUser expectedUser = new NetflixUser();
		expectedUser.setId(userId);
		expectedUser.setLogin(login);
		
		List<NetflixUser> expectedList = new ArrayList<NetflixUser>();
		expectedList.add(expectedUser);
		
		Mockito.when(this.usrRepo.findAll()).thenReturn(expectedList);
		
		List<NetflixUser> actualList = this.usrSvcRepoImp.getAll();
		
		Assertions.assertEquals(expectedList, actualList);
		
		Mockito.verify(this.usrRepo).findAll();
	}
	
	@Test
	void addUser_Success() {
		long userId = 4L;
		String login = "llongley";
		String firstName = "Luc";
		String lastName = "Longley";
		
		NetflixUser newUser = new NetflixUser();
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setLogin(login);
		
		NetflixUser expectedUser = new NetflixUser();
		expectedUser.setId(userId);
		expectedUser.setFirstName(firstName);
		expectedUser.setLastName(lastName);
		expectedUser.setLogin(login);
		
		Mockito.when(this.usrRepo.save(newUser)).thenReturn(expectedUser);
		
		NetflixUser resultUser = this.usrSvcRepoImp.add(login, firstName, lastName);
		
		Assertions.assertEquals(expectedUser, resultUser);
		
		Mockito.verify(this.usrRepo).save(newUser);

	}
	
	@Test
	void addUser_Failure() {
//		long userId = 4L;
		String login = "llongley";
		String firstName = "Luc";
		String lastName = "Longley";
		
		NetflixUser newUser = new NetflixUser();
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setLogin(login);
		
		Mockito.when(this.usrRepo.save(newUser)).thenReturn(null);
		
		NetflixUser resultUser = this.usrSvcRepoImp.add(login, firstName, lastName);
		
		Assertions.assertNull(resultUser);
		
		Mockito.verify(this.usrRepo).save(newUser);

	}
	
	@Test
	void updateUser_Success() {
		long userId = 4L;
		String login = "llongley";
		String firstName = "Luc";
		String lastName = "Longley";
		
		NetflixUser expectedUser = new NetflixUser();
		expectedUser.setId(userId);
		expectedUser.setFirstName(firstName);
		expectedUser.setLastName(lastName);
		expectedUser.setLogin(login);
		
		Mockito.when(this.usrRepo.findById(expectedUser.getId())).thenReturn(expectedUser);
		Mockito.when(this.usrRepo.save(expectedUser)).thenReturn(expectedUser);
		
		NetflixUser resultUser = this.usrSvcRepoImp.update(expectedUser);
		
		Assertions.assertEquals(expectedUser, resultUser);
		
		Mockito.verify(this.usrRepo).findById(expectedUser.getId());
		Mockito.verify(this.usrRepo).save(expectedUser);

	}
	
	@Test
	void updateUser_Failure() {
		long userId = 4L;
		String login = "llongley";
		String firstName = "Luc";
		String lastName = "Longley";
		
		NetflixUser expectedUser = new NetflixUser();
		expectedUser.setId(userId);
		expectedUser.setFirstName(firstName);
		expectedUser.setLastName(lastName);
		expectedUser.setLogin(login);
		
		Mockito.when(this.usrRepo.findById(expectedUser.getId())).thenReturn(null);
		
		NetflixUser resultUser = this.usrSvcRepoImp.update(expectedUser);
		
		Assertions.assertNull(resultUser);
		
		Mockito.verify(this.usrRepo).findById(expectedUser.getId());

	}
	
	@Test
	void deleteUser_Success() {
		long userId = 4L;
		String login = "llongley";
		String firstName = "Luc";
		String lastName = "Longley";
		
		NetflixUser expectedUser = new NetflixUser();
		expectedUser.setId(userId);
		expectedUser.setFirstName(firstName);
		expectedUser.setLastName(lastName);
		expectedUser.setLogin(login);
		
		Mockito.when(this.usrRepo.findById(expectedUser.getId())).thenReturn(expectedUser);
		
		boolean result = this.usrSvcRepoImp.delete(expectedUser);
		
		Assertions.assertTrue(result);
		
		Mockito.verify(this.usrRepo).findById(expectedUser.getId());

	}
	
	@Test
	void deleteUser_Failure() {
		long userId = 4L;
		String login = "llongley";
		String firstName = "Luc";
		String lastName = "Longley";
		
		NetflixUser expectedUser = new NetflixUser();
		expectedUser.setId(userId);
		expectedUser.setFirstName(firstName);
		expectedUser.setLastName(lastName);
		expectedUser.setLogin(login);
		
		Mockito.when(this.usrRepo.findById(expectedUser.getId())).thenReturn(null);
		
		boolean result = this.usrSvcRepoImp.delete(expectedUser);
		
		Assertions.assertFalse(result);
		
		Mockito.verify(this.usrRepo).findById(expectedUser.getId());

	}
}
