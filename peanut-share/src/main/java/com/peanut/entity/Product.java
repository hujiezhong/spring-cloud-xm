package com.peanut.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品表
 */
public class Product implements Serializable {

    private int pid;
    private Category category;         //所属分类
    private String pname;    //商品名称
    private double yuanPrice;//原价
    private double shopPrice;//商城价
    private Rate rate;      //折扣
    private String image;    //缩略图地址
    private String description; //详情
    private int flag;        //状态 1:有货  0：没货  还不是很清楚干嘛用  卖出多少 销量
    private int inventory;   //库存

    private Date createTime; //上架时间
    private int postage;     //是否包邮  1：是 0：不是

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getYuanPrice() {
        return yuanPrice;
    }

    public void setYuanPrice(double yuanPrice) {
        this.yuanPrice = yuanPrice;
    }

    public double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(double shopPrice) {
        this.shopPrice = shopPrice;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @JSONField(format="yyyy-MM-dd")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getPostage() {
        return postage;
    }

    public void setPostage(int postage) {
        this.postage = postage;
    }

}
