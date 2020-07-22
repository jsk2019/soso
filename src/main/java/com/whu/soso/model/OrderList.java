/**
 * <pre>
 *     author : 3D2Y.江圣坤、郑建宙
 *     e-mail : 530578697@qq.com
 *     date   : 2020/7/09
 *     description   : 订单实体类
 *     version: 2.0
 * </pre>
 */
package com.whu.soso.model;


import com.whu.soso.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class OrderList {
    @Id
    @Column(length = 20)
    private String id;

    //乘客手机号码
    @ManyToOne
    @JoinColumn
    private User user;
    //司机手机号码
    @ManyToOne
    @JoinColumn
    private Driver driver;
    //订单创建时间


    //@Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //预约出发时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date appointment;

    //订单类型
    private Integer order_type;

    //上车地点
    private String origin_address;

    //下车地点
    private String destination_address;

    //上车经度
    private double origin_longitude;

    //上车纬度
    private double origin_latitude;

    //下车经度
    private double des_longitude;

    //下车纬度
    private double des_latitude;

    //订单状态
    private int status;

    //订单金额
    private int order_money;

    //订单取消原因
    private String cancel_reason;

    //已行驶路程
    private int already_driver;


    @Temporal(TemporalType.TIMESTAMP)
    //实际出发时间
    private Date origin_time;

    @Temporal(TemporalType.TIMESTAMP)
    //实际到达时间
    private Date des_time;

    //乘客评论
    private String comment;


    @PreUpdate
    public void setStatus() {

        if (this.order_type == 0 & this.driver != null) {
            this.driver.setStatus(2);
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getAppointment() {
        return appointment;
    }

    public void setAppointment(Date appointment) {
        appointment = appointment;
    }

    public Integer getOrder_type() {
        return order_type;
    }

    public void setOrder_type(int order_type) {
        this.order_type = order_type;
    }

    public String getOrigin_address() {
        return origin_address;
    }

    public void setOrigin_address(String origin_address) {
        this.origin_address = origin_address;
    }

    public String getDestination_address() {
        return destination_address;
    }

    public void setDestination_address(String destination_address) {
        this.destination_address = destination_address;
    }

    public double getOrigin_longitude() {
        return origin_longitude;
    }

    public void setOrigin_longitude(double origin_longitude) {
        this.origin_longitude = origin_longitude;
    }

    public double getOrigin_latitude() {
        return origin_latitude;
    }

    public void setOrigin_latitude(double origin_latitude) {
        this.origin_latitude = origin_latitude;
    }

    public double getDes_longitude() {
        return des_longitude;
    }

    public void setDes_longitude(double des_longitude) {
        this.des_longitude = des_longitude;
    }

    public double getDes_latitude() {
        return des_latitude;
    }

    public void setDes_latitude(double des_latitude) {
        this.des_latitude = des_latitude;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrder_money() {
        return order_money;
    }

    public void setOrder_money(int order_money) {
        this.order_money = order_money;
    }

    public String getCancel_reason() {
        return cancel_reason;
    }

    public void setCancel_reason(String cancel_reason) {
        this.cancel_reason = cancel_reason;
    }

    public int getAlready_driver() {
        return already_driver;
    }

    public void setAlready_driver(int already_driver) {
        this.already_driver = already_driver;
    }

    public Date getOrigin_time() {
        return origin_time;
    }

    public void setOrigin_time(Date origin_time) {
        this.origin_time = origin_time;
    }

    public Date getDes_time() {
        return des_time;
    }

    public void setDes_time(Date des_time) {
        this.des_time = des_time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
