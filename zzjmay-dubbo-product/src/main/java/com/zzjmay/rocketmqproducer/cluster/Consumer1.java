package com.zzjmay.rocketmqproducer.cluster;

import com.zzjmay.config.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * Created by zzjmay on 2019/3/31.
 */
public class Consumer1 {


    public Consumer1(){
        try {
            //group1
            String group_name = "zzjmay_cluster_consumers";

            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(group_name);

            consumer.setNamesrvAddr(Constants.NAMESRV_ADDR);
            consumer.setVipChannelEnabled(false);
            consumer.subscribe(Constants.CLUSTER_TOPIC,"*");
            //使用sql表达式进行过滤
//            consumer.subscribe(Constants.CLUSTER_TOPIC, MessageSelector.bySql("useA between 0 and 3"));
            //指定消费模式
            consumer.setMessageModel(MessageModel.CLUSTERING);


//            consumer.setMessageModel(MessageModel.BROADCASTING);
            consumer.registerMessageListener(new MyListener());
            consumer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Consumer1 consumer1 = new Consumer1();
        System.out.println("C1 start....");
    }
}
