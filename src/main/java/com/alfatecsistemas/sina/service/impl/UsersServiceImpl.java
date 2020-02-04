package com.alfatecsistemas.sina.service.impl;

import com.alfatecsistemas.sina.dao.ProfessionalDao;
import com.alfatecsistemas.sina.domain.OrmaProfessionals;
import com.alfatecsistemas.sina.domain.SecuUsers;
import com.alfatecsistemas.sina.repository.UsersRepository;
import com.alfatecsistemas.sina.service.UsersService;
import com.alfatecsistemas.sina.utils.EncryptUtils;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ProfessionalDao professionalDao;

    @Override
    public List<SecuUsers> getUsers() {
        return usersRepository.findAll();
    }

    @Override
    public SecuUsers getUser(Integer userId) {
        return usersRepository.findOne(userId);
    }

    public SecuUsers getUserByProfId(Integer profId) {
        return usersRepository.getUserByProfId(profId);
    }

    @Override
    public SecuUsers getLogin(String name, String password) {
        String passwordSha1 = EncryptUtils.sha1(password);
        return usersRepository.getLogin(name, passwordSha1);
    }

    @Override
    public SecuUsers getUserAndProfessional(Integer userId, Integer profId) {
        return usersRepository.getUserAndProfessional(userId, profId);
    }

    @Override
    public SecuUsers updateUser(Integer userId, String name, String password) throws NotFoundException {
        SecuUsers user = getUser(userId);

        if (user != null) {
            String passwordSha1 = EncryptUtils.sha1(password);

            user.setUserLogin(name);
            user.setUserPassword(passwordSha1);
        } else {
            throw new NotFoundException(String.format("The user with name %s not exists", name));
        }

        return usersRepository.save(user);
    }

    @Override
    public SecuUsers insertUser(Integer profId, String name, String password) throws Exception {
        OrmaProfessionals professional = professionalDao.findOne(profId);
        SecuUsers user = null;

        if (professional == null) {
            String passwordSha1 = EncryptUtils.sha1(password);

            user = new SecuUsers();
            user.setProfId(profId);
            user.setUserLogin(name);
            user.setUserPassword(passwordSha1);

            usersRepository.save(user);
        } else {
            throw new Exception(String.format("The user with profId %s already exists", profId));
        }

        return user;
    }

    @Override
    public SecuUsers deleteUser(Integer userId) throws NotFoundException {
        SecuUsers user = usersRepository.findOne(userId);

        if (user != null) {
            usersRepository.delete(user);
        } else {
            throw new NotFoundException(String.format("The user with id %s not exists", userId));
        }

        return user;
    }
}
