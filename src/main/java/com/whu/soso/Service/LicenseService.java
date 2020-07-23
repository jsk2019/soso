package com.whu.soso.Service;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whu.soso.Util.Base64Util;
import com.whu.soso.Util.FileUtil;
import com.whu.soso.Util.HttpUtil;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 驾驶证识别
 */
public class LicenseService {


    public static String drivingLicense(MultipartFile file) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/driving_license";
        try {
            // 本地文件路径

            byte[] imgData = file.getBytes();
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = LicenseAuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static JSONObject getInfo(String result){
        Map<String,Object> params = new HashMap<>();
        JSONObject jsonObject = JSON.parseObject(result,JSONObject.class);
        String words_result = jsonObject.getString("words_result");
        JSONObject jsonObject1 = JSON.parseObject(words_result,JSONObject.class);
        String nameStr = jsonObject1.getString("姓名");
        JSONObject jsonObject2 = JSON.parseObject(nameStr,JSONObject.class);
        String name = jsonObject2.getString("words");
        String dateStr = jsonObject1.getString("至");
        JSONObject jsonObject3 = JSON.parseObject(dateStr,JSONObject.class);
        String date = jsonObject3.getString("words");
        params.put("name",name);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        String newDate = df.format(new Date());// new Date()为获取当前系统时间
        Double date1 = Double.valueOf(date);
        Double date2 = Double.valueOf(newDate);
        System.out.println(date1+"  "+date2);
        if(date1>date2){
            params.put("result","success");
        }
        else params.put("result","fail");
        return new JSONObject(params);
    }
}