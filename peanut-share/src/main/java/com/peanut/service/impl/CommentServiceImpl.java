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

    @Override
    public List<Comment> commentAndReply(Integer pid, Integer goodorbad) {
        return cd.commentAndReply(pid,goodorbad);
    }

    @Override
    public int insertComment(Comment com) {
        return cd.insertComment(com);
    }

    @Override
    public int selectCount(Integer pid,Integer goodorbad) {
        return cd.selectCount(pid,goodorbad);
    }

    @Override
    public Comment commentByCoId(Integer coId) {
        return cd.commentByCoId(coId);
    }
}
