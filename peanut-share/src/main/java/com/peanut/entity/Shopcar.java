package com.peanut.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 购物车
 */
public class Shopcar implements Serializable {

    private static final long serialVersionUID = 601147306898209003L;
    private int sid;
    private User user;  //用户
    private Product product;    //商品
    private Integer num;    //数量
    @JSONField(format="yyyy-MM-dd")
    private Date xinTime;   //新增时间
    @JSONField(format="yyyy-MM-dd")
    private Date updateTime;    //修改时间

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getXinTime() {
        return xinTime;
    }

    public void setXinTime(Date xinTime) {
        this.xinTime = xinTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
