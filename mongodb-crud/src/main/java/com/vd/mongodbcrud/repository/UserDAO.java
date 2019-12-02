/**
 * 
 */
package com.vd.mongodbcrud.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vd.mongodbcrud.model.User;

/**
 * @author vivedesh
 *
 */

public interface UserDAO {

	List<User> getAllUsers();

	User getUserById(String userId);

	User addNewUser(User user);

	Object getAllUserSettings(String userId);

	String getUserSetting(String userId, String key);

	String addUserSetting(String userId, String key, String value);
}
