package com.zzjmay.rocketmqproducer.cluster;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 监听器
 * Created by zzjmay on 2019/3/31.
 */
public class MyListener implements MessageListenerConcurrently {


    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

            for (MessageExt messageExt:msgs) {
                try {
                    String topic = messageExt.getTopic();
                    String key = messageExt.getKeys();
                    String tag = messageExt.getTags();

                    String msgBody = new String(messageExt.getBody(), "UTF-8");

                    System.out.println("Thread:" + Thread.currentThread().getName() + Thread.currentThread().getId() + ",topic:" + topic + ",key" + key + ",Tag:" + tag + ",msgbody:" + msgBody);

                }catch (Exception e){
                    e.printStackTrace();
                    //进行重试,获取重试次数
                    if(messageExt.getReconsumeTimes() == 3) {
                        System.out.println("已经重试了3次");
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    System.out.println("当前重试次数："+messageExt.getReconsumeTimes());
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
