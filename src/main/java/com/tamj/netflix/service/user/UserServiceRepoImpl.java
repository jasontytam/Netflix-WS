package com.tamj.netflix.service.user;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.tamj.netflix.service.user.entity.NetflixUser;
import com.tamj.netflix.service.user.repo.UserRepo;

@Component
public class UserServiceRepoImpl implements UserService {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private UserRepo userRepo;

	@Override
	public NetflixUser get(long id) {
		return this.userRepo.findById(id);
	}

	@Override
	public NetflixUser getByLogin(String login) {
		return this.userRepo.findByLogin(login);
	}

	@Override
	public List<NetflixUser> getAll() {
		return this.userRepo.findAll();
	}

	@Override
	public NetflixUser add(String login, String firstName, String lastName) {
		NetflixUser user = new NetflixUser(login, firstName, lastName);

		try {
			return this.userRepo.save(user);
		} catch (DataIntegrityViolationException e) {
			this.logger.error("User Add Failed (Already Existed) : {} - {}", login, e.getMessage());
			return null;
		}
	}

	@Override
	public NetflixUser update(NetflixUser user) {
		NetflixUser dbUser = this.userRepo.findById(user.getId());
		
		if (dbUser == null) {
			this.logger.error("NetflixUser Not exist : {}", user);
			return null;
		} else {
			this.userRepo.save(user);
			this.logger.info("NetflixUser Updated : {}", user);
			return user;
		}
	}

	@Override
	public boolean delete(NetflixUser user) {
		NetflixUser dbUser = this.userRepo.findById(user.getId());
		
		if (dbUser == null) {
			this.logger.error("NetflixUser Not exist : {}", user);
			return false;
		} else {
			this.userRepo.delete(dbUser);
			this.logger.info("NetflixUser Deleted : {}", dbUser);
			return true;
		}
	}

}
