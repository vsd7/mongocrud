/**
 * 
 */
package com.vd.mongodbcrud.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vd.mongodbcrud.model.User;
import com.vd.mongodbcrud.repository.UserDAO;
import com.vd.mongodbcrud.repository.UserRepository;

/**
 * @author vivedesh
 *
 */
@RestController
@RequestMapping(value = "/")
public class UserController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDAO userDAO;

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers() {
		LOG.info("Getting all users.");
		return userRepository.findAll();
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable String userId) {
		LOG.info("Getting user with ID: {}.", userId);
		return userRepository.findById(userId).get();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody User addNewUsers(@RequestBody User user) {
		LOG.info("Saving user.");
		return userRepository.save(user);
	}
	
	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	public @ResponseBody Object getAllUserSettings(@PathVariable String userId) {
		User user = userRepository.findById(userId).get();
		/*
		 * if (user != null) { return user.getUserSettings(); } else { return
		 * "User not found."; }
		 */
		if (user != null) {
	        return userDAO.getAllUserSettings(userId);
	    } else {
	        return "User not found.";
	    }
	}
	
	@RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
	public @ResponseBody String getUserSetting(@PathVariable String userId, @PathVariable String key) {
		User user = userRepository.findById(userId).get();
		/*
		 * if (user != null) { return user.getUserSettings().get(key); } else { return
		 * "User not found."; }
		 */
		
		return userDAO.getUserSetting(userId, key);
	}

	@RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
	public @ResponseBody String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
		User user = userRepository.findById(userId).get();
		if (user != null) {
			user.getUserSettings().put(key, value);
			userRepository.save(user);
			return "Key added";
		} else {
			return "User not found.";
		}
	}
	
	
}
