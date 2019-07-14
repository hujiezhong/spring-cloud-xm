package com.peanut.dao;

import com.peanut.entity.CommentImage;
import com.peanut.util.RedisCache;
import com.peanut.util.RedisUtil;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

@Repository
//@CacheNamespace(implementation = RedisCache.class)
public interface CommentImageDao {

    int insertCommentImage(CommentImage ci);


}
