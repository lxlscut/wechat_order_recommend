package com.example.wx.Configration;

import com.example.wx.dao.OrderDetailMapper;
import com.example.wx.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 推送文件的时候把本地图片映射成网络路径，浏览器一般不支持本地文件直接映射
 */
@Configuration
@Component
public class UrlConfig implements WebMvcConfigurer {
    @Autowired
    private OrderDetailMapper odm;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String url = null;
        for(int i = 0;i<100;i++){
            try{
                StringBuilder sb = new StringBuilder();
                OrderDetail orderDetail = odm.selectByPrimaryKey(String.valueOf(i));
                String[] path = orderDetail.getProductAddress().split("//");
                int n = path.length;
                sb.append("file:");
                for(int j = 0;j<n-2;j++){
                   sb.append(path[j]);
                   sb.append("//");
                }
                url = sb.toString();
                System.out.println(url);
                break;
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
        registry.addResourceHandler("/image/**").addResourceLocations(url);
    }
}
