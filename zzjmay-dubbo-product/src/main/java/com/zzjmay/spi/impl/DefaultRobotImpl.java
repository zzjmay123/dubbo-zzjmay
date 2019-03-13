package com.zzjmay.spi.impl;

import com.alibaba.dubbo.common.URL;
import com.zzjmay.spi.Robot;

/**
 * Created by zzjmay on 2019/3/12.
 */
public class DefaultRobotImpl implements Robot {
    @Override
    public void sayHello(URL url) {

        System.out.println("Hello every ,I am default");

    }
}
