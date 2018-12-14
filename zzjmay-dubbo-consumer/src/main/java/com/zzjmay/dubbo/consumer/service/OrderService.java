package com.zzjmay.dubbo.consumer.service;

import com.zzjmay.dubbo.api.domain.OrderInfo;
import com.zzjmay.dubbo.api.domain.UserAdress;

import java.util.List;

/**
 * Created by zzjmay on 2018/12/11.
 */
public interface OrderService {


    List<UserAdress> queryUserInfo(String userId);

    List<OrderInfo> queryOrderInfo(String orderId);
}
