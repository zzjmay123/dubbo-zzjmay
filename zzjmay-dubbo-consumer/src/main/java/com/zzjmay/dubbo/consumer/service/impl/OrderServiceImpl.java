package com.zzjmay.dubbo.consumer.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zzjmay.dubbo.api.domain.UserAdress;
import com.zzjmay.dubbo.api.export.UserService;
import com.zzjmay.dubbo.consumer.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zzjmay on 2018/12/11.
 */
@Service
public class OrderServiceImpl implements OrderService {

    /**
     * 引用dubbo的服务
     */
    @Reference(timeout = 5000)
    UserService userService;

    @Override
    public List<UserAdress> queryUserInfo(String userId) {
        return userService.queryUserAdress(userId);
    }
}
