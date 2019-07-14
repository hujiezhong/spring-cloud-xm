package com.peanut.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 评论表
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = 5744865321759594494L;
    private int coId;
    private Product product;    //商品
    private User user;//用户
    private String coContent;  //评论内容
    @JSONField(format="yyyy-MM-dd mm:dd:ss")
    private Date coCreateTime;  //评论时间
    private int goodorbad;    //好坏评  1：好 2：坏
    private List<Reply> replies;    //回复
    private List<CommentImage> commentImages;   //图片

    public List<CommentImage> getCommentImages() {
        return commentImages;
    }

    public void setCommentImages(List<CommentImage> commentImages) {
        this.commentImages = commentImages;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getGoodorbad() {
        return goodorbad;
    }

    public void setGoodorbad(int goodorbad) {
        this.goodorbad = goodorbad;
    }

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
