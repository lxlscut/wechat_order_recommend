package com.example.wx.Util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class PostUtil {
    public static String post(String url,String data){
        try {
            URL urlobj = new URL(url);
            URLConnection connection = urlobj.openConnection();
            //打开发送数据的开关
            connection.setDoOutput(true);
            //获取输出流
            OutputStream os = connection.getOutputStream();
            //写出数据
            os.write(data.getBytes());
            //关闭输入输出流
            os.close();

            //获取输入流
            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while((len=is.read(b))!=-1){
                sb.append(new String(b,0,len));
                return sb.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
