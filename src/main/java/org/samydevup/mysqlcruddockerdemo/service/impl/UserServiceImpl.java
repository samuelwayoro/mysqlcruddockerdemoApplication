package org.samydevup.mysqlcruddockerdemo.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.samydevup.mysqlcruddockerdemo.entity.User;
import org.samydevup.mysqlcruddockerdemo.exception.ResourceNotFoundException;
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
		log.info("user créee {} ", newUser.toString());

		return modelMapper.map(newUser, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(u -> modelMapper.map(u, UserDto.class)).toList();
	}

	@Override
	public UserDto getUserById(Long userId) {
		log.info("***** recuperation du user d'id {} *****", userId);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		log.info("user d'id {} ", userId);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUserById(UserDto userDto, Long id) {
		log.info("*** utilisateur d'id {} a modifier ", id);
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());

		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public void deleteUserById(Long id) {
		log.info("**** utilisateur did {} a supprimer ", id);
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		userRepository.delete(user);
	}

}
