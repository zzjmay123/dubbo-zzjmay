package com.zzjmay.dubbo.product.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zzjmay.dubbo.api.domain.UserAdress;
import com.zzjmay.dubbo.api.export.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 注意这个@Service 是dubbo下的服务暴露，而不是spring的
 * Created by zzjmay on 2018/12/10.
 */
@Service
public class UserServiceImpl implements UserService {


    @Override
    public List<UserAdress> queryUserAdress(String userId) {
        System.out.println("进入服务提供者UserServiceImpl>queryUserAdress");

        List<UserAdress> userAdresses = new ArrayList<>();

        UserAdress userAdress1= new UserAdress("JONY","13890872908","北京朝阳区");
        UserAdress userAdress2= new UserAdress("Lily","18979019897","浙江杭州余杭区");

        userAdresses.add(userAdress1);
        userAdresses.add(userAdress2);

        return userAdresses;
    }
}
