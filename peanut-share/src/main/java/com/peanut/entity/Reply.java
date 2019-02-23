package com.peanut.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 回复表
 */
public class Reply implements Serializable {

    private int rid;
    private Comment comment;  //评论
    private User user;   //用户
    private String rcontent;  //回复内容
    private Date rcreateTime;  //回复时间

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRcontent() {
        return rcontent;
    }

    public void setRcontent(String rcontent) {
        this.rcontent = rcontent;
    }

    public Date getRcreateTime() {
        return rcreateTime;
    }

    public void setRcreateTime(Date rcreateTime) {
        this.rcreateTime = rcreateTime;
    }
}
