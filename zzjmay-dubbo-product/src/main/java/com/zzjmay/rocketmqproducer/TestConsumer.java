package com.zzjmay.rocketmqproducer;

import com.zzjmay.config.Constants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * Created by zzjmay on 2019/3/26.
 */
public class TestConsumer {

    public static void main(String[] args) {
        //1. 创建Consumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("zzjmay-consumer-group");

        //2. 指定Namesrc
        consumer.setNamesrvAddr(Constants.NAMESRV_ADDR);
        //3. 屏蔽VIPIP
        consumer.setVipChannelEnabled(false);
        //4. 指定从哪开始读取
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //设置批处理消息,默认的消息是1条
//       consumer.setConsumeMessageBatchMaxSize(3);
        try {
            //5.订阅消息
            consumer.subscribe("test_zzjmay_topic", "*");

            //6.创建监听
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    //当设置批量处理消息的大小的时候，才有多条
                    MessageExt messageExt = msgs.get(0);
                    try{
                        String topic = messageExt.getTopic();
                        String key = messageExt.getKeys();
                        String tag = messageExt.getTags();

                        if(key.equals("key1")){
                            System.out.println("消息消费失败");
                            int a =1/0;
                        }
                        String msgBody = new String(messageExt.getBody(),"UTF-8");

                        System.out.println("Thread:"+Thread.currentThread().getName()+Thread.currentThread().getId()+",topic:"+topic+",msgbody:"+msgBody);


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


                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            //7. 启动Consumer
            consumer.start();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
