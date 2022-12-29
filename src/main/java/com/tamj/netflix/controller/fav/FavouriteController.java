package com.tamj.netflix.controller.fav;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tamj.netflix.service.fav.FavouriteService;
import com.tamj.netflix.service.fav.entity.FavMovie;

@RestController
@RequestMapping("/netflix/movie/fav")
@CrossOrigin
public class FavouriteController {
	
	final private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private FavouriteService favSvc;
	
	@GetMapping("/get/{userid}")
	public List<FavMovie> getFavMovieListByUserId(@PathVariable("userid") long userId) {
//		try {
			return this.favSvc.getList(userId);
//		} catch (NumberFormatException e) {
//			this.logger.warn("Get User by ID - Illegal User ID [{}]", id);
//			return null;
//		}
	}
	
	@PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addFavMovie(@RequestBody FavMovie favMovie) {
		if (favMovie == null || favMovie.getUser() == null || favMovie.getNetflixId() == null || favMovie.getTitleName() == null) {
			this.logger.error("ADD with Incomplete Required Info (User ID / Netflix ID / Title Name) : {}", favMovie);
			return new ResponseEntity<String>("Incomplete Required Info (User ID / Netflix ID / Title Name)", HttpStatus.BAD_REQUEST);
		} else {
			try {
				FavMovie addedFavMovie = this.favSvc.addToFavourite(favMovie.getUser().getId(), favMovie.getNetflixId(), favMovie.getTitleName());
				if (addedFavMovie != null && addedFavMovie.getId() != 0) {
		//			ResponseEntity.accepted().body(addedFavMovie);
					return new ResponseEntity<FavMovie>(addedFavMovie, HttpStatus.CREATED);
				} else {
					this.logger.error("FavMovie NOT added, returning [400 BAD REQUEST] : {}", favMovie);
					return new ResponseEntity<String>("Favourte Movie NOT added, possibly data entry already existed", HttpStatus.BAD_REQUEST);
				}
			} catch (Exception e) {
				this.logger.error("[{}] FavMovie add failed, returning [500 INTERNAL SERVER ERROR] : {}", e.getClass().getCanonicalName(), favMovie, e);
				return new ResponseEntity<String>("Unknown excpetion", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@DeleteMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> removeFavMovie(@RequestBody FavMovie favMovie) {
		if (favMovie == null || favMovie.getUser() == null || favMovie.getNetflixId() == null) {
			this.logger.error("DELETE with empty User / User ID / Netflix ID : {}", favMovie);
			return new ResponseEntity<String>("Incomplete Required Info (User ID / Netflix ID)", HttpStatus.BAD_REQUEST);
		} else {
			try {
				boolean result = this.favSvc.removeFavourite(favMovie.getUser().getId(), favMovie.getNetflixId());
				if (result) {
					return new ResponseEntity<>(HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("Record not found", HttpStatus.BAD_REQUEST);
				}
			} catch (Exception e) {
				this.logger.error("[{}] FavMovie delete failed, returning [500 INTERNAL SERVER ERROR] : {}", e.getClass().getCanonicalName(), favMovie, e);
				return new ResponseEntity<String>("Unknown excpetion", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

}
