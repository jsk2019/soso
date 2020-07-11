package com.whu.soso.Repository;

import com.whu.soso.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByTelephone(String telephone);
    User findAllByTelephone(String telephone);
}
