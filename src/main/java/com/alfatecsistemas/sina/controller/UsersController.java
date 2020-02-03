package com.alfatecsistemas.sina.controller;

import com.alfatecsistemas.sina.domain.SecuUsers;
import com.alfatecsistemas.sina.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService secuUsersService;

    @GetMapping(headers = "Accept=application/json")
    public List<SecuUsers> getUsers() {
        return secuUsersService.getUsers();
    }
}
