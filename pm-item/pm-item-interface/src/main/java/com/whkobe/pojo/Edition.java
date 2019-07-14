package com.whkobe.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
public class Edition implements Serializable {


    private static final long serialVersionUID = 8912299974750285534L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eid;
    private int pid;
    private double eyuanprice;
    private double eshopprice;
    private String ename;
    @ManyToOne
    @JsonIgnoreProperties(value = {"edition"})
    @JoinColumn(name = "pid",insertable = false,updatable = false)
    private Product product;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public double getEyuanprice() {
        return eyuanprice;
    }

    public void setEyuanprice(double eyuanprice) {
        this.eyuanprice = eyuanprice;
    }

    public double getEshopprice() {
        return eshopprice;
    }

    public void setEshopprice(double eshopprice) {
        this.eshopprice = eshopprice;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
