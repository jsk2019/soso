package com.whu.soso.Controller;

import com.whu.soso.Repository.DriverRepository;
import com.whu.soso.model.Driver;
import com.whu.soso.model.ReturnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/driver")
public class DriverController {
    @Autowired
    DriverRepository driverRepository;

    /**
     * 保存司机信息
     * @param driver
     * @return
     */
    @PostMapping(value = "/")
    public ReturnMessage Registered(@RequestBody Driver driver) {
        Driver driver1 = driverRepository.findByTelephone(driver.getTelephone());
        if (driver1 == null) {
            driverRepository.save(driver);
            return new ReturnMessage(1);
        } else {
            return new ReturnMessage(0);
        }
    }

    /**
     * 更新司机经纬度
     * @param telephone
     * @param longitude
     * @param latitude
     * @return
     */
    @GetMapping("/position")
    public ReturnMessage UpdatePosition(@RequestParam String telephone,@RequestParam Double longitude,@RequestParam Double latitude){
        driverRepository.updateDriverPosition(longitude,latitude,telephone);
        return null;
    }
}
