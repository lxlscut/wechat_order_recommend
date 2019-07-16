package com.example.wx.controller;

import com.example.wx.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wx")
public class Picture {
    @Autowired
    private OrderDetailService ods;
    /**
     * 图片展示页面
     * @return
     */
    @RequestMapping("/show")
    public String show(@RequestParam(name = "orderid") String orderid, Map<String,Object> data){
        System.out.println("hahahha");
        List<String> url = ods.picurl(orderid);
        data.put("picture",url);
        System.out.println("orderid");
        return "pictureview";
    }
}
