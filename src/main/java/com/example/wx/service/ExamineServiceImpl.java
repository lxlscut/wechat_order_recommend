package com.example.wx.service;

import com.example.wx.entity.*;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.wx.token.Token.gettoken;

@Service
public class ExamineServiceImpl implements ExamineService {
    private static final String TOKEN = "lxlscut";
    private static final String GET_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String appID = "wxe411ebf603d5587a";
    private static final String appsecret="a23649cd94aa12ae1e3926029fcefac1";

    int i = 0;

    /**
     * 接入验证
     * @param timestamp
     * @param nonce
     * @param signature
     * @return
     */
    @Override
    public boolean examine(String timestamp, String nonce,String signature) {
    //先对其进行字典排序
        String [] strings = new String[]{timestamp,nonce,TOKEN};
        Arrays.sort(strings);

        String str = strings[0]+strings[1]+strings[2];

        String sign = DigestUtils.shaHex(str);

        System.out.println("加密之后的字符串："+sign);
        if(sign.equals(signature))
            return  true;
        else
        return false;
    }

    /**
     * 获取发送消息者的信息并将其放到map中
     * @param inputStream
     * @return
     */
    @Override
    public Map<String, String> message(InputStream inputStream) {
        SAXReader reader = new SAXReader();
        Map<String,String> map = new HashMap<>();
        try {
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            for(Element element:elements){
                map.put(element.getName(),element.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将map中的数据传入响应当中，文本消息，暂未封装图文消息
      * @param map
     * @param content
     * @return
     */
    @Override
    public String reaponse(Map<String, String> map,String content) {
        Basemessage tm = new TextMsg(map,content);
        System.out.println(tm.toString());
        return beantoxml(tm);
    }

    @Override
    public String response(Map<String, String> map, List<Article> articels) {
        Basemessage tm = new NewsPicture(map,articels);
        return beantoxml(tm);
    }

    /**
     * 获取token
     * @return
     */
    @Override
    public String GETTOKEN() {
        i++;
        System.out.println("调用token的数目为"+i+"次");
        //获取有关url的token
         String uuu =  GET_TOKEN_URL.replace("APPID",appID).replace("APPSECRET",appsecret);
//        System.out.println(uuu);
        //通过url来获取token
        String bbb = gettoken(uuu);
        System.out.println("第一次获取的accesstoken"+bbb);
//        String ccc = gettoken(uuu);
//        System.out.println("第二次获取的accesstoken"+ccc);
        return bbb;
    }

    /**
     * 对response中的数据开头结尾换成xml
     * @param bms
     * @return
     */
    private String beantoxml(Basemessage bms){
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMsg.class);
        xStream.processAnnotations(PhotoMsg.class);
        xStream.processAnnotations(NewsPicture.class);
        xStream.processAnnotations(Article.class);
        String xml =  xStream.toXML(bms);
        System.out.println(xml);
        return xml;
    }
}
