package com.whu.soso.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class Driver {
    @Id
    @Column(unique=true)
    private Long tellphone;
    //密码
    private String password;
    //身份证号
    private String ID_num;
    //车名
    private String car_name;
    //车型
    private String car_model;
    //车牌号
    private String car_plate;
    //姓名
    private String name;
    //年龄
    private String age;
    //性别
    private String sex;
    //个人照片
    private String person_pic;
    //车辆照片
    private String car_pic;
    //接单量
    private Integer order_num;
    //0星数
    private Integer zero_star;
    //1星数
    private Integer one_star;
    //2星数
    private Integer two_star;
    //3星数
    private Integer three_star;
    //4星数
    private Integer four_star;
    //5星数
    private Integer five_star;
    //注册天数
    private Integer registration_day;
    //账户余额
    private Integer Balance;
    //手机外网IP地址
    private String IP_adress;
    //工作状态
    private Integer status;
    //经度
    private String longitude;
    //纬度
    private String latitude;
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

    public String getID_num() {
        return ID_num;
    }

    public void setID_num(String ID_num) {
        this.ID_num = ID_num;
    }

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public String getCar_plate() {
        return car_plate;
    }

    public void setCar_plate(String car_plate) {
        this.car_plate = car_plate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPerson_pic() {
        return person_pic;
    }

    public void setPerson_pic(String person_pic) {
        this.person_pic = person_pic;
    }

    public String getCar_pic() {
        return car_pic;
    }

    public void setCar_pic(String car_pic) {
        this.car_pic = car_pic;
    }

    public Integer getOrder_num() {
        return order_num;
    }

    public void setOrder_num(Integer order_num) {
        this.order_num = order_num;
    }

    public Integer getZero_star() {
        return zero_star;
    }

    public void setZero_star(Integer zero_star) {
        this.zero_star = zero_star;
    }

    public Integer getOne_star() {
        return one_star;
    }

    public void setOne_star(Integer one_star) {
        this.one_star = one_star;
    }

    public Integer getTwo_star() {
        return two_star;
    }

    public void setTwo_star(Integer two_star) {
        this.two_star = two_star;
    }

    public Integer getThree_star() {
        return three_star;
    }

    public void setThree_star(Integer three_star) {
        this.three_star = three_star;
    }

    public Integer getFour_star() {
        return four_star;
    }

    public void setFour_star(Integer four_star) {
        this.four_star = four_star;
    }

    public Integer getFive_star() {
        return five_star;
    }

    public void setFive_star(Integer five_star) {
        this.five_star = five_star;
    }

    public Integer getRegistration_day() {
        return registration_day;
    }

    public void setRegistration_day(Integer registration_day) {
        this.registration_day = registration_day;
    }

    public Integer getBalance() {
        return Balance;
    }

    public void setBalance(Integer balance) {
        Balance = balance;
    }

    public String getIP_adress() {
        return IP_adress;
    }

    public void setIP_adress(String IP_adress) {
        this.IP_adress = IP_adress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
