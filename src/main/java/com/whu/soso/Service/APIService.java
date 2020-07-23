/**
 * <pre>
 *     author : 3D2Y.郭香俊
 *     e-mail : 530578697@qq.com
 *     date   : 2020/7//15
 *     description   : 第三方接口调用
 *     version: 2.0
 * </pre>
 */
package com.whu.soso.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.HttpURLConnection;
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

    public String GetLicense(String image1,String key){
        //用JAVA发起http请求，并返回json格式的结果
        String serverUrl = "http://apis.juhe.cn/drivingLicenseOcr/index?image="+image1+"&key="+key;
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

        return resultstring;
    }
    public static String ImageToBase64ByOnline(String imgURL) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(imgURL);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 关闭流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.toByteArray());
    }

    public static String ImageToBase64ByLocal(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);

            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

}
