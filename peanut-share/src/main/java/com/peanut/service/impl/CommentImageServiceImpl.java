package com.peanut.service.impl;

import com.peanut.dao.CommentImageDao;
import com.peanut.entity.CommentImage;
import com.peanut.service.CommentImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentImageServiceImpl implements CommentImageService {

    @Autowired
    private CommentImageDao cid;

    @Override
    public int insertCommentImage(CommentImage ci) {
        return cid.insertCommentImage(ci);
    }
}
