package com.whu.soso;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whu.soso.Repository.DriverRepository;
import com.whu.soso.Repository.OrderListRepository;
import com.whu.soso.Repository.UserRepository;
import com.whu.soso.Service.APIService;
import com.whu.soso.model.Driver;
import com.whu.soso.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTests {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private OrderListRepository orderListRepository;
    @Autowired
    private UserRepository userRepository;


    /**
     * 单元测试
     * 时间：2020/7/11 23:10
     * 缺陷描述摘要：缺陷所在模块：UserRepository.java
     *            缺陷所在函数：findByTelephone
     *            缺陷说明：测试user的jpa操作是否可用
     * 单元测试用例编号：101
     * 程序员：江圣坤
     * 核准时间：16:00
     * 状态：完成
     */
    @Test
    @Transactional
    @Rollback(true)
    public void UserSavetest() {
        User user = new User();
        user.setTelephone("123");
        user.setPassword("123456");
        userRepository.save(user);
        User user1 = userRepository.findByTelephone("123");
        Assert.assertEquals("123456",user1.getPassword());
    }

    /**
     * 单元测试
     * 时间：2020/7/11 23:10
     * 缺陷描述摘要：缺陷所在模块：DriverRepository.java
     *            缺陷所在函数：findByTelephone
     *            缺陷说明：测试driver的jpa操作是否可用
     * 单元测试用例编号：102
     * 程序员：江圣坤
     * 核准时间：16:00
     * 状态：完成
     */
    @Test
    @Transactional
    @Rollback(true)
    public void DriverSavetest() {
        Driver driver = new Driver();
        driver.setTelephone("123");
        driver.setPassword("123456");
        driverRepository.save(driver);
        Driver driver1 = driverRepository.findByTelephone("123");
        Assert.assertEquals("123456",driver1);
    }


    @Test
    public void jsontest(){
        String myJsonObj2 = "{\n" +
                "    \"name\":\"网站\",\n" +
                "    \"num\":3,\n" +
                "    \"sites\": [\n" +
                "        { \"name\":\"Google\", \"info\":[ \"Android\", \"Google 搜索\", \"Google 翻译\" ] },\n" +
                "        { \"name\":\"Runoob\", \"info\":[ \"菜鸟教程\", \"菜鸟工具\", \"菜鸟微信\" ] },\n" +
                "        { \"name\":\"Taobao\", \"info\":[ \"淘宝\", \"网购\" ] }\n" +
                "    ]\n" +
                "}";
        JSONObject jsonobj2 = JSON.parseObject(myJsonObj2); //将json字符串转换成jsonObject对象
        JSONArray jsonArray = jsonobj2.getJSONArray("sites");
        System.out.println(jsonArray.getJSONObject(1));
    }


    @Test
    public  void apitest2(){
        APIService apiService = new APIService();
        apiService.GetDistance("116.481028","39.989643","114.465302","40.004717");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
    }
}
