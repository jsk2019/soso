package com.whu.soso.Service;


import com.whu.soso.Repository.DriverRepository;
import com.whu.soso.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderListService {

    @Autowired
    DriverRepository driverRepository;

    /**
     * 订单自动匹配司机
     * @param drivers
     * @param userLon
     * @param userLat
     * @return 匹配到的司机的电话号码
     */
    public String MatchingDriver(List<Driver> drivers,String userLon,String userLat){
        Map<String,Long> distanceMap = new HashMap<>();
        APIService apiService = new APIService();
        for (int i=0;i<drivers.size();i++){
            Driver driver = drivers.get(i);
            String driverLon = String.valueOf(driver.getLongitude());
            String driverLat = String.valueOf(driver.getLatitude());
            Long distance = apiService.GetDistance(driverLon,driverLat,userLon,userLat);
            distanceMap.put(driver.getTelephone(),distance);
        }
        Comparator<Map.Entry<String, Long>> valueComparator = new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };
        List<Map.Entry<String, Long>> list = new ArrayList<Map.Entry<String, Long>>(distanceMap.entrySet());
        // 排序
        Collections.sort(list, valueComparator);
        String telephone = list.get(0).getKey();
        System.out.println(telephone);
        return telephone;
    }
}
