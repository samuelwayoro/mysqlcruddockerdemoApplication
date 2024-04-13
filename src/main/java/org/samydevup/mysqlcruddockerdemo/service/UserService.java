package org.samydevup.mysqlcruddockerdemo.service;

import java.util.List;

import org.samydevup.mysqlcruddockerdemo.payload.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	List<UserDto> getAllUsers();

}
