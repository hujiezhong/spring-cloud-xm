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
 * 收藏表
 */
@Getter
@Setter
@ToString
@Entity
public class Collections implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int colid;
    private int pid;   //商品
    private int uid;   //用户

    public int getColid() {
        return colid;
    }

    public void setColid(int colid) {
        this.colid = colid;
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
}
