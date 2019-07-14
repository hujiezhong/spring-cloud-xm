package com.peanut.dao;


import com.peanut.entity.ProductImage;
import com.peanut.util.RedisCache;
import com.peanut.util.RedisUtil;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheNamespace(implementation = RedisCache.class)
public interface ProductImagesDao {

    List<ProductImage> selectImageProduct(String color, Integer cid, Integer pid, Integer size);

}
