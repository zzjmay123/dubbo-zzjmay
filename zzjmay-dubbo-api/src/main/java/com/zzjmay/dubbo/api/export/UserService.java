package com.zzjmay.dubbo.api.export;

import com.zzjmay.dubbo.api.domain.OrderInfo;
import com.zzjmay.dubbo.api.domain.UserAdress;

import java.util.List;

/**
 * Created by zzjmay on 2018/12/10.
 */
public interface UserService {

    /**
     * 根据用户ID查询对应的地址
     * @param userId
     * @return
     */
    List<UserAdress> queryUserAdress(String userId);

    /**
     * 查询订单信息
     * @param orderId
     * @return
     */
    List<OrderInfo> queryOrderInfo(String orderId);


    boolean insertUserAddress(UserAdress userAdress);
}
