package com.example.service.impl;

import com.example.dao.UserDao;
import com.example.pojo.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getLoginUser(User user) {
        return userDao.getLoginUser(user);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User findByLoginId(String loginId) {
        return userDao.findByLoginId(loginId);
    }
}
