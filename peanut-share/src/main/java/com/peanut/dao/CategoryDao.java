package com.peanut.dao;

import com.peanut.entity.Category;
import com.peanut.util.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
//@CacheNamespace(implementation = RedisCache.class)
public interface CategoryDao {

    public List<Category> list();

    List<Category> listNav();

    List<Category> selectParent();

}
