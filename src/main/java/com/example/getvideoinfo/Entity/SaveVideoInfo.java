package com.example.getvideoinfo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SaveVideoInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String href;
    private String img;
    private String language;
    private String title;
    private String content;

    public SaveVideoInfo(Long id, String href, String img, String language, String title, String content) {
        this.id = id;
        this.href = href;
        this.img = img;
        this.language = language;
        this.title = title;
        this.content = content;
    }

    public SaveVideoInfo() {
    }

    @Override
    public String toString() {
        return "SaveVideoInfo{" +
                "id=" + id +
                ", href='" + href + '\'' +
                ", img='" + img + '\'' +
                ", language='" + language + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
