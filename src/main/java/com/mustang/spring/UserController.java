package com.mustang.spring;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private final UserDALImpl userDALImpl;
	
	public UserController(UserRepository userRepository, UserDALImpl userDALImpl) {
		this.userRepository = userRepository;
		this.userDALImpl = userDALImpl;
	}
	
	@GetMapping("/getAll")
	public List<User> getAllUsers() {
		LOG.info("Getting all users");
//		return userRepository.findAll();	
		return userDALImpl.getAllUsers();
	}
	
	@GetMapping("/get/{userId}")
	public User getById(@PathVariable String userId) {
		LOG.info("Getting user with id: {}", userId);
//		return userRepository.findById(userId).get();
		return userDALImpl.getUserById(userId);
	}
	
	@PostMapping("/post")
	public User AddUser(@RequestBody User user) {
		LOG.info("Saving user");
//		return userRepository.save(user);
		return userDALImpl.addNewUser(user);
	}
	
	@GetMapping("/getSettings/{userId}")
	public Object getAllUserSettings(@PathVariable String userId) {
//		User user = userRepository.findById(userId).get();
		User user = userDALImpl.getUserById(userId);
		if (user != null) {
			return user.getUserSettings();
		} else {
			return "user not found";
		}
	}
	
	@GetMapping("/getSettings/{userId}/{key}")
	public String getUserSettings(@PathVariable String userId, @PathVariable String key) {
//		User user = userRepository.findById(userId).get();
		User user = userDALImpl.getUserById(userId);
		if (user != null) {
			return user.getUserSettings().get(key);
		} else {
			return "user not found";
		}
	}
	
	@GetMapping("/PostSettings/{userId}/{key}/{value}")
	public String PostSettings(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
//		User user = userRepository.findById(userId).get();
		User user = userDALImpl.getUserById(userId);
		if (user != null) {
			user.getUserSettings().put(key, value);
			userRepository.save(user);
			return "key added";
		} else {
			return "user not found";
		}
	}
}
