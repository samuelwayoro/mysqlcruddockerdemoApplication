package org.samydevup.mysqlcruddockerdemo.service;

import java.util.List;

import org.samydevup.mysqlcruddockerdemo.payload.ObjetDto;

public interface ObjetService {

	ObjetDto createObjet(Long userId,ObjetDto objetDto);
	
	List<ObjetDto> getObjetByUserId(Long userId);
	
}
