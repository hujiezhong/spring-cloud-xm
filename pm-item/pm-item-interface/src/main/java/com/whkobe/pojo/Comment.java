package com.whkobe.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.sql.Insert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@ToString
@Entity
public class Comment implements Serializable {
    private static final long serialVersionUID = -3403809822261757343L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coid;
    private int pid;
    private int uid;
   @ManyToOne()
   @JoinColumn(name = "uid",insertable = false,updatable = false)
    private User user;//用户
    /*@ManyToOne
    @JoinColumn(name="pid")
    private Product product;*/
    private String cocontent;  //评论内容
    private Date cocreatetime;  //评论时间

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCoid() {
        return coid;
    }

    public void setCoid(int coid) {
        this.coid = coid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCocontent() {
        return cocontent;
    }

    public void setCocontent(String cocontent) {
        this.cocontent = cocontent;
    }

    public Date getCocreatetime() {
        return cocreatetime;
    }

    public void setCocreatetime(Date cocreatetime) {
        this.cocreatetime = cocreatetime;
    }
}
