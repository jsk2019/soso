/**
 * <pre>
 *     author : 3D2Y.郑建宙、江圣坤
 *     e-mail : 530578697@qq.com
 *     date   : 2020/7/09
 *     description   : 司机服务层
 *     version: 1.0
 * </pre>
 */

package com.whu.soso.Repository;

import com.whu.soso.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, String> {
    Driver findByTelephone(String telephone);
}
