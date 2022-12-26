package com.tamj.netflix.service.fav.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamj.netflix.service.fav.entity.FavMovie;

public interface FavMovieRepository extends JpaRepository<FavMovie, Long> {
	List<FavMovie> findByUserId(long userId);
	List<FavMovie> findByUserIdAndNetflixId(long userId, String netflixId);
	
}
