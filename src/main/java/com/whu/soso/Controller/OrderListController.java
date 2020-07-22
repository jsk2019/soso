/**
 * <pre>
 *     author : 3D2Y.郭香俊、江圣坤
 *     e-mail : 530578697@qq.com
 *     date   : 2020/7//16
 *     description   : 用于用户操作的接口
 *     version: 2.0
 * </pre>
 */
package com.whu.soso.Controller;


import com.alibaba.fastjson.JSON;
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
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.sound.midi.Soundbank;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    OrderListService orderListService = new OrderListService();
    APIService apiService = new APIService();
    /**
     *创建订单，匹配司机
     * @param orderList
     * @return
     */
    @PostMapping(value = "/create")
    public String CreareOrder(@RequestBody JSONObject orderList) throws ParseException {
        Map<String,Object> params = new HashMap<>();
        String userLon = orderList.getString("origin_longitude");
        String userLat = orderList.getString("origin_latitude");
        String city = orderList.getString("city");
        Double destination_longitude = Double.parseDouble(orderList.getString("destination_longitude"));
        Double destination_latitude = Double.parseDouble(orderList.getString("destination_latitude"));
        String destination_address = orderList.getString("destination_address");
        String userTel = orderList.getString("telephone");
        String origin_address = orderList.getString("origin_address");
        String createTime = orderList.getString("createTime");
        String appointment = orderList.getString("appointment");
        Integer order_type = Integer.valueOf(orderList.getString("order_type"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date date = formatter.parse(createTime);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String id = df.format(new Date());// new Date()为获取当前系统时间
        Date date1 = formatter.parse(appointment);
        User user = userRepository.findByTelephone(userTel);
        List<Driver> drivers = driverRepository.findAllByCityAndStatus(city,1);
        OrderListService orderListService = new OrderListService();
        Double userLon1 = Double.parseDouble(userLon);
        Double userLat1 = Double.parseDouble(userLat);
        OrderList orderList1 = new OrderList();
        orderList1.setOrigin_longitude(userLon1);
        orderList1.setOrigin_latitude(userLat1);
        orderList1.setUser(user);
        orderList1.setOrigin_address(origin_address);
        orderList1.setOrder_type(order_type);
        orderList1.setDestination_address(destination_address);
        orderList1.setDes_latitude(destination_latitude);
        orderList1.setDes_longitude(destination_longitude);
        orderList1.setOrigin_time(date);
        orderList1.setCreateTime(date);
        orderList1.setAppointment(date1);
        orderList1.setId(id);
        orderListRepository.save(orderList1);
        String tele =  orderListService.MatchingDriver(drivers,userLon,userLat);
        Driver driver =  driverRepository.findByTelephone(tele);
        return orderList1.getId();
    }

    /**
     * 匹配司机
     * @param orderList
     * @return
     * @throws ParseException
     */
    @PostMapping(value = "/match")
    public Driver MatchOrder(@RequestBody JSONObject orderList) throws ParseException {
        String id = orderList.getString("id");
        String userLon = orderList.getString("origin_longitude");
        String userLat = orderList.getString("origin_latitude");
        String city = orderList.getString("city");
        List<Driver> drivers = driverRepository.findAllByCityAndStatus(city,1);
        String tele =  orderListService.MatchingDriver(drivers,userLon,userLat);
        Driver driver =  driverRepository.findByTelephone(tele);
        orderListRepository.updateDriverTelephone(tele,id);
        return driver;
    }


    /**
     * 删除订单
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete")
    public ReturnMessage DeleteOrder(@RequestParam String id){
        try {
            OrderList orderList = orderListRepository.findById(id).orElse(null);
            if (orderList==null){return new ReturnMessage(0);}
            orderListRepository.deleteById(id);
        }catch (NullPointerException e){
            return new ReturnMessage(0);
        }
        return new ReturnMessage(1);
    }

    /**
     * 获取所有订单
     * @return
     * @throws ParseException
     */
    @GetMapping(value = "/listOrder")
    public Object ListOrder() throws ParseException {
        Map<String,Object> params = new HashMap<>();
        List<OrderList> orderLists = orderListRepository.findByOrder_type(1);
        for (int i=0;i<orderLists.size();i++){
            Map<String,Object> params1 = new HashMap<>();
            OrderList orderList = orderLists.get(i);
            String id = orderList.getId();
            String origin_time = orderListService.DateToString(orderList.getOrigin_time().toString());
        }
        return orderLists;
    }

    /**
     * 查看订单信息
     * @param id
     * @return
     */
    @GetMapping(value = "/getInfo")
    public Object getInfo(@RequestParam String id){
        if (orderListRepository.existsById(id)){
            OrderList orderList = orderListRepository.findOrderListById(id);
            return orderList;
        }
        else return new ReturnMessage(0);
    }


}
