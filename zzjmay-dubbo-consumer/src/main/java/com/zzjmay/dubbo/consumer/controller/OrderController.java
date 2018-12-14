package com.zzjmay.dubbo.consumer.controller;

import com.zzjmay.dubbo.api.domain.OrderInfo;
import com.zzjmay.dubbo.api.domain.UserAdress;
import com.zzjmay.dubbo.consumer.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单服务控制层
 * Created by zzjmay on 2018/12/11.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;


    @ResponseBody
    @RequestMapping("/queryUserInfo")
    public List<UserAdress> querUserInfo(@RequestBody String userId){

        return orderService.queryUserInfo(userId);
    }

    @ResponseBody
    @RequestMapping("/queryorderInfo")
    public List<OrderInfo> querOrderInfo(@RequestBody String userId){

        return orderService.queryOrderInfo(userId);
    }
}
