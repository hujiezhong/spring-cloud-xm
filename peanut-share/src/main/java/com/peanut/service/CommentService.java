package com.peanut.service;

import com.peanut.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {

    List<Comment> comment(Integer pid, Integer goodorbad, Integer size);

    List<Comment> commentAndReply(Integer pid, Integer goodorbad);

    int insertComment(Comment com);

    int selectCount(Integer pid,Integer goodorbad);

    Comment commentByCoId(Integer coId);

}
