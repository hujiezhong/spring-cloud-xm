package com.whkobe.pojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单
 */
@Getter
@Setter
@ToString
@Entity
public class Orders implements Serializable {
    @Id
    private long oid;
    private int uid;  //用户
    private String oname;  //收货人
    private String otelphone;   //收货人电话
    private String oaddress;    //收货地址
    private Date oordertime;   //收获时间
    private double ototal;  //订单总额
    private int ostate;     //订单状态 1：待发货 2：已配货 3：已发货 4：已接收

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public Date getOordertime() {
        return oordertime;
    }

    public void setOordertime(Date oordertime) {
        this.oordertime = oordertime;
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
