package org.samydevup.mysqlcruddockerdemo.repository;

import org.samydevup.mysqlcruddockerdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
