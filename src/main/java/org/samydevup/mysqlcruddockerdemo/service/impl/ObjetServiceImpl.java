package org.samydevup.mysqlcruddockerdemo.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.samydevup.mysqlcruddockerdemo.entity.Objet;
import org.samydevup.mysqlcruddockerdemo.entity.User;
import org.samydevup.mysqlcruddockerdemo.exception.MysqlCrudApiException;
import org.samydevup.mysqlcruddockerdemo.exception.ResourceNotFoundException;
import org.samydevup.mysqlcruddockerdemo.payload.ObjetDto;
import org.samydevup.mysqlcruddockerdemo.repository.ObjetRepository;
import org.samydevup.mysqlcruddockerdemo.repository.UserRepository;
import org.samydevup.mysqlcruddockerdemo.service.ObjetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ObjetServiceImpl implements ObjetService {

	private ObjetRepository objetRepository;
	private UserRepository userRepository;
	private ModelMapper modelMapper;
	private Logger log = LoggerFactory.getLogger(ObjetServiceImpl.class);

	public ObjetServiceImpl(ObjetRepository objetRepository, UserRepository userRepository, ModelMapper modelMapper) {
		this.objetRepository = objetRepository;
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ObjetDto createObjet(Long userId, ObjetDto objetDto) {

		Objet objet = modelMapper.map(objetDto, Objet.class);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		objet.setUser(user);
		log.info("OBJET A ENREGISTRER : {} ", objet);
		Objet newObjet = objetRepository.save(objet);
		return modelMapper.map(newObjet, ObjetDto.class);
	}

	@Override
	public List<ObjetDto> getObjetByUserId(Long userId) {
		List<Objet> objets = objetRepository.findByUserId(userId);
		return objets.stream().map(obj -> modelMapper.map(obj, ObjetDto.class)).toList();
	}

	@Override
	public ObjetDto getObjetById(Long userId, Long objetId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		Objet objet = objetRepository.findById(objetId)
				.orElseThrow(() -> new ResourceNotFoundException("Objet", "id", objetId));

		if (!objet.getUser().getId().equals(user.getId())) {
			throw new MysqlCrudApiException(HttpStatus.BAD_REQUEST, "cet objet n'est pas lié au propriétaire donné");
		}

		return modelMapper.map(objet, ObjetDto.class);
	}

	@Override
	public ObjetDto updateObjetId(Long userId, Long objetId, ObjetDto objetDto) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		Objet objet = objetRepository.findById(objetId)
				.orElseThrow(() -> new ResourceNotFoundException("Objet", "id", objetId));

		if (!objet.getUser().getId().equals(user.getId())) {
			throw new MysqlCrudApiException(HttpStatus.BAD_REQUEST, "cet objet n'est pas lié au propriétaire donné");
		}

		objet.setName(objet.getName());
		objet.setDescription(objet.getDescription());
		objet.setDateAchat(objet.getDateAchat());

		objet = objetRepository.save(objet);

		return modelMapper.map(objet, ObjetDto.class);
	}

	@Override
	public void deleteObjetById(Long userId, Long objetId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		Objet objet = objetRepository.findById(objetId)
				.orElseThrow(() -> new ResourceNotFoundException("Objet", "id", objetId));

		if (!objet.getUser().getId().equals(user.getId())) {
			throw new MysqlCrudApiException(HttpStatus.BAD_REQUEST, "cet objet n'est pas lié au propriétaire donné");
		}

		objetRepository.delete(objet);

	}

	@Override
	public List<ObjetDto> getAllObjets() {
		List<Objet> objets = objetRepository.findAll();
		return objets.stream().map(o -> modelMapper.map(o, ObjetDto.class)).toList();
	}

}
