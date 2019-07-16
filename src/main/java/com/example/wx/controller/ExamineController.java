package com.example.wx.controller;

import com.example.wx.entity.Article;
import com.example.wx.entity.Token;
import com.example.wx.service.ExamineService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jump")
public class ExamineController {
    @Autowired
    private ExamineService es;

    private  static  Token tk;

    /**
     * 微信验证签名
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(@RequestParam(name = "signature",required = false)String signature,
                       @RequestParam(name = "timestamp" ,required = false)String timestamp,
                       @RequestParam(name = "nonce",required = false)String nonce,
                       @RequestParam(name = "echostr",required = false)String echostr) throws IOException {
        /**
         * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
         * timestamp	时间戳
         * nonce	随机数
         * echostr	随机字符串
         */
        System.out.println("signature: "+signature);
        System.out.println("timestamp: "+timestamp);
        System.out.println("nonce: "+nonce);
        System.out.println("echostr: "+echostr);
        System.out.println("connect success");
       boolean a = es.examine(timestamp,nonce,signature);
        if(a){
            System.out.println("校验成功");
            return echostr;
        }else {
            System.out.println("校验失败");
            return "test";
        }
    }

    /**
     * 回复微信公众号发来的消息
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws IOException
     */
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ResponseBody
    public void test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        Map<String,String> map = new HashMap<>();
        map =  es.message(httpServletRequest.getInputStream());

        switch (map.get("MsgType")){
            case "text":
                String msg = map.get("Content");
                if(msg.equals("图文")){
                    List<Article> articels = new ArrayList<>();
                    Article articel = new Article("图文消息","第一条图文消息","https://img.nicepsd.com/49/ab/49ab418c18ea4f1f50fa3749af9da56c?imageView2/2/w/1000","www.baidu.com");
                    articels.add(articel);
                    String xml = es.response(map,articels);
                    String xml1 = xml.replace("&lt;",'<'+"");
                    xml1 = xml1.replace("&gt;",'>'+"");
                    System.out.println(xml1);
                    httpServletResponse.setCharacterEncoding("utf-8");
                    httpServletResponse.getWriter().print(xml1);
                    httpServletResponse.getWriter().flush();
                    return;
                }
                String in = httpServletRequest.getQueryString();
                System.out.println(in);
                System.out.println(map);
                //回复文字消息
                String xml = es.reaponse(map,"hahaha");
                String xml1 = xml.replace("&lt;",'<'+"");
                xml1 = xml1.replace("&gt;",'>'+"");
                System.out.println(xml1);
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.getWriter().print(xml1);
                httpServletResponse.getWriter().flush();
                break;
            case "event":
                String msg1 = map.get("Status");
                if(msg1.equals("success")){
                    System.out.println("推送成功");
                }else {
                    System.out.println("推送失败");
                }
        }
    }

    /**
     * 获取token并将其存储起来，同时设置过期时间
     */
    @RequestMapping(value = "/test2")
    @ResponseBody
    public void test2(){
         String access_token =  es.GETTOKEN();
        JSONObject json = JSONObject.fromObject(access_token);
        String token = json.getString("access_token");
        String expires = json.getString("expires_in");
        tk = new Token(token,expires);
    }

    @RequestMapping(value = "/test3")
    @ResponseBody
    public void  test3(){
        if(tk==null||tk.isexpired()){
            test2();
            System.out.println(tk);
        }else {
            System.out.println(tk);
        }
    }
}
