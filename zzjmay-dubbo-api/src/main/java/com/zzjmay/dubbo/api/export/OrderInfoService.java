package com.zzjmay.dubbo.api.export;

import com.zzjmay.dubbo.api.domain.OrderInfo;

import java.util.List;

/**
 * Created by zzjmay on 2019/3/15.
 */
public interface OrderInfoService {

    List<OrderInfo> queryOrderInfo(String orderId);
}
