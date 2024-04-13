package org.samydevup.mysqlcruddockerdemo.controller;

import java.util.List;

import org.samydevup.mysqlcruddockerdemo.payload.UserDto;
import org.samydevup.mysqlcruddockerdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	 * créer un nouveau user
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
	 * retourne la liste de tous les users
	 * @return
	 */
	@GetMapping
	public List<UserDto> getAllUsers(){
		return userService.getAllUsers();
	}

}
