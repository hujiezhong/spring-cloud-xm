package com.peanut.entity;

import java.io.Serializable;

/**
 * 评论图片表
 */
public class CommentImage implements Serializable {

    private static final long serialVersionUID = 7219829621782204643L;
    private int ciId;
    private String ciFileName;  //图片
    private Integer coId;       //评论

    public int getCiId() {
        return ciId;
    }

    public void setCiId(int ciId) {
        this.ciId = ciId;
    }

    public String getCiFileName() {
        return ciFileName;
    }

    public void setCiFileName(String ciFileName) {
        this.ciFileName = ciFileName;
    }

    public Integer getCoId() {
        return coId;
    }

    public void setCoId(Integer coId) {
        this.coId = coId;
    }
}
