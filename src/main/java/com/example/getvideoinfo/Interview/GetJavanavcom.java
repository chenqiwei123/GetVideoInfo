package com.example.getvideoinfo.Interview;

import com.example.getvideoinfo.Dao.JavaInterViewDao;
import com.example.getvideoinfo.Dao.VideoDao;
import com.example.getvideoinfo.Entity.JavaBaseInfo;
import com.overzealous.remark.Remark;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class GetJavanavcom {
    @Autowired
    JavaInterViewDao JavaInterViewDao;
    final static String baseUrl = "https://www.javanav.com/interview/";
    final static String firstUrl = "https://www.javanav.com/interview/93b0069472fd479393006c0e73043fc4.html";

    public void writeFileInfo() throws IOException {
        File file=new File("output.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        // 将数据库的数据存储在txt文件中
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        List<JavaBaseInfo> javaBaseInfos = JavaInterViewDao.selectJavaInterviewInfo();
        for (JavaBaseInfo javaBaseInfo : javaBaseInfos) {
            writer.write("## "+javaBaseInfo.getTitle()+" \n \n " +javaBaseInfo.getContent());
            writer.newLine();
        }
        writer.close();
    }
    public void runMethod() {
        try {
            String newUrl = firstUrl ;
            Document document = Jsoup.parse(new URL(newUrl), 300);
            Elements section = document.getElementsByClass("badge");
            text(section);
            Thread.sleep(50);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void text(Elements section) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();

        String content = "";
        String id = "";
        String title = "";
        Document document = null;
        Elements elements=null;
        Remark remark = new Remark();
        for (Element s : section) {

            try {
                id = s.id();
                title = s.attr("title");
                document = Jsoup.parse(new URL(baseUrl + id + ".html"), 1000);
                elements = document.getElementsByClass("interview_detail");
                content = remark.convert(elements.toString());
                JavaInterViewDao.insertJavaInterviewInfo(id, title, content);
                Thread.sleep(50);
            } catch (Exception e) {
                System.out.println("出异常了"+id);
            }
        }
    }
}
