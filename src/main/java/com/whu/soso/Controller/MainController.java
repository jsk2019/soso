/**
 * <pre>
 *     author : 3D2Y.江圣坤，郭香俊
 *     e-mail : 530578697@qq.com
 *     date   : 2020/7//16
 *     description   : 订单进行中的接口
 *     version: 2.0
 * </pre>
 */

package com.whu.soso.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whu.soso.Repository.DriverRepository;
import com.whu.soso.Repository.OrderListRepository;
import com.whu.soso.Repository.UserRepository;
import com.whu.soso.Service.APIService;
import com.whu.soso.model.OrderList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/main")
public class MainController {
    @Autowired
    OrderListRepository orderListRepository ;
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    UserRepository userRepository;

    APIService apiService = new APIService();
    /**
     * 距离计算
     * @param Lon1 地点1的经度
     * @param Lat1 地点1的纬度
     * @param Lon2 地点2的经度
     * @param Lat2 地点2的纬度
     * @return
     */
   @GetMapping(value = "/getDistance")
   public JSONObject GetDistance(@RequestParam String Lon1, @RequestParam String Lat1,@RequestParam  String Lon2,@RequestParam  String Lat2){
       Map<String,Object> params = new HashMap<>();
       APIService apiService = new APIService();
       Long distance = apiService.GetDistance(Lon1,Lat1,Lon2,Lat2);
       params.put("distance", distance);
       if (distance<=1000) {
           params.put("result",1);
       }
       else params.put("result",0);
       return new JSONObject(params);
   }

    /**
     * 结束订单，判断和预计下车地点距离是否小于1000
     * @param id
     * @param longitude
     * @param latitude
     * @return
     */
    @PostMapping(value = "/finishOrder")
    public JSONObject FinishOrder(@RequestParam String id,@RequestParam Double longitude ,Double latitude){
        Map<String,Object> params = new HashMap<>();
        if (orderListRepository.existsById(id)){
            OrderList orderList = orderListRepository.findOrderListById(id);
            String lon =String.valueOf(orderList.getOrigin_longitude());
            String lat =String.valueOf(orderList.getOrigin_latitude());
            String lon1 = String.valueOf(longitude);
            String lat1 = String.valueOf(latitude);
            Long distance = apiService.GetDistance(lon1,lat1,lon,lat);
            String lon2 =String.valueOf(orderList.getDes_longitude());
            String lat2 =String.valueOf(orderList.getDes_latitude());
            Long distance2 = apiService.GetDistance(lon1,lat1,lon2,lat2);
            if (distance2 <= 1000) {
                int money = Integer.valueOf((int) (distance * 0.0001));
                params.put("distance", distance);
                params.put("money", money);
                return new JSONObject(params);
            }
            else {
                int money = Integer.valueOf((int) (distance * 0.0001));
                params.put("distance", distance);
                params.put("warn",distance2);
                params.put("money", money);
                return new JSONObject(params);
            }
        }
        else {
            params.put("error","0");
            return new JSONObject(params);
        }
    }
}
