package com.whu.soso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebAppConfig implements WebMvcConfigurer {


    @Autowired
    private UserImageProperties userImageProperties;

    @Autowired
    private CarPicProperties carPicProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //映射路径
        registry.addResourceHandler("/userImage/**").addResourceLocations("file:"+
                userImageProperties.getLocalUrl());


        registry.addResourceHandler("/carImage/**").addResourceLocations("file:"+
                carPicProperties.getLocalUrl());

    }


}
