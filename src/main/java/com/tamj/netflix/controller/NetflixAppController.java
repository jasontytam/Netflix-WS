package com.tamj.netflix.controller;

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

import com.tamj.netflix.service.fav.FavouriteService;
import com.tamj.netflix.service.fav.entity.FavMovie;
import com.tamj.netflix.service.unogs.NetflixSearchService;
import com.tamj.netflix.service.unogs.entity.TitleDetail;
import com.tamj.netflix.service.unogs.entity.TitleSearchResult;
import com.tamj.netflix.service.user.UserService;
import com.tamj.netflix.service.user.entity.NetflixUser;

@RestController
@RequestMapping("/netflix")
@CrossOrigin
public class NetflixAppController {
	
	final private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private NetflixSearchService netSearchSvc;
	@Autowired
	private UserService userSvc;
	@Autowired
	private FavouriteService favSvc;

	@GetMapping("/movie/get/{id}")
	public TitleDetail getTitleDetailById(@PathVariable("id") String id) {
		return netSearchSvc.getTitleDetailById(id);
	}
	
	@GetMapping("/movie/search/{titleName}")
	public List<TitleSearchResult> searchTitleByName(@PathVariable("titleName") String titleName) {
		return netSearchSvc.searchTitleByName(titleName);
	}
	
	@GetMapping("/user/get")
	public List<NetflixUser> getAllUsers() {
		return this.userSvc.getAll();
	}
	
	@GetMapping("/user/get/{login}")
	public NetflixUser getUserByLogin(@PathVariable("login") String login) {
//		try {
			return this.userSvc.getByLogin(login);
//		} catch (NumberFormatException e) {
//			this.logger.warn("Get User by ID - Illegal User ID [{}]", id);
//			return null;
//		}
	}
	
	@PostMapping(value = "/user/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NetflixUser> addUser(@RequestBody NetflixUser user) {
		if (user == null || user.getLogin() == null || user.getFirstName() == null || user.getLastName() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			try {
				NetflixUser addedUser = this.userSvc.add(user.getLogin(), user.getFirstName(), user.getLastName());
				if (addedUser != null && addedUser.getId() != 0) {
		//			ResponseEntity.accepted().body(addedUser);
					return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} catch (Exception e) {
				this.logger.error("User add failed : {} - {}", user, e.getMessage());
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@PutMapping(value = "/user/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NetflixUser> modifyUser(@RequestBody NetflixUser user) {
		if (user == null || user.getId() == 0) {
			this.logger.warn("PUT with empty User / User ID");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			NetflixUser updatedUser = this.userSvc.update(user);
			if (updatedUser != null && updatedUser.getId() != 0) {
	//			ResponseEntity.accepted().body(addedUser);
				return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@DeleteMapping(value = "/user/delete", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUser(@RequestBody NetflixUser user) {
		if (user == null || user.getId() == 0) {
			this.logger.warn("DELETE with empty User / User ID");
		} else {
			this.userSvc.delete(user);
		}
	}
	
	@GetMapping("/movie/fav/get/{userid}")
	public List<FavMovie> getFavMovieListByUserId(@PathVariable("userid") long userId) {
//		try {
			return this.favSvc.getList(userId);
//		} catch (NumberFormatException e) {
//			this.logger.warn("Get User by ID - Illegal User ID [{}]", id);
//			return null;
//		}
	}
	
	@PostMapping(value = "/movie/fav/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FavMovie> addFavMovie(@RequestBody FavMovie favMovie) {
		if (favMovie == null || favMovie.getUser() == null || favMovie.getNetflixId() == null || favMovie.getTitleName() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			try {
				FavMovie addedFavMovie = this.favSvc.addToFavourite(favMovie.getUser().getId(), favMovie.getNetflixId(), favMovie.getTitleName());
				if (addedFavMovie != null && addedFavMovie.getId() != 0) {
		//			ResponseEntity.accepted().body(addedFavMovie);
					return new ResponseEntity<>(addedFavMovie, HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} catch (Exception e) {
				this.logger.error("[{}] FavMovie add failed : {}", e.getClass().getCanonicalName(), favMovie);
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@DeleteMapping(value = "/movie/fav/remove", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void removeFavMovie(@RequestBody FavMovie favMovie) {
		if (favMovie == null || favMovie.getUser() == null || favMovie.getNetflixId() == null) {
			this.logger.warn("DELETE with empty User / User ID / Netflix ID : {}", favMovie);
		} else {
			this.favSvc.removeFavourite(favMovie.getUser().getId(), favMovie.getNetflixId());
		}
	}

	public NetflixSearchService getNetSearchSvc() {
		return netSearchSvc;
	}

	public void setNetSearchSvc(NetflixSearchService netSearchSvc) {
		this.netSearchSvc = netSearchSvc;
	}

	public UserService getUserSvc() {
		return userSvc;
	}

	public void setUserSvc(UserService userSvc) {
		this.userSvc = userSvc;
	}
	
	public FavouriteService getFavSvc() {
		return favSvc;
	}

	public void setFavSvc(FavouriteService favSvc) {
		this.favSvc = favSvc;
	}
	
}
