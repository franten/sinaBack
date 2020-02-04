package com.alfatecsistemas.sina.service;

import com.alfatecsistemas.sina.domain.SecuUsers;
import javassist.NotFoundException;

import java.util.List;

public interface UsersService {

    List<SecuUsers> getUsers();

    SecuUsers getUser(Integer userId);

    SecuUsers getLogin(String name, String password);

    SecuUsers getUserAndProfessional(Integer userId, Integer profId);

    SecuUsers updateUser(Integer userId, String name, String password) throws NotFoundException;

    SecuUsers insertUser(Integer userId, String name, String password) throws Exception;

    SecuUsers deleteUser(Integer userId) throws NotFoundException;
}
