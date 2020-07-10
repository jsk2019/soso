package com.whu.soso.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

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
    private Double longitude;
    //纬度
    private Double latitude;
    //城市
    private String city;


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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
