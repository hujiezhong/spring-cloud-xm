package com.peanut.service;

import com.peanut.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> comment(Integer pid, Integer goodorbad, Integer size);

}
