package com.example.wx.Util;

public class XmlUtil {
    public String xmlprocess(String xml){
        String xml1 = xml.replace("&lt;",'<'+"");
        xml1 = xml1.replace("&gt;",'>'+"");
        return xml1;
    }
}
