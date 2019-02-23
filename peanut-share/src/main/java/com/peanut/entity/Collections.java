package com.peanut.entity;


import java.io.Serializable;

/**
 * 收藏表
 */
public class Collections implements Serializable {

    private int colId;
    private Product prod;   //商品
    private User user;   //用户

    public int getColId() {
        return colId;
    }

    public void setColId(int colId) {
        this.colId = colId;
    }

    public Product getProd() {
        return prod;
    }

    public void setProd(Product prod) {
        this.prod = prod;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
