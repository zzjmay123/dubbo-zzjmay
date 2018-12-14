package com.zzjmay.dubbo.consumer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 引入dubbo的xml配置
 * Created by zzjmay on 2018/12/13.
 */
@Configuration
@ImportResource(locations = {"classpath:dubbo/dubbo-consumer.xml"})
public class DubboXmlConfig {
}
