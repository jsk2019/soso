package com.whu.soso;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class APITest {
    /**
     * 单元测试
     * 时间：2020/7/18 20:10
     * 缺陷描述摘要：缺陷所在模块：APIService.java
     *            缺陷所在函数：GetDistance
     *            缺陷说明：调用第三方接口失败
     * 单元测试用例编号：208
     * 程序员：江圣坤
     * 核准时间：20:30
     * 状态：完成
     */
    @Test
    public void GetDistance(){
        //用JAVA发起http请求，并返回json格式的结果
        String serverUrl = "https://restapi.amap.com/v3/distance?origins=116.463621,38.875422&destination=116.463621,39.875422&key=785ec1fda85a730156c10f27e565f9c0";
        System.out.println(serverUrl);
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line;
            while((line = in.readLine()) != null){
                result.append(line);
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String resultstring  = result.toString();
        JSONObject jsonObject = JSON.parseObject(resultstring,JSONObject.class);
        JSONArray results = jsonObject.getJSONArray("results");
        JSONObject jsonObject1 = results.getJSONObject(0);
        String distanceString = jsonObject1.getString("distance");
        //  System.out.println(distanceString);
        Long distance = Long.parseLong(distanceString);
        System.out.println(distance);
    }



}
