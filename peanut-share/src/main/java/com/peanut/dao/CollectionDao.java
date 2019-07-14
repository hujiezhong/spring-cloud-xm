package com.peanut.dao;

import com.peanut.entity.Collections;
import com.peanut.util.RedisCache;
import com.peanut.util.RedisUtil;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheNamespace(implementation = RedisCache.class)
public interface CollectionDao {

    int inserLove(Integer pid, Integer uid);

    int deleteLove(Integer pid, Integer uid);

    int isLove(Integer pid, Integer uid);

    List<Collections> secbylike(Integer uid);

    int delbycolid (Integer colid);

    int selikecount(Integer uid);

}
