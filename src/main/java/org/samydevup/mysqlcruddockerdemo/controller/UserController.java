package org.samydevup.mysqlcruddockerdemo.controller;

import java.util.List;

import org.samydevup.mysqlcruddockerdemo.payload.UserDto;
import org.samydevup.mysqlcruddockerdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserService userService;

	private Logger log = LoggerFactory.getLogger(UserController.class);

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * create a new user in the db
	 * 
	 * @param userDto
	 * @return
	 */
	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		log.info("createUser service endpoint ...");
		return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
	}

	/**
	 * get all users of Users Table
	 * 
	 * @return
	 */
	@GetMapping
	public List<UserDto> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * get a user by his id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}

	/**
	 * update a user by his id
	 * 
	 * @param userDto
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUserById(@RequestBody UserDto userDto, @PathVariable(name = "id") Long id) {
		UserDto userUpdated = userService.updateUserById(userDto, id);
		return new ResponseEntity<>(userUpdated, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable(name = "userId") Long id) {
		userService.deleteUserById(id);
		return new ResponseEntity<>("Utilisateur supprimé", HttpStatus.OK);
	}

}
