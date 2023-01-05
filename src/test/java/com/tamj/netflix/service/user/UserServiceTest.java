package com.tamj.netflix.service.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.tamj.netflix.service.user.entity.NetflixUser;

//@SpringBootTest
//@TestInstance(Lifecycle.PER_CLASS)
//@TestMethodOrder(MethodOrderer.MethodName.class)
public class UserServiceTest {
	
	@Autowired
	private UserService userSvc;
	
	private NetflixUser testUser;
	
//	@BeforeAll
//	public void setUp() {
//		testUser = new NetflixUser();
//		testUser.setLogin("unittestlogin");
//		testUser.setFirstName("Unit");
//		testUser.setLastName("Test");
//		
////		this.testUser = this.userSvc.add(this.testUser.getLogin(), this.testUser.getFirstName(), this.testUser.getLastName());
//	}
//	
//	@Test
//	void test1Context() {
//		assertNotNull(this.userSvc);
//	}
//	
//	@Test
//	void test2Create() {
//		NetflixUser returnedUser = this.userSvc.add(this.testUser.getLogin(), this.testUser.getFirstName(), this.testUser.getLastName());
//		
//		assertNotNull(returnedUser);
//		assertNotEquals(returnedUser.getId(), 0);
//		
//		this.testUser = returnedUser;
//	}
//	
//	@Test
//	void test3GetById() {
//		NetflixUser user = this.userSvc.get(-1);
//		assertNull(user);
//		
//		user = this.userSvc.get(this.testUser.getId());
//		assertNotNull(user);
//		assertEquals(user.getLogin(), this.testUser.getLogin());
//	}
//	
//	@Test
//	void test4GetByLogin() {
//		NetflixUser user = this.userSvc.getByLogin(this.testUser.getLogin());
//		assertNotNull(user);
//		assertEquals(user.getFirstName(), this.testUser.getFirstName());
//		assertEquals(user.getLastName(), this.testUser.getLastName());
//	}
//	
//	@Test
//	void test5GetAll() {
//		List<NetflixUser> userList = this.userSvc.getAll();
//		assertNotNull(userList);
//		
//	}
//	
//	@Test
//	void test6Update() {
//		this.testUser.setFirstName(this.testUser.getFirstName()+"_modified");
//		this.testUser.setLastName(this.testUser.getLastName()+"_modified");
//		
//		this.userSvc.update(testUser);
//		
//		NetflixUser user = this.userSvc.getByLogin(this.testUser.getLogin());
//		assertEquals(user.getFirstName(), this.testUser.getFirstName());
//		assertEquals(user.getLastName(), this.testUser.getLastName());
//	}
//	
//	@Test
//	void test7Delete() {
//		this.userSvc.delete(this.testUser);
//		
//		NetflixUser user = this.userSvc.getByLogin(this.testUser.getLogin());
//		assertNull(user);
//	}

}
