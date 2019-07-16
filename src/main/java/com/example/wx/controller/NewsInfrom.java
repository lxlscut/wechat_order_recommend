package com.example.wx.controller;

import com.example.wx.dao.OrderMasterMapper;
import com.example.wx.entity.OrderMaster;
import com.example.wx.entity.Template;
import com.example.wx.service.ExamineService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import static com.example.wx.Util.PostUtil.post;


@Configuration
@EnableScheduling
@Component
public class NewsInfrom {
    @Autowired
    private OrderMasterMapper omm;
    @Autowired
    private ExamineService es;
    static int i = 0;
    @Scheduled(cron = "0 0/1 * * * ?")
    public void inform(){
        String access_token = es.GETTOKEN();
        JSONObject json = JSONObject.fromObject(access_token);
        System.out.println(json);
        String token = json.getString("access_token");
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token;
        int j = omm.orderno();
         if(j>i){
             List<OrderMaster> list = omm.selectinform(i,j);
             for(OrderMaster o:list){
                 Template template = new Template();
                 template.setTouser("oV5XHs5PJukeB4c91DWLj5Fk4Gj8");
                 template.setTemplate_id("XDkXIHm2fidLmyaaEk1cPbuDjkZc25XAuSVOLH63GjM");
                 template.setUrl("http://64eb332a.ngrok.io/wx/show?orderid="+o.getOrderId());
                 template.setTopcolor("#173177");
                 template.add("first","新订单\n","#173177");
                 template.add("orderid",o.getOrderId()+"\n","#173177");
                 template.add("padid",o.getBuyerPadId()+"\n","#173177");
                 template.add("picture","201821011361\n","#173177");
                 template.add("time", System.currentTimeMillis()/1000+""+"\n","#173177");
                 template.add("remark","哈哈\n","#173177");
                 String data = template.build();
                 System.out.println(template.toString());
                 String result = post(url,data);
             }
         }
        System.out.println("hh");
    }
}

