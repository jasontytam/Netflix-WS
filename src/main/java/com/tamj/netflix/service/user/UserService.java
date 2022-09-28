package com.tamj.netflix.service.user;

import java.util.List;

import com.tamj.netflix.service.user.entity.NetflixUser;

public interface UserService {
	public NetflixUser get(long id);
	public NetflixUser getByLogin(String login);
	public List<NetflixUser> getAll();
	public NetflixUser add(String login, String firstName, String lastName);
	public NetflixUser update(NetflixUser user);
	public void delete(NetflixUser user);
}
