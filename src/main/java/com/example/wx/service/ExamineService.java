package com.example.wx.service;

import com.example.wx.entity.Article;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ExamineService {
    boolean examine(String timestamp,String nonce,String signature);
    Map<String,String> message(InputStream inputStream);
    String reaponse(Map<String,String> map,String content);
    String response(Map<String, String> map, List<Article> articels);
    String GETTOKEN();
}
