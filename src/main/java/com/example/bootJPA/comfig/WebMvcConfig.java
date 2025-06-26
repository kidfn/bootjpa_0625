package com.example.bootJPA.comfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig {

    String uploadPath = "D:\\web_0226_pjs\\_myProject\\_java\\_fileUpload\\";

    @Override
    public void  addResourceHandler(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/upload/**")
                .addResourceLocations(uploadPath);
    }

}
