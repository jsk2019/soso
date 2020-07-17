package com.whu.soso.Controller;


import com.alibaba.fastjson.JSONObject;
import com.whu.soso.Repository.DriverRepository;
import com.whu.soso.Repository.OrderListRepository;
import com.whu.soso.Repository.UserRepository;
import com.whu.soso.Service.APIService;
import com.whu.soso.Service.OrderListService;
import com.whu.soso.model.Driver;
import com.whu.soso.model.OrderList;
import com.whu.soso.model.ReturnMessage;
import com.whu.soso.model.User;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import javax.xml.crypto.Data;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

@RestController
@RequestMapping(value = "/order")
public class OrderListController {
    @Autowired
    OrderListRepository orderListRepository ;
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    UserRepository userRepository;
    /**
     *创建订单
     * @param orderList
     * @return
     */
    @PostMapping(value = "/create")
    public OrderList CreareOrder(@RequestBody JSONObject orderList) {
        //orderListRepository.save(orderList);
       // String driverLon,String driverLat,String userLon,String userLat;
        String userLon = orderList.getString("origin_longitude");
        String userLat = orderList.getString("origin_latitude");
        String city = orderList.getString("city");
        String userTel = orderList.getString("telephone");
        String origin_address = orderList.getString("origin_address");
        String createTime = orderList.getString("createTime");
        Integer order_type = Integer.valueOf(orderList.getString("order_type"));
        User user = userRepository.findByTelephone(userTel);
//        User user = orderList.getUser();
//        String city = user.getCity();
//        User user = new User();
//        user.setCity("bei");
//        String city = user.getCity();
        List<Driver> drivers = driverRepository.findAllByCityAndStatus(city,0);
        OrderListService orderListService = new OrderListService();
        String tele =  orderListService.MatchingDriver(drivers,userLon,userLat);
        Driver driver =  driverRepository.findByTelephone(tele);
        System.out.println(drivers.toString());
        OrderList orderList1 = new OrderList();
        orderList1.setDriver(driver);
        orderList1.setUser(user);
        orderList1.setOrigin_address(origin_address);
        orderList1.setOrder_type(order_type);
        //orderList1.setCreateTime(createTime);
        return orderList1;
    }

    /**
     * 删除订单
     * @param telephone
     * @return
     */
    @DeleteMapping(value = "/delete")
    public ReturnMessage DeleteOrder(@RequestParam String telephone){
        try {
            OrderList orderList = orderListRepository.findById(telephone).orElse(null);
            if (orderList==null){return new ReturnMessage(0);}
            orderListRepository.deleteById(telephone);
        }catch (NullPointerException e){
            return new ReturnMessage(0);
        }
        return new ReturnMessage(1);
    }

}
