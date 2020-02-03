package com.alfatecsistemas.sina.controller;

import com.alfatecsistemas.sina.domain.SecuUsers;
import com.alfatecsistemas.sina.dto.UserDto;
import com.alfatecsistemas.sina.service.UsersService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<SecuUsers> getUsers() {
        return usersService.getUsers();
    }

    @RequestMapping(path = "/{profId}", method = RequestMethod.GET)
    public SecuUsers getUser(@PathVariable Integer profId) {
        return usersService.getUser(profId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public SecuUsers getUserByName(@RequestParam String name) {
        return usersService.getUserByName(name);
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public SecuUsers getLogin(@RequestBody UserDto user) {
        return usersService.getLogin(user.getName(), user.getPassword());
    }

    @RequestMapping(path = "/{profId}", method = RequestMethod.PUT)
    public ResponseEntity<SecuUsers> updateUser(@PathVariable Integer profId, @RequestBody UserDto dto) {

        ResponseEntity response = null;
        try {
            SecuUsers user = usersService.updateUser(profId, dto.getName(), dto.getPassword());
            response = ResponseEntity.ok(user);
        } catch (NotFoundException e) {
            LOGGER.error(e.toString());
            response = ResponseEntity.notFound().build();
        }

        return response;
    }

    @RequestMapping(path = "/{profId}", method = RequestMethod.POST)
    public ResponseEntity<SecuUsers> insertUser(@PathVariable Integer profId, @RequestBody UserDto dto) {
        ResponseEntity response = null;
        try {
            SecuUsers user = usersService.insertUser(profId, dto.getName(), dto.getPassword());
            response = ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            LOGGER.error(e.toString());
            response = ResponseEntity.badRequest().build();
        }

        return response;
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<SecuUsers> deleteUser(@PathVariable Integer userId) {
        ResponseEntity response = null;
        try {
            SecuUsers user = usersService.deleteUser(userId);
            response = ResponseEntity.ok(user);
        } catch (NotFoundException e) {
            LOGGER.error(e.toString());
            response = ResponseEntity.notFound().build();
        }

        return response;
    }
}
