/**
 * <pre>
 *     author : 3D2Y.郑建宙、江圣坤
 *     e-mail : 530578697@qq.com
 *     date   : 2020/7/09
 *     description   : 用户服务层
 *     version: 2.0
 * </pre>
 */

package com.whu.soso.Repository;

import com.whu.soso.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByTelephone(String telephone);
}
