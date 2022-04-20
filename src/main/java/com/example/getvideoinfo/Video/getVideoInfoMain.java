package com.example.getvideoinfo.Video;

import com.alibaba.fastjson.JSONObject;
import com.example.getvideoinfo.Dao.VideoDao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class getVideoInfoMain {
    @Autowired
    VideoDao videoDao;
    @RequestMapping("index")
    public  void main(String[] args) throws Exception {
        String url="https://code-projects.org/page/";
        for (int i = 0; i < 182; i++) {
            String newUrl=url+i+"/";
            Document document = Jsoup.parse(new URL(newUrl), 30000);
            Elements section = document.getElementsByTag("article");
            text(section);
        }
//        System.out.println(stringBuffer.toString());
    }
    public void text(Elements section) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        for (Element s:section){

            String href = s.getElementsByClass("st-loop-entry-inner").get(0).getElementsByTag("a").attr("href");
            String img = s.getElementsByClass("st-loop-entry-inner").get(0).getElementsByTag("img").attr("src");
            String language = s.getElementsByClass("st-loop-entry-inner").get(0).getElementsByClass("st-loop-entry-content").get(0).getElementsByClass("st-button-typo").get(0).getElementsByTag("a").text();
            String title = s.getElementsByClass("st-loop-entry-content").get(0).getElementsByClass("st-loop-entry-title").get(0).getElementsByTag("a").text();
            String content = s.getElementsByClass("st-loop-entry-content").get(0).getElementsByClass("st-loop-entry-excerpt").get(0).text();
            Document document = Jsoup.parse(new URL(href), 30000);
            String download=document.getElementsByClass("st-post-content").get(0).getElementsByTag("a").get(0).attr("href");
            videoDao.updateIndexCalculated(UUID.randomUUID().getLeastSignificantBits(),href,img,language,title,content,download);
        }
    }

    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
