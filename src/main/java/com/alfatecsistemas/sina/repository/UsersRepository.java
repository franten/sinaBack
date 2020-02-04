package com.alfatecsistemas.sina.repository;

import com.alfatecsistemas.sina.domain.SecuUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<SecuUsers, Integer> {

    SecuUsers getSecuUsersByUserLogin(String userLogin);

    SecuUsers getSecuUsersByProfId(Integer userLogin);

    @Query("select su from SecuUsers su " +
            "where su.profId = :profId")
    SecuUsers getUserByProfId(@Param("profId") Integer profId);

    @Query("select su from SecuUsers su " +
            "where su.userLogin = :userLogin and su.userPassword = :userPassword")
    SecuUsers getLogin(@Param("userLogin") String userLogin, @Param("userPassword") String userPassword);

    @Query("select su from SecuUsers su JOIN FETCH su.ormaProfessionals op " +
            "where su.userId = :userId and op.profId = :profId")
    SecuUsers getUserAndProfessional(@Param("userId") Integer userId, @Param("profId") Integer profId);

}
