package com.tamj.netflix.service.fav;

import java.util.List;

import com.tamj.netflix.service.fav.entity.FavMovie;

public interface FavouriteService {
	public List<FavMovie> getList(long userId);
	public FavMovie addToFavourite(long userId, String netflixId, String titleName);
	public void removeFavourite(long userId, String netflixId);
}
