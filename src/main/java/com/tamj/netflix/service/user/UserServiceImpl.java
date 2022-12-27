package com.tamj.netflix.service.user;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.tamj.netflix.service.user.entity.NetflixUser;


public class UserServiceImpl implements UserService {
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@PersistenceContext
	private EntityManager entityMgr;

	@Override
	public NetflixUser get(long id) {
		return this.entityMgr.find(NetflixUser.class, id);
	}

	@Override
	public NetflixUser getByLogin(String login) {
		TypedQuery<NetflixUser> query = this.entityMgr.createQuery(
				"select netflixuser from NetflixUser netflixuser WHERE netflixuser.login = :login", NetflixUser.class);
		query.setParameter("login", login);
		List<NetflixUser> resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0) {
			this.logger.info("{} User(s) with login [{}] retrieved (Returning first one only)", resultList.size(), login);
			return resultList.get(0);
		} else {
			this.logger.warn("User with login [{}] Not found", login);
			return null;
		}
	}

	@Override
	public List<NetflixUser> getAll() {
		TypedQuery<NetflixUser> query = this.entityMgr.createQuery(
				"select netflixuser from NetflixUser netflixuser", NetflixUser.class);
		List<NetflixUser> resultList = query.getResultList();
		this.logger.info("{} User(s) retrieved", resultList.size());
		return resultList;
	}

	@Override
	@Transactional
	public NetflixUser add(String login, String firstName, String lastName) {
		NetflixUser user = new NetflixUser(login, firstName, lastName);
		try {
			this.entityMgr.persist(user);
			this.logger.info("User Added : {}", user);
			return user;
		} catch (EntityExistsException e) {
			this.logger.error("User already existed : {}", user);
			return null;
		} catch (Exception e) {
			this.logger.error("User add failed : {} - {}", user, e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional
	public NetflixUser update(NetflixUser user) {
		NetflixUser existingUser = this.entityMgr.find(NetflixUser.class, user.getId());
		
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setLogin(user.getLogin());
		this.logger.info("User Updated : {}", existingUser);
		
		return existingUser;
	}

	@Override
	@Transactional
	public boolean delete(NetflixUser user) {
		NetflixUser existingUser = this.entityMgr.find(NetflixUser.class, user.getId());
		
		if (existingUser == null) {
			this.logger.error("User Not exist : {}", existingUser);
			return false;
		} else {
			this.logger.info("User Deleted : {}", existingUser);
			this.entityMgr.remove(existingUser);
			return true;
		}
		
	}
	
	
}
