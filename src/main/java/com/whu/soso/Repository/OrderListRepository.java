/**
 * <pre>
 *     author : 3D2Y.郑建宙
 *     e-mail : 530578697@qq.com
 *     date   : 2020/7/16
 *     description   : 订单服务层
 *     version: 2.0
 * </pre>
 */

package com.whu.soso.Repository;

import com.whu.soso.model.OrderList;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface OrderListRepository extends JpaRepository<OrderList, String> {

    /**
     * 修改订单司机手机号码
     * @param phone 更新订单司机手机号
     * @param id  订单ID
     */
    @Modifying
    @Transactional
    @Query(value = "update order_list set driver_telephone=?1 where id=?2",nativeQuery = true)
    void updateDriverTelephone(String phone,String id);

    OrderList findOrderListById(String id);

    @Query(value = "select * from order_list where order_type = ?1" ,nativeQuery = true)
    List<OrderList> findByOrder_type(Integer order_type);

}
