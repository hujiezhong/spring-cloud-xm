package com.peanut.entity;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 评论的html
 */
public class CommentHTML implements Serializable {

    private static final long serialVersionUID = -5159943964351625656L;
    @Id
    private int coId;   //评论的mongdb  id
    private String HTML;    //页面

    public int getCoId() {
        return coId;
    }

    public void setCoId(int coId) {
        this.coId = coId;
    }

    public String getHTML() {
        return HTML;
    }

    public void setHTML(String HTML) {
        this.HTML = HTML;
    }
}
