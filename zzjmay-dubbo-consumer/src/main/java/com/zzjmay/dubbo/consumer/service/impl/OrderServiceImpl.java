package com.zzjmay.dubbo.consumer.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zzjmay.dubbo.api.domain.OrderInfo;
import com.zzjmay.dubbo.api.domain.UserAdress;
import com.zzjmay.dubbo.api.export.OrderInfoService;
import com.zzjmay.dubbo.api.export.UserService;
import com.zzjmay.dubbo.consumer.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zzjmay on 2018/12/11.
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 引用dubbo的服务
     * 启动时不检查
     */
    @Resource
    UserService userService;

    @Resource
    OrderInfoService orderInfoService;


    @Override
    public List<UserAdress> queryUserInfo(String userId) {
        return userService.queryUserAdress(userId);
    }

    @Override
    public List<OrderInfo> queryOrderInfo(String orderId) {
        return orderInfoService.queryOrderInfo(orderId);
    }

    @Override
    public boolean insert(UserAdress userAdress) {
        return userService.insertUserAddress(userAdress);
    }


}
