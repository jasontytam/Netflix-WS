package com.tamj.netflix.service.fav;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tamj.netflix.service.fav.entity.FavMovie;
import com.tamj.netflix.service.user.entity.NetflixUser;

public class FavouriteServiceImpl implements FavouriteService {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	private EntityManager entityMgr;
	
	@Override
	public void setEntityManager(EntityManager em) {
		this.entityMgr = em;
	}


	@Override
	public List<FavMovie> getList(long userId) {
		TypedQuery<FavMovie> query = this.entityMgr.createQuery(
				"select favmovie from FavMovie favmovie WHERE favmovie.user.id = :userid", FavMovie.class);
		query.setParameter("userid", userId);
		List<FavMovie> resultList = query.getResultList();
		
		if (resultList != null && resultList.size() > 0) {
			this.logger.info("{} FavMovie(s) with User Id [{}] retrieved ", resultList.size(), userId);
			return resultList;
		} else {
			this.logger.warn("FavMovie with User Id [{}] Not found", userId);
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = PersistenceException.class )
	public FavMovie addToFavourite(long userId, String netflixId, String titleName) {
		NetflixUser user = new NetflixUser();
		user.setId(userId);
		FavMovie favMovie = new FavMovie();
		favMovie.setUser(user);
		favMovie.setNetflixId(netflixId);
		favMovie.setTitleName(titleName);
		try {
			this.entityMgr.persist(favMovie);
			this.logger.info("FavMovie Added : {}", favMovie);
			return favMovie;
//		} catch (EntityExistsException e) {
//			this.logger.error("FavMovie already existed : {} - {}", favMovie, e.getMessage());
//			return null;
//		} catch (ConstraintViolationException e) {
//			this.logger.error("FavMovie add failed as User Not exist : {} - {}", favMovie, e.getClass().getCanonicalName(), e);
//			return null;
		} catch (Exception e) {
//			for (Throwable t: e.getSuppressed())
//				this.logger.error(e.getCause().getClass().getCanonicalName());
			Class<?> cls = e.getCause().getClass();
			if (cls.equals(EntityExistsException.class)) {
				this.logger.error("[{}] FavMovie already existed : {}", cls.getCanonicalName(), favMovie);
			} else if (cls.equals(ConstraintViolationException.class)) {
				this.logger.error("[{}] FavMovie add failed for FK/UK violation : {}", cls.getCanonicalName(), favMovie);
			} else {
				this.logger.error("[{}] FavMovie add failed : {}", e.getClass().getCanonicalName(), favMovie, e);
			}
			return null;
		}
	}

	@Override
	@Transactional
	public boolean removeFavourite(long userId, String netflixId) {
		TypedQuery<FavMovie> query = this.entityMgr.createQuery(
				"select favmovie from FavMovie favmovie WHERE favmovie.user.id = :userid and favmovie.netflixId = :netflixid", FavMovie.class);
		query.setParameter("userid", userId);
		query.setParameter("netflixid", netflixId);

		List<FavMovie> resultList = query.getResultList();
		
		if (resultList == null || resultList.size() == 0) {
			this.logger.error("FavMovie Not exist : {} + {}", userId, netflixId);
			return false;
		} else {
			this.entityMgr.remove(resultList.get(0));
			this.logger.info("FavMovie Deleted : {} + {}", userId, netflixId);
			return true;
		}
	}

}
