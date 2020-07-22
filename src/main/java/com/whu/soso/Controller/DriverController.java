/**
 * <pre>
 *     author : 3D2Y.江圣坤，郭香俊
 *     e-mail : 530578697@qq.com
 *     date   : 2020/7//16
 *     description   : 司机端接口
 *     version: 2.0
 * </pre>
 */

package com.whu.soso.Controller;

import com.alibaba.fastjson.JSONObject;
import com.whu.soso.Repository.DriverRepository;
import com.whu.soso.Service.FaceMatchService;
import com.whu.soso.config.UserImageProperties;
import com.whu.soso.model.Driver;
import com.whu.soso.model.ReturnMessage;
import com.whu.soso.model.User;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/driver")
public class DriverController {

   // E:\soso\src\main\java\com\whu\soso\Controller\DriverController.java

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    UserImageProperties userImageProperties;

    /**
     * 保存司机信息
     * @param driver
     * @return
     */
    @PostMapping(value = "/")
    public ReturnMessage RegisteredDirver(@RequestBody Driver driver) {
        Driver driver1 = driverRepository.findByTelephone(driver.getTelephone());
        if (driver1 == null) {
            driver.setStatus(2);
            driverRepository.save(driver);
            return new ReturnMessage(1);
        } else {
            return new ReturnMessage(0);
        }
    }

    @PostMapping(value = "/login")
    public ReturnMessage LoginInPassword(@RequestParam String telephone, @RequestParam String password) {
        try {
            Driver user = driverRepository.findByTelephone(telephone);
            if (password.equals(user.getPassword())) {
                return new ReturnMessage(1);
            }
            return new ReturnMessage(0);
        } catch (NullPointerException e) {
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
    @PostMapping("/position")
    public ReturnMessage UpdatePosition(@RequestParam String telephone,@RequestParam Double longitude,@RequestParam Double latitude){
        try {
            if (driverRepository.existsById(telephone)) {
                driverRepository.updateDriverPosition(longitude, latitude, telephone);
                return new ReturnMessage(1);
            }
            else return new ReturnMessage(0);

        }catch (Exception e){
            return new ReturnMessage(0);
        }
    }

    /**
     * 更新司机状态
     * @param telephone 电话号码
     * @param status    状态
     * @return
     */
    @PostMapping(value = "/updateStatus")
    public ReturnMessage UpdateDriverStatus(@RequestParam String telephone, @RequestParam Integer status){
        try {
            if (driverRepository.existsById(telephone)) {
                driverRepository.UpdateDriverStatus(status, telephone);
                return new ReturnMessage(1);
            }
            else return new ReturnMessage(0);
        }catch (Exception e){
            return new ReturnMessage(0);
        }
    }

    /**
     * 获取司机经纬度
     * @param telephone
     * @return
     */
    @GetMapping(value = "/driverPosition")
    public JSONObject GetDriverPosition(@RequestParam String telephone){
        Map<String,Object> params = new HashMap<>();
        if (driverRepository.existsById(telephone)){
            Driver driver = driverRepository.findByTelephone(telephone);
            params.put("longitude",driver.getLongitude());
            params.put("latitude",driver.getLatitude());
        }
        else{
            params.put("error","0");
        }
        return new JSONObject(params);
    }

    @PostMapping(value="/faceLogin")
    public Object getFaceDB(@RequestParam(value = "file") MultipartFile upload,@RequestParam("phone") String telephone ) throws Exception{
        //String filePath = new File("").getAbsolutePath();
        //System.out.println(filePath);
       // String imgPath1="E:\\soso\\src\\main\\resources\\timg.jpg";
        try {
            String imgPath2=userImageProperties.getLocalUrl()+telephone+".png";
            String result = FaceMatchService.match(upload, imgPath2);
            System.out.println(result);
            return result;
        }catch (NullPointerException e){
            return "null";
        }
    }


    @PostMapping(value = "/uploadPic")
    public Object upLoadFile(@RequestParam(value = "file") MultipartFile upload,@RequestParam("phone") String telephone) {
//        String filePath = "E:\\soso\\src\\main\\resources\\userImage\\";
        String filePath ="D:\\SoSoBack\\soso\\src\\main\\resources\\userImage\\";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String newFileName = telephone+".png";
        String newFilePath = filePath + newFileName;
        try {
            upload.transferTo(new File(newFilePath));
            return newFileName;
        } catch (IllegalStateException e) {
            return e;
        } catch (IOException e1) {
            return e1;
        }

    }


    @PostMapping(value = "/uploadPicToLocal" )
    @ResponseBody
    public String upLoadFileLoc(@RequestParam(value = "file") MultipartFile upload,@RequestParam("phone") String telephone){
        String localPath=userImageProperties.getLocalUrl()+upload.getOriginalFilename();
        System.out.println(localPath);
        File targetFile=new File(localPath);
        try {
            upload.transferTo(targetFile);
            String imageUrl=userImageProperties.getHttpUrl()+upload.getOriginalFilename();
            //存储用户脸部图像路径到数据库
            driverRepository.UpdateDriverPersonImage(imageUrl,telephone);
            return imageUrl;
        } catch (IOException e) {
           return "上传图片失败";
        }
    }
}
