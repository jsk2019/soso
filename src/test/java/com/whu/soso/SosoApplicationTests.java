package com.whu.soso;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whu.soso.Repository.DriverRepository;
import com.whu.soso.Repository.OrderListRepository;
import com.whu.soso.Repository.UserRepository;
import com.whu.soso.model.Driver;
import com.whu.soso.model.OrderList;
import com.whu.soso.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SosoApplicationTests {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private OrderListRepository orderListRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {

     //  Driver test = new Driver();
     //  test.setTelephone("123456843");
     //  driverRepository.save(test);
      //Driver driver = driverRepository.findByTelephone("1234567");

     // OrderList orderList = new OrderList();
     // orderList.setId("awf");
      orderListRepository.updateDriverTelephone("123456843","awf");

     // orderListRepository.save(orderList);
      Driver driver1= driverRepository.findByTelephone("123456843");
      System.out.println(driver1.getStatus());
    }


}
