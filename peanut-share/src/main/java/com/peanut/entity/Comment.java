package com.peanut.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 评论表
 */
public class Comment implements Serializable {

    private int coId;
    private User user;//用户
    private String coContent;  //评论内容
    private Date coCreateTime;  //评论时间

    public int getCoId() {
        return coId;
    }

    public void setCoId(int coId) {
        this.coId = coId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCoContent() {
        return coContent;
    }

    public void setCoContent(String coContent) {
        this.coContent = coContent;
    }

    public Date getCoCreateTime() {
        return coCreateTime;
    }

    public void setCoCreateTime(Date coCreateTime) {
        this.coCreateTime = coCreateTime;
    }
}
