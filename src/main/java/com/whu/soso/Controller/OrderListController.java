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
import com.whu.soso.model.*;
import net.bytebuddy.implementation.bytecode.Throw;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/match")
    public MyResult CreateOrder(@RequestBody JSONObject jsonObject) throws ParseException {
        String orderId="N"+ new Date().getTime();
        jsonObject.put("id",orderId);
        OrderList orderList=jsonObject.toJavaObject(OrderList.class);

        //如果不是预约单
        if(jsonObject.getInteger("order_type")==0) {
            try {
                String id = jsonObject.getString("id");
                String userLon = jsonObject.getString("origin_longitude");
                String userLat = jsonObject.getString("origin_latitude");
                String city = jsonObject.getString("city");
                List<Driver> drivers = driverRepository.findAllByCityAndStatus(city, 1);
                if (drivers.size() > 0) {
                    String tele = orderListService.MatchingDriver(drivers, userLon, userLat);
                    Driver driver = driverRepository.findByTelephone(tele);
                    orderListRepository.updateDriverTelephone(tele, id);
                    return new MyResult().status(200).msg("匹配成功").result(driver);
                } else return new MyResult().status(600).msg("未成功匹配到司机");
            } catch (Exception e) {
                return new MyResult().msg("匹配异常").status(601);
            }
        }
        return null;

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
