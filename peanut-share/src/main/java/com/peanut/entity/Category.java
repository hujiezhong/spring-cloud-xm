package com.peanut.entity;


import java.io.Serializable;

/**
 * 分类表
 */
public class Category implements Serializable {

    private Integer cid;        //分类id
    private String cname;   //分类名称
    private Category parent;   //父分类
    private int nav;        //是否在导航栏显示 1为true 0为false

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public int getNav() {
        return nav;
    }

    public void setNav(int nav) {
        this.nav = nav;
    }
}
