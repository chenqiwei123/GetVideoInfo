package com.example.getvideoinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.getvideoinfo.Entity"})
public class GetVideoInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetVideoInfoApplication.class, args);
    }

}
