package com.whu.soso.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

@Service
public class APIService {
    private String KEY = "785ec1fda85a730156c10f27e565f9c0";

    /**
     * 调用测距接口
     * @param driverLon 司机经度
     * @param driverLat 司机纬度
     * @param userLon   用户经度
     * @param userLat 用户纬度
     * @return 距离（米）
     */
    public Long GetDistance(String driverLon,String driverLat,String userLon,String userLat){
        //用JAVA发起http请求，并返回json格式的结果
        String serverUrl = "https://restapi.amap.com/v3/distance?origins="+driverLon+","+driverLat+"&destination="+userLon+","+userLat+"&key="+KEY;
       // System.out.println(serverUrl);
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
        System.out.println(distanceString);
        Long distance = Long.parseLong(distanceString);
        return distance;
    }


    /**
     * 根据value的值获取key的值
     * @param map
     * @param value
     * @return key的值
     */
    public static String getDistanceKey(Map<String,Long> map, Long value){
        String key="";
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            if(value.equals(entry.getValue())){
                key=entry.getKey();
            }
        }
        return key;
    }

}
