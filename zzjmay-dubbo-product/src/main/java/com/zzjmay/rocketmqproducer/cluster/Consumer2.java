package com.zzjmay.rocketmqproducer.cluster;

import com.zzjmay.config.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * Created by zzjmay on 2019/3/31.
 */
public class Consumer2 {


    public Consumer2(){
        try {
            //group2
            String group_name = "zzjmay_cluster3_consumers";

            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group_name);

            consumer.setNamesrvAddr(Constants.NAMESRV_ADDR);
            consumer.setVipChannelEnabled(false);
            consumer.subscribe(Constants.CLUSTER_TOPIC,"*");
            //指定消费模式--集群模式
            consumer.setMessageModel(MessageModel.CLUSTERING);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            //指定消费模式--广播模式
//            consumer.setMessageModel(MessageModel.BROADCASTING);
            consumer.registerMessageListener(new MyListener());

            consumer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Consumer2 consumer1 = new Consumer2();
        System.out.println("C2 start....");

    }
}
