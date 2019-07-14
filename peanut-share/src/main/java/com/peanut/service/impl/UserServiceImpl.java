package com.peanut.service.impl;

import com.peanut.dao.UserDao;
import com.peanut.entity.User;
import com.peanut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao ud;

    @Override
    public Integer secbyphone(String phone) {
        return ud.secbyphone(phone);
    }

    @Override
    public int intuser(User user) {
        return ud.intuser(user);
    }

    @Override
    public User sebyname(Map<String,Object> map) {
        return ud.sebyname(map);
    }

    @Override
    public int updateT(User user) {
        return ud.updateT(user);
    }

    @Override
    public User seubyphone(String phone) {
        return ud.seubyphone(phone);
    }

    @Override
    public int updateU(User user) {
        return ud.updateU(user);
    }
}
