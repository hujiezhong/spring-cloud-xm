package com.peanut.service;

import com.peanut.entity.Reply;

import java.util.List;

public interface ReplyService {


    List<Reply> replyByCoid(Integer coId);

    int insertReply(Reply reply);
}
