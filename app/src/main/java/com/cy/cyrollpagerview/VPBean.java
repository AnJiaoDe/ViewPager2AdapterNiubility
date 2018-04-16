package com.cy.cyrollpagerview;

/**
 * Created by cy on 2018/4/16.
 */

public class VPBean {
    private String url;
    private String text;

    public VPBean(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
