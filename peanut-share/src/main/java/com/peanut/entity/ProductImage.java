package com.peanut.entity;


import java.io.Serializable;

/**
 * 商品图片
 */
public class ProductImage implements Serializable {

    private static final long serialVersionUID = 4348790559382578126L;
    private int piId;
    private Product product;   //商品
    private String fileName;  //商品图片地址
    private int pflag;   //暂时不用
    private String color;   //颜色  没有颜色为null

    public int getPiId() {
        return piId;
    }

    public void setPiId(int piId) {
        this.piId = piId;
    }

    public Product getProd() {
        return product;
    }

    public void setProd(Product product) {
        this.product = product;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getPflag() {
        return pflag;
    }

    public void setPflag(int pflag) {
        this.pflag = pflag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
