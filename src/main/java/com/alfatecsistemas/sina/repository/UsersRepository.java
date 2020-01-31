package com.alfatecsistemas.sina.repository;

import com.alfatecsistemas.sina.domain.SecuUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<SecuUsers, Long> {

}
