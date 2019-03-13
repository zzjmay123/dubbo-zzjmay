package com.zzjmay.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * Created by zzjmay on 2019/3/12.
 */
public class DubboSPITest {

    public static void main(String[] args) {
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);

        //测试Adaptive场景，因为Dubbo采用的URL总线设计，所以通过url上的参数指定对应的实现类

        URL url = URL.valueOf("test://localhost/test?test=jony");

        Robot herryRobot = extensionLoader.getAdaptiveExtension();

        herryRobot.sayHello(url);

    }
}
