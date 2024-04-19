package org.samydevup.mysqlcruddockerdemo.repository;

import java.util.List;

import org.samydevup.mysqlcruddockerdemo.entity.Objet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjetRepository extends JpaRepository<Objet, Long> {
	
	
	List<Objet> findByUserId(Long userId);
	
}
