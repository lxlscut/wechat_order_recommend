package com.example.wx.controller;

import com.example.wx.entity.Template;
import com.example.wx.service.ExamineService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.example.wx.Util.PostUtil.post;


@Controller
public class TemplateController {

    @Autowired
    private ExamineService es;
    @RequestMapping("/hahah")
    public void send(){
        System.out.println("发送模板消息");
        String access_token = es.GETTOKEN();
        JSONObject json = JSONObject.fromObject(access_token);
        String token = json.getString("access_token");
       // String token =  json.getString("access_token");
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token;
        Template template = new Template();
        template.setTouser("oV5XHs5PJukeB4c91DWLj5Fk4Gj8");
        template.setTemplate_id("XDkXIHm2fidLmyaaEk1cPbuDjkZc25XAuSVOLH63GjM");
        template.setUrl("https://hao.360.com/?src=lm&ls=n57cc1a139a");
        template.setTopcolor("#173177");
        template.add("first","新订单\n","#173177");
        template.add("orderid","201821011361\n","#173177");
        template.add("padid","201821011361\n","#173177");
        template.add("picture","201821011361\n","#173177");
        template.add("time", System.currentTimeMillis()/1000+""+"\n","#173177");
        template.add("remark","哈哈\n","#173177");
        String data = template.build();
        System.out.println(data);
        String result = post(url,data);
        System.out.println(result);
        return;
    }
}
