package com.example.shoeshop.MvcConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${config.upload}")
    private String directory;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("images/**")
                .addResourceLocations("file:/" + directory);
    }

    public String uploadImage(MultipartFile file){
        String fileName = null;
        try{
            fileName = file.getOriginalFilename().replace(" ","-");
            Path path = Paths.get(directory + fileName);
            Files.write(path,file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return fileName;
    }
}
