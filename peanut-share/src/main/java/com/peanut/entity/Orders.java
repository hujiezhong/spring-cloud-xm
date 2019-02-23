package com.peanut.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 订单
 */
public class Orders implements Serializable {

    private int oid;
    private User user;  //用户
    private String oname;  //收货人
    private String otelphone;   //收货人电话
    private String oaddress;    //收货地址
    private Date oorederTime;   //收获时间
    private double ototal;  //订单总额
    private int ostate;     //订单状态 1：待发货 2：已配货 3：已发货 4：已接收

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getOtelphone() {
        return otelphone;
    }

    public void setOtelphone(String otelphone) {
        this.otelphone = otelphone;
    }

    public String getOaddress() {
        return oaddress;
    }

    public void setOaddress(String oaddress) {
        this.oaddress = oaddress;
    }

    public Date getOorederTime() {
        return oorederTime;
    }

    public void setOorederTime(Date oorederTime) {
        this.oorederTime = oorederTime;
    }

    public double getOtotal() {
        return ototal;
    }

    public void setOtotal(double ototal) {
        this.ototal = ototal;
    }

    public int getOstate() {
        return ostate;
    }

    public void setOstate(int ostate) {
        this.ostate = ostate;
    }
}
