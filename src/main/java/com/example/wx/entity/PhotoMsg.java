package com.example.wx.entity;

import java.util.Map;

public class PhotoMsg extends Basemessage {
    private String PicUrl;
    private String MediaId;

    public PhotoMsg(Map<String, String> map) {
        super(map);
        this.setMsgType("<![CDATA["+"text"+"]]>");
        this.PicUrl ="<![CDATA["+ PicUrl +"]]>";
        this.MediaId = "<![CDATA["+ MediaId +"]]>";
    }
}
