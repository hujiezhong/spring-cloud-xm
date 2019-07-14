package com.peanut.dao;

import com.peanut.entity.Product;
import com.peanut.util.RedisCache;
import com.peanut.util.RedisUtil;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
//@CacheNamespace(implementation = RedisCache.class)
public interface ProductDao {

    List<Product> select();

    List<Product> selectNav(Integer cid, Integer size);

    List<Product> productByFuCid(Integer cid, Integer size);

    List<Product> productByLike(String pname, Integer size);

    Product selectProductImageEdition(Integer pid);
}
