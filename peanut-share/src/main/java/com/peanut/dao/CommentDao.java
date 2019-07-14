package com.peanut.dao;

import com.peanut.entity.Comment;
import com.peanut.util.RedisCache;
import com.peanut.util.RedisUtil;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
//@CacheNamespace(implementation = RedisCache.class)
public interface CommentDao {

    List<Comment> comment(Integer pid, Integer goodorbad, Integer size);

    List<Comment> commentAndReply(@Param(value="pid") Integer pid, @Param(value = "goodorbad") Integer goodorbad);

    int insertComment(Comment com);

    int selectCount(@Param(value = "pid") Integer pid, @Param(value = "goodorbad") Integer goodorbad);

    Comment commentByCoId(@Param("coId") Integer coId);

}
