package com.example.wx.token;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Token {

    /**
     * 通过url获取token
     * @param url
     * @return
     */
    public static String gettoken(String url){
        System.out.println("次数为：\n");
        try {
            URL url1 = new URL(url);
            URLConnection urlConnection = url1.openConnection();
            InputStream is = urlConnection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len=is.read(b))!=-1){
                sb.append(new String(b,0,len));
            }
            return String.valueOf(sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
