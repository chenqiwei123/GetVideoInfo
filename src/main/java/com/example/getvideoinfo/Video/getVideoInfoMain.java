package com.example.getvideoinfo.Video;

import com.alibaba.fastjson.JSONObject;
import com.example.getvideoinfo.Dao.VideoDao;
import com.example.getvideoinfo.Entity.SaveVideoInfo;
import com.example.getvideoinfo.Interview.GetJavanavcom;
import lombok.val;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class getVideoInfoMain {
    @Autowired
    VideoDao videoDao;

    @Autowired
    GetJavanavcom getJavanavcom;
    final static String baseUrl="https://code-projects.org/page/";

    public void transferEnglish(){
        List<SaveVideoInfo> all = videoDao.findAll();
        for (int i = 0; i < all.size(); i++) {

        }
    }

    @RequestMapping("test")
    public String test(){
        getJavanavcom.runMethod();
        return "success";
    }

    @RequestMapping("writeFileInfo")
    public String writeFileInfo() throws IOException {
        getJavanavcom.writeFileInfo();
        return "success";
    }


    @RequestMapping("index")
    public  void main(String[] args) throws Exception {
        for (int i = 0; i < 19; i++) {
            final Integer j0=i*10;
            final Integer j1=(i+1)*10;
            CompletableFuture<String> completableFuture =CompletableFuture.supplyAsync(() -> {this.runMethod(j0,j1);
            return "";});
        }
//        System.out.println(stringBuffer.toString());
    }

    public void runMethod(Integer startnum,Integer endStart){
        try {
            for (int i = startnum; i < endStart; i++) {
                String newUrl=baseUrl+i+"/";
                Document document = Jsoup.parse(new URL(newUrl), 30000);
                Elements section = document.getElementsByTag("article");
                text(section);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void text(Elements section) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        for (Element s:section){

            try {
                String href = s.getElementsByClass("st-loop-entry-inner").get(0).getElementsByTag("a").attr("href");
                String img = s.getElementsByClass("st-loop-entry-inner").get(0).getElementsByTag("img").attr("src");
                String language = null;
                try {
                    language = s.getElementsByClass("st-loop-entry-inner").get(0).getElementsByClass("st-loop-entry-content").get(0).getElementsByClass("st-button-typo").get(0).getElementsByTag("a").text();
                } catch (Exception e) {

                }
                String title = s.getElementsByClass("st-loop-entry-content").get(0).getElementsByClass("st-loop-entry-title").get(0).getElementsByTag("a").text();
                String content = s.getElementsByClass("st-loop-entry-content").get(0).getElementsByClass("st-loop-entry-excerpt").get(0).text();
                Document document = Jsoup.parse(new URL(href), 30000);
                String download=document.getElementsByClass("st-post-content").get(0).getElementsByTag("a").get(0).attr("href");
                videoDao.updateIndexCalculated(UUID.randomUUID().getLeastSignificantBits(),href,img,language,title,content,download,new Date());
            } catch (IOException e) {

            }
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
