package com.example.wx.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("item")
public class Article {
    @XStreamAlias("Title")
    private String Title;
    @XStreamAlias("Description")
    private String Description;
    @XStreamAlias("PicUrl")
    private String PicUrl;
    @XStreamAlias("Url")
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Article(String title, String description, String picUrl, String url) {
        this.Title = "<![CDATA["+title+"]]>";
        this.Description = "<![CDATA["+description+"]]>";
        this.PicUrl = "<![CDATA["+picUrl+"]]>";
        this.Url = "<![CDATA["+url+"]]>";
    }
}
