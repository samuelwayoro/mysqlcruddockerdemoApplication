package org.samydevup.mysqlcruddockerdemo.controller;

import java.util.List;

import org.samydevup.mysqlcruddockerdemo.payload.ObjetDto;
import org.samydevup.mysqlcruddockerdemo.service.ObjetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ObjetController {

	private ObjetService objetService;
	private Logger log = LoggerFactory.getLogger(ObjetController.class);

	public ObjetController(ObjetService objetService) {
		this.objetService = objetService;
	}

	@PostMapping("/users/{userId}/objet")
	public ResponseEntity<ObjetDto> createObjet(@RequestBody ObjetDto objetDto, @PathVariable Long userId) {
		log.info("*** création d'un nouvelle objet demandée ....***");
		return new ResponseEntity<>(objetService.createObjet(userId, objetDto), HttpStatus.CREATED);
	}

	@GetMapping("/users/{userId}/objets")
	public List<ObjetDto> getObjetsByUserId(@PathVariable Long userId) {
		log.info("*** liste d'objets du user d'id : {} ", userId);
		
		return objetService.getObjetByUserId(userId) ;
	}
}
