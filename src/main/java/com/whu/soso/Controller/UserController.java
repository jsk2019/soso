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

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONObjectCodec;
import com.whu.soso.Repository.UserRepository;
import com.whu.soso.model.Driver;
import com.whu.soso.model.ReturnMessage;
import com.whu.soso.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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

    @PostMapping(value = "/password")
    public Object UpdatePassword(@RequestParam String telephone,@RequestParam String oldPassword,@RequestParam String newPassword){
        try {
            User user = userRepository.findByTelephone(telephone);
            if (user.getPassword()==oldPassword){
                userRepository.UpdateUserPassword(newPassword,telephone);
            }
        }catch (NullPointerException e)
        {
            return "null";
        }
        return "success";
    }

    @PostMapping(value = "/telephone")
    public Object UpdateTelephone(@RequestParam String oldTelephone,String newTelephone){
        try {
            User user = userRepository.findByTelephone(oldTelephone);
            userRepository.UpdateUserTel(newTelephone,oldTelephone);
        }catch (NullPointerException e)
        {
            return "null";
        }
        return "success";
    }

    @PostMapping(value = "/nickname")
    public Object UpdateNickName(@RequestParam String telephone,@RequestParam String nickname){
        try {
            User user = userRepository.findByTelephone(telephone);
            userRepository.UpdateUserName(nickname,telephone);
        }catch (NullPointerException e)
        {
            return "null";
        }
        return "success";
    }

}
