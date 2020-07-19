/**
 * <pre>
 *     author : 3D2Y.江圣坤
 *     e-mail : 530578697@qq.com
 *     date   : 2020/7//16
 *     description   : 用于用户操作的接口
 *     version: 2.0
 * </pre>
 */

package com.whu.soso.Controller;

import com.whu.soso.Repository.UserRepository;
import com.whu.soso.model.ReturnMessage;
import com.whu.soso.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    /**
     *
     * @param user 用户实体类
     * @return  1：注册成功
     *          2：注册失败，手机号已被注册
     */
    @PostMapping(value = "/registered")
    public ReturnMessage Registered(@RequestBody User user) {
        User user1 = userRepository.findByTelephone(user.getTelephone());
        if (user1 == null) {
            userRepository.save(user);
            return new ReturnMessage(1);
        } else {
            return new ReturnMessage(0);
        }
    }

    /**
     *
     * @param telephone 手机号
     * @param password  密码
     * @return  1：登陆成功
     *          2：用户名或密码错误
     */
    @PostMapping(value = "/login")
    public ReturnMessage LoginInPassword(@RequestParam String telephone, @RequestParam String password) {
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


    @PostMapping(value = "/upload")
    public Object upLoadFile(@RequestParam String s) {

        return s;
    }

}
