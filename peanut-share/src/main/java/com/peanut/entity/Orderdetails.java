package com.peanut.entity;

import java.io.Serializable;


/**
 * 订单详情
 */
public class Orderdetails implements Serializable {

    private int odId;
    private int odCount;  //商品数量
    private double odSubtotal;  //订单总额
    private Orders order;    //所属订单

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
