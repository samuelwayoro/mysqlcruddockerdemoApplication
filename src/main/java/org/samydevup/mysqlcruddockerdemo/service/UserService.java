package org.samydevup.mysqlcruddockerdemo.service;

import org.samydevup.mysqlcruddockerdemo.payload.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);

}
