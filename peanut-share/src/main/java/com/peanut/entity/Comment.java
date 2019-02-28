package com.peanut.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;


/**
 * 评论表
 */
public class Comment implements Serializable {

    private int coId;
    private Product product;    //商品
    private User user;//用户
    private String coContent;  //评论内容
    @JSONField(format="yyyy-MM-dd")
    private Date coCreateTime;  //评论时间
    private int goodorbad;    //好坏评  1：好 2：坏

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
