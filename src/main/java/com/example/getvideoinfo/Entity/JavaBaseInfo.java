package com.example.getvideoinfo.Entity;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.persistence.*;

@Entity
@Data
public class JavaBaseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String title;
    private String content;

    public JavaBaseInfo() {
    }
}
