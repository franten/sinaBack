package com.alfatecsistemas.sina.service.impl;

import com.alfatecsistemas.sina.repository.UsersRepository;
import com.alfatecsistemas.sina.domain.SecuUsers;
import com.alfatecsistemas.sina.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public List<SecuUsers> getUsers() {
        return usersRepository.findAll();
    }
}
