package com.peanut.service;

import com.peanut.entity.User;

import java.util.Map;

public interface UserService {

    public Integer secbyphone(String phone);

    User seubyphone(String phone);

    User sebyname(Map<String,Object> map);

    int intuser(User user);

    int updateU(User user);

    int updateT(User user);
}
