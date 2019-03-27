package com.zzjmay.dubbo.product.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzjmay.dubbo.api.domain.OrderInfo;
import com.zzjmay.dubbo.api.export.OrderInfoService;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzjmay on 2019/3/15.
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Override
    public List<OrderInfo> queryOrderInfo(String orderId) {

        List<OrderInfo> list = new ArrayList<>();

        OrderInfo orderInfo = new OrderInfo("12344","测试",121L);

        list.add(orderInfo);

        return list;
    }
}
