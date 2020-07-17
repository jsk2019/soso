/**
 * <pre>
 *     author : 3D2Y.江圣坤
 *     e-mail : 530578697@qq.com
 *     date   : 2020/7//11
 *     description   : 用于用户的登录和注册
 *     version: 1.0
 * </pre>
 */

package com.whu.soso.Controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONObjectCodec;
import com.whu.soso.Repository.UserRepository;
import com.whu.soso.model.ReturnMessage;
import com.whu.soso.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/registered" )
    @ResponseBody

    public JSONObject Registered(@RequestBody User user) {
        //用一个map保存responseBody 将来转成json
        Map<String,Object> params=new HashMap<>();
        //如果用户不存在
        if (!userRepository.existsById(user.getTelephone())) {
            userRepository.save(user);
            //Status：1 注册成功  0注册失败
            params.put("status",1);
        } else {
           params.put("status",0);
        }
        return new JSONObject(params);
    }


    @PostMapping(value = "/login")
    public ReturnMessage Login(@RequestParam String telephone, @RequestParam String password) {
        try {
            User user = userRepository.findByTelephone(telephone);
            if (password.equals(user.getPassword())) {
                return new ReturnMessage(1);
            }
            return new ReturnMessage(0);
        } catch (NullPointerException e) {
            return new ReturnMessage(0);
        }
    }


}
