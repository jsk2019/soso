package com.whu.soso.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class User {
    @Id
    @Column(unique=true)
    private Long tellphone ;
    //密码
    private String password;
    //昵称
    private String nickname;
    //头像
    private String avatar;
    //优惠卷1
    private Integer one_coupon;
    //优惠卷2
    private Integer two_coupon;
    //优惠卷3
    private Integer three_coupon;
    //经度
    private Double longitude;
    //纬度
    private Double latitude;
    //城市
    private String city;

    public Long getTellphone() {
        return tellphone;
    }

    public void setTellphone(Long tellphone) {
        this.tellphone = tellphone;
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

    public Integer getOne_coupon() {
        return one_coupon;
    }

    public void setOne_coupon(Integer one_coupon) {
        this.one_coupon = one_coupon;
    }

    public Integer getTwo_coupon() {
        return two_coupon;
    }

    public void setTwo_coupon(Integer two_coupon) {
        this.two_coupon = two_coupon;
    }

    public Integer getThree_coupon() {
        return three_coupon;
    }

    public void setThree_coupon(Integer three_coupon) {
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
