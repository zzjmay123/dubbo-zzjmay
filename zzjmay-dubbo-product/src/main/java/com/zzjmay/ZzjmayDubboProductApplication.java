package com.zzjmay;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 实现dubbo的springboot版本
 * 1. 导入dubbo的starter的依赖
 * 2. 配置配置文件
 * 3. 暴露服务出去
 */
@EnableDubbo //开启基于注解的dubbo功能
@SpringBootApplication
public class ZzjmayDubboProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZzjmayDubboProductApplication.class, args);
	}
}
