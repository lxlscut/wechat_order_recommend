package com.example.wx.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Map;


@XStreamAlias("xml")
public class TextMsg extends Basemessage {
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public TextMsg(Map<String, String> map,String content) {
        super(map);
        this.setMsgType("<![CDATA["+"text"+"]]>");
        this.Content ="<![CDATA["+ content +"]]>";
    }

    @Override
    public String toString() {
        return "TextMsg{" + "Content='" + Content + '\'' + "CreateTime='" +super.getCreateTime()+"FromUserName='" +super.getFromUserName()+"ToUserName='" +super.getToUserName()+'}';
    }
}
