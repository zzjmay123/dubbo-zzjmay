package com.zzjmay.dubbo.api.domain;

import java.io.Serializable;

/**
 * Created by zzjmay on 2018/12/14.
 */
public class OrderInfo implements Serializable {

    private String orderId;

    private String productName;

    private Long amount;

    public OrderInfo(String orderId, String productName, Long amount) {
        this.orderId = orderId;
        this.productName = productName;
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
