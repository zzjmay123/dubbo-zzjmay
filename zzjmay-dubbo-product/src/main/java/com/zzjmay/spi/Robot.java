package com.zzjmay.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * 使用Dubbo的SPI机制，需要使用dubbo的注解@SPI
 * Created by zzjmay on 2019/3/12.
 */
@SPI("default")
public interface Robot {

    @Adaptive({"test"})
    void sayHello(URL url);
}
