package com.whkobe.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Product implements Serializable{
    private static final long serialVersionUID = 8665898915150392721L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field
    private int pid;
    @Field
    private int cid;
    @Field
    private String pname;
    @Field
    private double yuanprice;
    @Field
    private double shopprice;
    @Field
    private int rateid;
    @Field
    private String image;
    @Field
    private String description;
    @Field
    private int flag;
    @Field
    private int inventory;
    @Field
    private String createtime;
    @Field
    private int postage;
    @JsonIgnore(true)
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    private List<Comment> comment;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="pid")
    @JsonIgnoreProperties(value ={"product"} )
    private List<Edition> edition;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getYuanprice() {
        return yuanprice;
    }

    public void setYuanprice(double yuanprice) {
        this.yuanprice = yuanprice;
    }

    public double getShopprice() {
        return shopprice;
    }

    public void setShopprice(double shopprice) {
        this.shopprice = shopprice;
    }

    public int getRateid() {
        return rateid;
    }

    public void setRateid(int rateid) {
        this.rateid = rateid;
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getPostage() {
        return postage;
    }

    public void setPostage(int postage) {
        this.postage = postage;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public List<Edition> getEdition() {
        return edition;
    }

    public void setEdition(List<Edition> edition) {
        this.edition = edition;
    }
}