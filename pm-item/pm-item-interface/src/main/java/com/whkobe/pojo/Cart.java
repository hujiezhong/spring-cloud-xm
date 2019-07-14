package com.whkobe.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {

    private static final long serialVersionUID = 1025826135630731110L;
    private int num;
    private int biaoji;
    private Edition edition;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getBiaoji() {
        return biaoji;
    }

    public void setBiaoji(int biaoji) {
        this.biaoji = biaoji;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }
}
