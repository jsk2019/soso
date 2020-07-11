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

import com.whu.soso.Repository.UserRepository;
import com.whu.soso.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    UserRepository userRepository;
    @PostMapping(value="/registered")
    public Integer Registered(@RequestBody User user){
//        JSONObject jsonObject = JSONObject.parseObject(userString);
//        User user = JSONObject.toJavaObject(jsonObject,User.class);
//        System.out.println(user.toString());
        User user1 = userRepository.findByTelephone(user.getTelephone());
        if (user1==null) {
            userRepository.save(user);
            return 1;
        }
        else {
            return 0;
        }
    }


    @PostMapping(value = "/login")
    public Integer Login(@RequestParam String tellphone,@RequestParam String password){
        User user = userRepository.findByTelephone(tellphone);
        if (password.equals(user.getPassword())){
            return 1;
        }
        else return 0;
    }
}
