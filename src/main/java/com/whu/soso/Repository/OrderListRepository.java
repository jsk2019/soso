package com.whu.soso.Repository;

import com.whu.soso.model.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderListRepository extends JpaRepository<OrderList, String> {

    /**
     * 修改订单司机手机号码
     * @param phone 更新订单司机手机号
     * @param id  订单ID
     */
    @Modifying
    @Transactional
    @Query(value = "update order_list set driver_telephone=?1 where id=?2",nativeQuery = true)
    int updateDriverTelephone(String phone,String id);


}
