package com.peanut.service.impl;

import com.peanut.dao.CommentDao;
import com.peanut.entity.Comment;
import com.peanut.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao cd;

    @Override
    public List<Comment> comment(Integer pid, Integer goodorbad, Integer size) {
        return cd.comment(pid, goodorbad, size);
    }
}
