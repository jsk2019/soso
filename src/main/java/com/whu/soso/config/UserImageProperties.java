package com.whu.soso.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


//注入从本地路径到HttpUrl映射的一个配置类
@Component
@ConfigurationProperties(prefix = "userfaceimage")
@PropertySource(value = {"classpath:configFiles/user_face_image_storage.properties"},encoding = "utf-8")
public class UserImageProperties {
    private String httpUrl;
    private String localUrl;

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }
}



