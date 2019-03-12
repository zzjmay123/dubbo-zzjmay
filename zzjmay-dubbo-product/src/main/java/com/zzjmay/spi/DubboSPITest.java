package com.zzjmay.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * Created by zzjmay on 2019/3/12.
 */
public class DubboSPITest {

    public static void main(String[] args) {
        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);

        Robot herryRobot = extensionLoader.getExtension("herry");

        herryRobot.sayHello();

    }
}
