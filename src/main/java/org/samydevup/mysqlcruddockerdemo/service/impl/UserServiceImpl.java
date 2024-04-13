package org.samydevup.mysqlcruddockerdemo.service.impl;

import org.modelmapper.ModelMapper;
import org.samydevup.mysqlcruddockerdemo.entity.User;
import org.samydevup.mysqlcruddockerdemo.payload.UserDto;
import org.samydevup.mysqlcruddockerdemo.repository.UserRepository;
import org.samydevup.mysqlcruddockerdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private ModelMapper modelMapper;

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}


	@Override
	public UserDto createUser(UserDto userDto) {
		log.info("création du user en cours ...");
		User user = modelMapper.map(userDto, User.class);
		User newUser = userRepository.save(user);
		log.info("user créee {} : ", newUser);

		return modelMapper.map(newUser, UserDto.class);
	}

}
