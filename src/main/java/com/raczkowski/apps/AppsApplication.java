package com.raczkowski.apps;

import com.raczkowski.apps.service.ArticleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AppsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppsApplication.class, args);

    }
}
