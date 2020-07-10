package com.whu.soso.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class ChatRecord {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(unique=true)
    private Integer id;

    //用户手机号码
    private String user_tell;
    //司机手机号码
    private String driver_tell;
    //消息发送时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date char_time;
    //消息记录
    private String record;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_tell() {
        return user_tell;
    }

    public void setUser_tell(String user_tell) {
        this.user_tell = user_tell;
    }

    public String getDriver_tell() {
        return driver_tell;
    }

    public void setDriver_tell(String driver_tell) {
        this.driver_tell = driver_tell;
    }

    public Date getChar_time() {
        return char_time;
    }

    public void setChar_time(Date char_time) {
        this.char_time = char_time;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
