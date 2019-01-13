package com.zzjmay.dubbo.api.domain;

import java.io.Serializable;

/**
 * 用户信息
 * Created by zzjmay on 2018/12/10.
 */
public class UserAdress implements Serializable {

    /**
     * 收货人
     */
    private String consignee;

    private String phone;

    private String adress;

    public UserAdress(String consignee, String phone, String adress) {
        this.consignee = consignee;
        this.phone = phone;
        this.adress = adress;
    }

    public UserAdress() {
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "UserAdress{" +
                "consignee='" + consignee + '\'' +
                ", phone='" + phone + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
