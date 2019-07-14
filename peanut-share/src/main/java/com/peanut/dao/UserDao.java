package com.peanut.dao;


import com.peanut.entity.User;
import com.peanut.util.RedisCache;
import com.peanut.util.RedisUtil;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
//@CacheNamespace(implementation = RedisCache.class)
public interface UserDao {

    public Integer secbyphone(String phone);

    User seubyphone(String phone);

    User sebyname(Map<String,Object> map);

    int intuser(User user);

    int updateU(User user);

    int updateT(User user);

}
