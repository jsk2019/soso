/**
 * <pre>
 *     author : 3D2Y.郑建宙、江圣坤
 *     e-mail : 530578697@qq.com
 *     date   : 2020/7/19
 *     description   : 司机服务层
 *     version: 2.0
 * </pre>
 */

package com.whu.soso.Repository;

import com.whu.soso.model.Driver;
import jdk.net.SocketFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, String> {
    Driver findByTelephone(String telephone);


    /**
     * 更新司机经纬度
     * @param longitude
     * @param latitude
     * @param telephone
     */
    @Modifying
    @Transactional
    @Query(value = "update driver set longitude=?1,latitude=?2 where telephone=?3",nativeQuery = true)
    void updateDriverPosition(Double longitude,Double latitude,String telephone);

    List<Driver> findAllByCityAndStatus(String city,Integer status);


    @Modifying
    @Transactional
    @Query(value = "update driver set status=?1 where telephone=?2",nativeQuery = true)
    void UpdateDriverStatus(Integer status,String telephone);

    @Modifying
    @Transactional
    @Query(value = "update driver set person_pic=?1 where telephone=?2",nativeQuery = true)
    int UpdateDriverPersonImage(String path,String telephone);

    @Modifying
    @Transactional
    @Query(value = "update driver set car_pic=?1 where telephone=?2",nativeQuery = true)
    int UpdateDriverCarImage(String path,String telephone);

    @Modifying
    @Transactional
    @Query(value = "update driver set name=?1 where telephone=?2",nativeQuery = true)
    void UpdateDrivername(String name,String telephone);

    Driver existsByTelephone(String telephone);

    @Modifying
    @Transactional
    @Query(value = "update driver set telephone=?1 where telephone=?2",nativeQuery = true)
    void UpdateDriverTel(String newTelephone,String telephone);

    @Modifying
    @Transactional
    @Query(value = "update driver set password=?1 where telephone=?2",nativeQuery = true)
    void UpdateDriverPassword(String password,String telephone);

    @Transactional
    void deleteByTelephone(String telephone);


}
