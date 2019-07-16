package com.example.wx.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@XStreamAlias("xml")
public class NewsPicture extends Basemessage {

    @XStreamAlias("ArticleCount")
    private String ArticleCount;

    public String getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(String articleCount) {
        ArticleCount = articleCount;
    }

    @XStreamAlias("Articles")
    private  List<Article> articels = new ArrayList<>();

    public List<Article> getArticels() {
        return articels;
    }

    public void setArticels(List<Article> articels) {
        this.articels = articels;
    }



    public NewsPicture(Map<String, String> map,List<Article> articels) {
        super(map);
        this.setMsgType("<![CDATA["+"news"+"]]>");
        this.ArticleCount = "1";
        this.articels = articels;
    }
}
