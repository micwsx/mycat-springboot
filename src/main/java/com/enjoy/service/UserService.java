package com.enjoy.service;

import com.enjoy.dao.OrderDao;
import com.enjoy.dao.UserDao;
import com.enjoy.domain.Order;
import com.enjoy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public int add(User user) {
        return userDao.add(user);
    }

    public List<User> getUsers() {
        return userDao.selectAll();
    }

    public void batchAdd(List<User> users) {
        userDao.batchInsert(users);
    }


    public void batchSequenceInsert(List<User> users) {
        userDao.batchSequenceInsert(users);
    }


}
