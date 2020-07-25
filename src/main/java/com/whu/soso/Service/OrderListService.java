/**
 * <pre>
 *     author : 3D2Y.江圣坤、郭香俊
 *     e-mail : 530578697@qq.com
 *     date   : 2020/7//17
 *     description   : 订单操作服务层
 *     version: 1.0
 * </pre>
 */
package com.whu.soso.Service;


import com.whu.soso.Repository.DriverRepository;
import com.whu.soso.Repository.OrderListRepository;
import com.whu.soso.model.Driver;
import com.whu.soso.model.OrderList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderListService {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    OrderListRepository orderListRepository;
    /**
     * 订单自动匹配司机
     * @param drivers
     * @param userLon
     * @param userLat
     * @return 匹配到的司机的电话号码
     */
    public String MatchingDriver(List<Driver> drivers,String userLon,String userLat)throws Exception{
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
        //System.out.println(telephone);
        return telephone;
    }

    public Date zoneToLocalTime(String dateString) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateString = dateString.replace("Z", " UTC");
        System.out.println(dateString);
        Date date = simpleDateFormat.parse(dateString);
        String d = df.format(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date pictureCapturedTime = sdf.parse(d);
        return pictureCapturedTime;
    }

    public List<OrderList> ListOrderList(){return orderListRepository.findByOrder_type(1);}

    public String DateToString(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date date = (Date) sdf.parse(str);
        String formatStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return formatStr;
    }
}
