package com.whkobe.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 387570338484550941L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String username; //用户名 唯一 可以用来登陆
    private String password; //密码
    private String emal;     //邮箱 可以用来登陆
    private String phone;    //手机号码
    private String card;     //身份证
    private String address;  //收获地址
    private int level;       //用户级别 1：管理员  0：普通用户
    private String name;     //昵称 网名
    private int status;      //状态 1：在线  0：离线
    private String touxiangpath;
   /* @OneToMany(fetch=FetchType.EAGER,cascade = {CascadeType.ALL},mappedBy = "comment")
    private List<Comment> comment;*/

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmal() {
        return emal;
    }

    public void setEmal(String emal) {
        this.emal = emal;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTouxiangpath() {
        return touxiangpath;
    }

    public void setTouxiangpath(String touxiangpath) {
        this.touxiangpath = touxiangpath;
    }
}
