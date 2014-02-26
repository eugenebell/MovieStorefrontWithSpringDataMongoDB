package com.eugene.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.eugene.model.User;
import com.eugene.service.dao.UserRepository;

public class MongoUserDetailsService implements UserDetailsService {

	private static final Logger LOG = Logger.getLogger(MongoUserDetailsService.class);

	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getUserDetail(username);
		org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), true, true, true, true,
				user.getAuthoritieses());
		return userDetail;
	}

	public User getUserDetail(String username) {
		User user = userRepository.findByUsername(username);
		LOG.debug("Getting details for user : " + user.toString());
		return user;
	}

}
