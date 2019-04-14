package com.zzjmay.rocketmqproducer.orderproducer;

import com.zzjmay.config.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 顺序消息消费场景
 * Created by zzjmay on 2019/4/11.
 */
public class TestOrderConsumer {

    public static void main(String[] args) {
//1. 创建Consumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("zzjmay-consumer-group");

        //2. 指定Namesrc
        consumer.setNamesrvAddr(Constants.NAMESRV_ADDR);
        //3. 屏蔽VIPIP
        consumer.setVipChannelEnabled(false);
        //4. 指定从哪开始读取
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        try {
            //5.订阅消息
            consumer.subscribe("test_order_topic", "*");

            //6.创建顺序消息的监听器
            consumer.registerMessageListener(new MessageListenerOrderly() {
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                    System.out.println("Received new Messages:" + new String(msgs.get(0).getBody())+"，times"+System.currentTimeMillis());

                    try {
                        //睡2s
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });

            //7. 启动Consumer
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
