package com.example.wx.service;

import com.example.wx.dao.OrderDetailMapper;
import com.example.wx.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailMapper odm;
    @Override
    public List<String> picurl(String orderid) {
        List<OrderDetail> list = odm.selectbyorderid(orderid);
        List<String> picurl = new ArrayList<>();
        for(OrderDetail orderDetail : list){
            StringBuilder sb = new StringBuilder();
            sb.append("/image/");
            String path[] = orderDetail.getProductAddress().split("//");
            int n = path.length;
            System.out.println("路径的长度为"+n);
            sb.append(path[n-2]);
            sb.append("//");
            sb.append(path[n-1]);
            String url = sb+"";
            System.out.println(url);
            picurl.add(url);
        }
        return picurl;
    }
}
