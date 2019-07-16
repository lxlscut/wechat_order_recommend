package com.example.wx.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.springframework.stereotype.Component;

import java.util.Map;

@XStreamAlias("xml")
public class Basemessage {
    @XStreamAlias("ToUserName")
    private  String ToUserName;
    @XStreamAlias("FromUserName")
    private  String FromUserName;
    @XStreamAlias("CreateTime")
    private  String CreateTime;
    @XStreamAlias("MsgType")
    private  String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public Basemessage(Map<String,String> map) {
        this.ToUserName = "<![CDATA["+map.get("FromUserName")+"]]>";
        this.FromUserName ="<![CDATA["+map.get("ToUserName")+"]]>";
        this.CreateTime = System.currentTimeMillis()/1000+"";
    }
}
