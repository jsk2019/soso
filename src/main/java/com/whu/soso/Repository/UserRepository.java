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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User,String> {
    User findByTelephone(String telephone);

    @Modifying
    @Transactional
    @Query(value = "update user set telephone=?1 where telephone=?2",nativeQuery = true)
    void UpdateUserTel(String newTelephone,String telephone);

    @Modifying
    @Transactional
    @Query(value = "update user set nickname=?1 where telephone=?2",nativeQuery = true)
    void UpdateUserName(String nickname,String telephone);

    @Modifying
    @Transactional
    @Query(value = "update user set password=?1 where telephone=?2",nativeQuery = true)
    void UpdateUserPassword(String password,String telephone);
}
