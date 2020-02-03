package com.alfatecsistemas.sina.service;

import com.alfatecsistemas.sina.domain.SecuUsers;
import javassist.NotFoundException;

import java.util.List;

public interface UsersService {

    List<SecuUsers> getUsers();

    SecuUsers getUser(Integer profId);

    SecuUsers getUserByName(String name);

    SecuUsers getLogin(String name, String password);

    SecuUsers updateUser(Integer profId, String name, String password) throws NotFoundException;

    SecuUsers insertUser(Integer profId, String name, String password) throws Exception;

    SecuUsers deleteUser(Integer profId) throws NotFoundException;
}
