package org.samydevup.mysqlcruddockerdemo.service;

import java.util.List;

import org.samydevup.mysqlcruddockerdemo.payload.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	List<UserDto> getAllUsers();
	
	UserDto getUserById(Long userId);
	
	UserDto updateUserById(UserDto userDto,Long id);
	
	void deleteUserById(Long id);

}
