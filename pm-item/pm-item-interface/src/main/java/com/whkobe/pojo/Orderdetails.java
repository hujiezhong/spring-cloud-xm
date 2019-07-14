package com.whkobe.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


/**
 * 订单详情
 */
@Getter
@Setter
@ToString
@Entity
public class Orderdetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int odid;
    private int odcount;  //商品数量
    private double odsubtotal;  //订单总额
    private int pid;
    private int piid;
    private int eid;
    private long oid;    //所属订单


    public int getOdid() {
        return odid;
    }

    public void setOdid(int odid) {
        this.odid = odid;
    }

    public int getOdcount() {
        return odcount;
    }

    public void setOdcount(int odcount) {
        this.odcount = odcount;
    }

    public double getOdsubtotal() {
        return odsubtotal;
    }

    public void setOdsubtotal(double odsubtotal) {
        this.odsubtotal = odsubtotal;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPiid() {
        return piid;
    }

    public void setPiid(int piid) {
        this.piid = piid;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }
}
