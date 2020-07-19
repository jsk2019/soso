package com.whu.soso;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whu.soso.Repository.DriverRepository;
import com.whu.soso.Repository.OrderListRepository;
import com.whu.soso.Repository.UserRepository;
import com.whu.soso.Service.APIService;
import com.whu.soso.Service.OrderListService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTests {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private OrderListRepository orderListRepository;
    @Autowired
    private UserRepository userRepository;

    OrderListService orderListService = new OrderListService();

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
     * 程序员：郭香俊
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




    /**
     * 单元测试
     * 时间：2020/7/18 19:30
     * 缺陷描述摘要：缺陷所在模块：OrderListController.java
     *            缺陷所在函数：CreateOrder
     *            缺陷说明：日期格式转化存入数据库问题
     * 单元测试用例编号：206
     * 程序员：郑建宙
     * 核准时间：20:00
     * 状态：完成
     */
    @Test
    public  void apitest2() throws ParseException {
        APIService apiService = new APIService();
        apiService.GetDistance("116.481028","39.989643","114.465302","40.004717");
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String  s = "2000-01-02 16:30:12";
        String s1 = "Tue Mar 26 10:26:08 CST 2019";

     //   System.out.println(orderListService.zoneToLocalTime(s1));
        Date date = df1.parse(s);
        System.out.println(s);

    }

    /**
     * 单元测试
     * 时间：2020/7/18 19:30
     * 缺陷描述摘要：缺陷所在模块：OrderListController.java
     *            缺陷所在函数：CreateOrder
     *            缺陷说明：从数据库读取日期格式转化问题
     * 单元测试用例编号：207
     * 程序员：郭香俊
     * 核准时间：20:00
     * 状态：完成
     */
    @Test
    public  void DataTest() throws ParseException {
        String str = "Wed Apr 22 14:35:58 CST 2020";
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date date = (Date) sdf.parse(str);
        String formatStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println(formatStr);
    }


}
