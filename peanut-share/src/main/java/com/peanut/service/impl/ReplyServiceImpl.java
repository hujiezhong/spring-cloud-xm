package com.peanut.service.impl;

import com.peanut.dao.ReplyDao;
import com.peanut.entity.Reply;
import com.peanut.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyDao pd;

    @Override
    public List<Reply> replyByCoid(Integer coId) {
        return pd.replyByCoid(coId);
    }

    @Override
    public int insertReply(Reply reply) {
        return pd.insertReply(reply);
    }
}
