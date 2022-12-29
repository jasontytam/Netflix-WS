package com.tamj.netflix.controller.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamj.netflix.service.user.UserService;
import com.tamj.netflix.service.user.entity.NetflixUser;

@RestController
@RequestMapping("/netflix/user")
@CrossOrigin
public class UserController {
	
	final private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userSvc;

	@GetMapping("/get")
	public List<NetflixUser> getAllUsers() {
		return this.userSvc.getAll();
	}
	
	@GetMapping("/get/{login}")
	public NetflixUser getUserByLogin(@PathVariable("login") String login) {
//		try {
			return this.userSvc.getByLogin(login);
//		} catch (NumberFormatException e) {
//			this.logger.warn("Get User by ID - Illegal User ID [{}]", id);
//			return null;
//		}
	}
	
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addUser(@RequestBody NetflixUser user) {
		if (user == null || user.getLogin() == null || user.getFirstName() == null || user.getLastName() == null) {
			this.logger.warn("POST with empty User / Login / FirstName / LastName {}", user);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			try {
				NetflixUser addedUser = this.userSvc.add(user.getLogin(), user.getFirstName(), user.getLastName());
				if (addedUser != null && addedUser.getId() != 0) {
					return new ResponseEntity<NetflixUser>(addedUser, HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} catch (Exception e) {
				this.logger.error("[{}] User add failed, returning [500 INTERNAL SERVER ERROR] : {}", e.getClass().getCanonicalName(), user, e);
				return new ResponseEntity<String>("Unknown excpetion", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUser(@RequestBody NetflixUser user) {
		if (user == null || user.getId() == 0) {
			this.logger.warn("PUT with empty User / User ID {}", user);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			try {
				NetflixUser updatedUser = this.userSvc.update(user);
				if (updatedUser != null && updatedUser.getId() != 0) {
					return new ResponseEntity<NetflixUser>(updatedUser, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} catch (Exception e) {
				this.logger.error("[{}] User update failed, returning [500 INTERNAL SERVER ERROR] : {}", e.getClass().getCanonicalName(), user, e);
				return new ResponseEntity<String>("Unknown excpetion", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteUser(@RequestBody NetflixUser user) {
		if (user == null || user.getId() == 0) {
			this.logger.warn("DELETE with empty User / User ID {}", user);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			try {
				boolean result = this.userSvc.delete(user);
				if (result) {
					return new ResponseEntity<>(HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("Record not found", HttpStatus.BAD_REQUEST);
				}
			} catch (Exception e) {
				this.logger.error("[{}] User delete failed, returning [500 INTERNAL SERVER ERROR] : {}", e.getClass().getCanonicalName(), user, e);
				return new ResponseEntity<String>("Unknown excpetion", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

}
