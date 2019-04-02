package com.zzjmay.rocketmqproducer;


import com.zzjmay.config.Constants;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.junit.Test;

import java.util.List;

/**
 * Created by zzjmay on 2019/3/25.
 */
public class TestProducer {


    /**
     * 测试默认的发送者
     */
    @Test
    public void testDefaultProducer() {
        //1.创建生产者，指定生产者组
        DefaultMQProducer producer = new DefaultMQProducer("test_producer_A");

        //2.设置命名中心
        producer.setNamesrvAddr(Constants.NAMESRV_ADDR);

        //3.单台机器 关闭VIP通道
        producer.setVipChannelEnabled(false);
        try {
            //4. 启动生产者
            producer.start();

            //5.构建消息体
            for (int i = 0; i < 10; i++) {


                String tag = i % 2 == 0 ? "TagA" : "TagB";
                Message message = new Message
                        (Constants.CLUSTER_TOPIC, tag, "key" + i, ("filter" + i).getBytes());

                //设置用户自定义属性
//                message.putUserProperty("useA",String.valueOf(i));

                //同步发送消息
                //进行延迟消费
//              if (i%2 == 0){
//                message.setDelayTimeLevel(2);
//              }
                SendResult sendResult = producer.send(message);
                System.out.println("消息异步发送成功了 " + sendResult);


            }

            producer.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
