package com.tamj.netflix.service.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamj.netflix.service.user.entity.NetflixUser;

public interface UserRepo extends JpaRepository<NetflixUser, Long> {
	
	NetflixUser findById(long id);
	NetflixUser findByLogin(String login);

}
