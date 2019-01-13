package com.zzjmay.dubbo.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zzjmay on 2018/12/28.
 */
public class Test {

    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        logger.info("test");
        long mode = 3458979L % 600;
        long dbIndex = mode / 200;
        long tbIndex = mode  % 200;
        long testIndex =3458979L%3;
        long testtbIndex = 3458979L  % 200;

        System.out.println("中间值"+mode);
        System.out.println("分库"+dbIndex);
        System.out.println("分表"+tbIndex);
        System.out.println("直接分库"+testIndex);
        System.out.println("直接分表"+testtbIndex);
    }
}
