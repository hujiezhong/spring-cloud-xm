package com.peanut.entity;

import java.io.Serializable;

public class Edition implements Serializable {

    private static final long serialVersionUID = 261299201760497020L;
    private int eid;
    private Product product;    //商品
    private double eYuanPrice;  //版本原价
    private double eShopPrice;  //版本商城价
    private String eName;       //版本名

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double geteYuanPrice() {
        return eYuanPrice;
    }

    public void seteYuanPrice(double eYuanPrice) {
        this.eYuanPrice = eYuanPrice;
    }

    public double geteShopPrice() {
        return eShopPrice;
    }

    public void seteShopPrice(double eShopPrice) {
        this.eShopPrice = eShopPrice;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }
}
