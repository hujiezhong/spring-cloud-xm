package com.peanut.dao;

import com.peanut.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentDao {

    List<Comment> comment(Integer pid, Integer goodorbad, Integer size);

}
