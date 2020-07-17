package com.whu.soso;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whu.soso.model.Driver;
import com.whu.soso.model.OrderList;
import com.whu.soso.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    String BASIC_ADDRESS = "https://restapi.amap.com/v3/";
    String KEY = "785ec1fda85a730156c10f27e565f9c0";

    @GetMapping("/")
    public Object hello(){
        return new OrderList();
    }

    @PostMapping(value="/Test",produces = "application/json;charset=UTF-8")
    public User Registered(@RequestParam(required = false) String userString) {
        JSONObject jsonObject = JSONObject.parseObject(userString);
        User user = JSONObject.toJavaObject(jsonObject, User.class);
        System.out.println(user.toString());
        return user;
    }



}
