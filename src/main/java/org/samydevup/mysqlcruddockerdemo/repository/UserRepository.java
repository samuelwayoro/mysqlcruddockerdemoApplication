package org.samydevup.mysqlcruddockerdemo.repository;

import org.samydevup.mysqlcruddockerdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository : annotation facultative car JpaRepository contient les annotation @Repository et @Transactional
public interface UserRepository extends JpaRepository<User, Long> {

}
