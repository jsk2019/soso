/**
 * <pre>
 *     author : 3D2Y.郑建宙、江圣坤
 *     e-mail : 530578697@qq.com
 *     date   : 2020/7/09
 *     description   : 聊天记录实体类
 *     version: 2.0
 * </pre>
 */

package com.whu.soso.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class ChatRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    //用户手机号码
    private String receiverPhone;
    //司机手机号码
    private String senderPhone;
    //消息发送时间

    private Date charTime;
    //消息记录
    private String record;

    @PrePersist
    void prePersist(){
        this.charTime=new Date();
    }

    @Column()
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String ReceiverPhone) {
        this.receiverPhone = ReceiverPhone;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String SenderPhone) {
        this.senderPhone = SenderPhone;
    }

    public Date getCharTime() {
        return charTime;
    }

    public void setCharTime(Date charTime) {
        this.charTime = charTime;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
