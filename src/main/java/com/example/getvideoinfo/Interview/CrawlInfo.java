package com.example.getvideoinfo.Interview;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URL;


public class CrawlInfo {
//    @Autowired
//    private RestTemplate restTemplate;
//    private static String url="https://example.com/api/postEndpoint";
//    public void sendPostByEntity(StringBuilder content,String title) {
//        // 创建请求头
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        // 可以添加其他请求头参数
//        // 创建请求体
//        String requestBody = "{\"key\":\"value\"}"; // 替换成你的请求体
//    }
//
//    public void runMethod(Integer startnum,Integer endStart){
//        try {
//            for (int i = startnum; i < endStart; i++) {
//                String newUrl=url+i+"/";
//                Document document = Jsoup.parse(new URL(newUrl), 30000);
//                Elements section = document.getElementsByTag("article");
////                text(section);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
