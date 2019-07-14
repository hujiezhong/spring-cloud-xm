package com.peanut.dao;

import com.peanut.entity.Reply;
import com.peanut.util.RedisCache;
import com.peanut.util.RedisUtil;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheNamespace(implementation = RedisCache.class)
public interface ReplyDao {

    List<Reply> replyByCoid(Integer coId);

    int insertReply(Reply reply);

}
