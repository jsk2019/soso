package com.whu.soso;


import com.alibaba.fastjson.JSONObject;
import com.whu.soso.Repository.DriverRepository;
import com.whu.soso.Repository.OrderListRepository;
import com.whu.soso.Repository.UserRepository;
import com.whu.soso.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTests {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private OrderListRepository orderListRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * 时间：2020/7/11 23:10
     * 缺陷描述摘要：缺陷所在模块：LoginController.java
     *            缺陷所在函数：LoginInPassword
     *            缺陷说明：无法成功根据用户手机号返回user对象
     * 单元测试用例编号：101
     * 程序员：江圣坤
     * 核准时间：23:30
     * 状态：完成
     */
    @Test
    @Transactional
    @Rollback(true)
    public void LoginTest(){
        User user = new User();
        user.setTelephone("123");
        user.setPassword("jsk");
        userRepository.save(user);
        User user1 = userRepository.findByTelephone("123");
        Assert.assertEquals(user.getPassword(),user1.getPassword());
    }

    /**
     * 时间：2020/7/11 23:30
     * 缺陷描述摘要：缺陷所在模块：LoginController.java
     *            缺陷所在函数：Registered
     *            缺陷说明：无法将解析json数据格式并转化为User对象
     * 单元测试用例编号：102
     * 程序员：江圣坤
     * 核准时间：23:50
     * 状态：完成
     */
    @Test
    @Transactional
    @Rollback(true)
    public void RegisteredTest(){
        String userString = "{\n" +
                "  \"telephone\": \"123456789\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"nickname\": null,\n" +
                "  \"avatar\": null,\n" +
                "  \"one_coupon\": null,\n" +
                "  \"two_coupon\": null,\n" +
                "  \"three_coupon\": null,\n" +
                "  \"longitude\": null,\n" +
                "  \"latitude\": null,\n" +
                "  \"city\": null\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(userString);
        User user = JSONObject.toJavaObject(jsonObject,User.class);
        user.setTelephone("123");
        userRepository.save(user);
        User user1 = userRepository.findByTelephone("123");
        Assert.assertNotNull(user1);

    }
}
