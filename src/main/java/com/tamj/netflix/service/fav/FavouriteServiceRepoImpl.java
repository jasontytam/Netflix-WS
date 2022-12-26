package com.tamj.netflix.service.fav;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.tamj.netflix.service.fav.entity.FavMovie;
import com.tamj.netflix.service.fav.repo.FavMovieRepository;
import com.tamj.netflix.service.user.entity.NetflixUser;

@Component
public class FavouriteServiceRepoImpl implements FavouriteService {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private FavMovieRepository favMovieRepo;

	@Override
	public List<FavMovie> getList(long userId) {
		return this.favMovieRepo.findByUserId(userId);
	}

	@Override
	public FavMovie addToFavourite(long userId, String netflixId, String titleName) {
		NetflixUser user = new NetflixUser();
		user.setId(userId);
		
		FavMovie favMov = new FavMovie();
		favMov.setUser(user);
		favMov.setNetflixId(netflixId);
		favMov.setTitleName(titleName);
		
		try {
			return this.favMovieRepo.save(favMov);
		} catch (DataIntegrityViolationException e) {
			this.logger.error("FavMovie Add Faile (Already Existed) : {} + {} + {} - {}", userId, netflixId, titleName, e.getMessage());
			return null;
		}
	}

	@Override
	public boolean removeFavourite(long userId, String netflixId) {
		List<FavMovie> resultList = this.favMovieRepo.findByUserIdAndNetflixId(userId, netflixId);
		
		if (resultList == null || resultList.size() == 0) {
			this.logger.error("FavMovie Not exist : {} + {}", userId, netflixId);
			return false;
		} else {
			this.favMovieRepo.delete(resultList.get(0));
			this.logger.info("FavMovie Deleted : {} + {}", userId, netflixId);
			return true;
		}
	}

	@Override
	public void setEntityManager(EntityManager em) {
		// TODO Auto-generated method stub

	}

}
