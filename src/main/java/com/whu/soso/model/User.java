/**
 * <pre>
 *     author : 3D2Y.郑建宙、江圣坤
 *     e-mail : 530578697@qq.com
 *     date   : 2020/7/09
 *     description   : 用户实体类
 *     version: 2.0
 * </pre>
 */

package com.whu.soso.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    @Column(unique = true)
    private String telephone;

    //密码
    private String password;
    //昵称
    private String nickname;
    //头像
    private String avatar;
    //优惠卷1
    private int one_coupon;
    //优惠卷2
    private int two_coupon;
    //优惠卷3
    private int three_coupon;
    //经度
    private double longitude;
    //纬度
    private double latitude;
    //账户余额
    private int Balance;
    //手机外网IP地址
    private String IP_address;
    //城市
    private String city;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderList> orderLists=new ArrayList<>();

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getOne_coupon() {
        return one_coupon;
    }

    public void setOne_coupon(int one_coupon) {
        this.one_coupon = one_coupon;
    }

    public int getTwo_coupon() {
        return two_coupon;
    }

    public void setTwo_coupon(int two_coupon) {
        this.two_coupon = two_coupon;
    }

    public int getThree_coupon() {
        return three_coupon;
    }

    public void setThree_coupon(int three_coupon) {
        this.three_coupon = three_coupon;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<OrderList> getOrderLists() {
        return orderLists;
    }

    public void setOrderLists(List<OrderList> orderLists) {
        this.orderLists = orderLists;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }

    public String getIP_address() {
        return IP_address;
    }

    public void setIP_address(String IP_address) {
        this.IP_address = IP_address;
    }
}
