package com.zzjmay.dubbo.product.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzjmay.dubbo.api.domain.OrderInfo;
import com.zzjmay.dubbo.api.domain.UserAdress;
import com.zzjmay.dubbo.api.export.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 注意这个@Service 是dubbo下的服务暴露，而不是spring的
 * Created by zzjmay on 2018/12/10.
 */
@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<UserAdress> queryUserAdress(String userId) {
        logger.info("进入服务提供者UserServiceImpl>queryUserAdress userId:{}",userId);
        try {
            List<UserAdress> userAdresses = new ArrayList<>();

            //通过缓存进行读取对应的数据

            UserAdress userAdress = (UserAdress) redisTemplate.opsForValue().get(userId);
            logger.info("缓存数据库的数据 json:"+userAdress);

            userAdresses.add(userAdress);
            //测试链接的超时时间
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

    /**
     * 插入数据到redis客户端
     * @param userAdress
     * @return
     */
    @Override
    public boolean insertUserAddress(UserAdress userAdress) {

        if(userAdress == null){
            logger.info("##参数校验异常");
            return false;
        }

        boolean result = false;

        try{
            String userId = userAdress.getConsignee();//作为key
            redisTemplate.opsForValue().set(userId,userAdress);
            logger.info("缓存数据信息 userId:{},value:{}",userAdress,userAdress);
            result = true;

        }catch (Exception e){
            logger.error("##插入用户地址失败 userAdress:{}",userAdress,e);
        }

        return result;
    }
}
