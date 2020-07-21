package com.whu.soso.Service;


import com.whu.soso.Util.Base64Util;
import com.whu.soso.Util.FileUtil;
import com.whu.soso.Util.GsonUtils;
import com.whu.soso.Util.HttpUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaceMatchService{

    public static String match(MultipartFile file, String imgPath2) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            // 【本地文件1地址】
            //byte[] bytes1 = FileUtil.readFileByBytes(imgPath1);
            byte[] bytes1 = file.getBytes();
            // 【本地文件2地址】
            byte[] bytes2 = FileUtil.readFileByBytes(imgPath2);
            String image1 = Base64Util.encode(bytes1);
            String image2 = Base64Util.encode(bytes2);

            List<Map<String, Object>> images = new ArrayList<>();

            Map<String, Object> map1 = new HashMap<>();
            //请求参数详情，看百度人脸对比开发文档
            //https://ai.baidu.com/ai-doc/FACE/Lk37c1tpf
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "NORMAL");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "LOW");
            map2.put("liveness_control", "NORMAL");

            images.add(map1);
            images.add(map2);

            String param = GsonUtils.toJson(images);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            // 【调用鉴权接口获取的token】
            String accessToken = AuthService.getAuth();
            //String accessToken ="25.8f1a0bcb20be223187c02b478b34bc9a.315360000.1910576773.282335-21178303";
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
