package com.zzjmay.dubbo.product.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzjmay.dubbo.api.domain.OrderInfo;
import com.zzjmay.dubbo.api.domain.UserAdress;
import com.zzjmay.dubbo.api.export.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 注意这个@Service 是dubbo下的服务暴露，而不是spring的
 * Created by zzjmay on 2018/12/10.
 */
@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public List<UserAdress> queryUserAdress(String userId) {
        logger.info("进入服务提供者UserServiceImpl>queryUserAdress userId:{}",userId);
        try {
            List<UserAdress> userAdresses = new ArrayList<>();

            UserAdress userAdress1 = new UserAdress("JONY", "13890872908", "北京朝阳区");
            UserAdress userAdress2 = new UserAdress("Lily", "18979019897", "浙江杭州余杭区");

            userAdresses.add(userAdress1);
            userAdresses.add(userAdress2);
            //测试链接的超时时间
            Thread.sleep(4000);
            return userAdresses;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<OrderInfo> queryOrderInfo(String orderId) {
        logger.info("进入服务提供者UserServiceImpl>queryOrderInfo orderId:{}",orderId);
        try {
            List<OrderInfo> orderInfoList = new ArrayList<>();

            OrderInfo orderInfo1 = new OrderInfo("123456", "樱桃", 69L);
            OrderInfo orderInfo2 = new OrderInfo("123458", "酸奶", 19L);

            orderInfoList.add(orderInfo1);
            orderInfoList.add(orderInfo2);

            Thread.sleep(3000);
            return orderInfoList;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
