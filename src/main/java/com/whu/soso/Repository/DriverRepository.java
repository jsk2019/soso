package com.whu.soso.Repository;

import com.whu.soso.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, String> {
    Driver findByTelephone(String telephone);
}
