package com.peanut.entity;

import java.io.Serializable;


/**
 * 订单详情
 */
public class Orderdetails implements Serializable {

    private static final long serialVersionUID = 4643554670352667609L;
    private int odId;
    private int odCount;  //商品数量
    private double odSubtotal;  //订单总额
    private Orders order;    //所属订单
    private Product product;    //商品
    private Edition edition;    //版本
    private ProductImage productImage;  //图片颜色

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getOdId() {
        return odId;
    }

    public void setOdId(int odId) {
        this.odId = odId;
    }

    public int getOdCount() {
        return odCount;
    }

    public void setOdCount(int odCount) {
        this.odCount = odCount;
    }

    public double getOdSubtotal() {
        return odSubtotal;
    }

    public void setOdSubtotal(double odSubtotal) {
        this.odSubtotal = odSubtotal;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
}
