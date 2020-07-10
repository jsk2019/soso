package com.whu.soso;


import com.whu.soso.Repository.DriverRepository;
import com.whu.soso.Repository.OrderListRepository;
import com.whu.soso.model.Driver;
import com.whu.soso.model.OrderList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
 public class SosoApplicationTests {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private OrderListRepository orderListRepository;
    @Test
    public void contextLoads() {
//        Driver test = new Driver();
//        test.setTellphone(12345678910L);
//        test.setStatus(0);
//        driverRepository.save(test);
//        Driver driver = driverRepository.findByTellphone(12345678910L);
//        OrderList orderList = new OrderList();
//        orderList.setStatus(0);
//        orderList.setDriver(driver);
//        orderListRepository.save(orderList);
//        Driver driver1= driverRepository.findByTellphone(12345678910L);
//        System.out.println(driver1.getStatus());
    }

}
