package com.zzjmay.rocketmqproducer.orderproducer;

import com.zzjmay.config.Constants;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * 发送顺序消息
 * Created by zzjmay on 2019/4/11.
 */
public class TestOrderProducer {

    public static void main(String[] args) {
        //1.创建生产者，指定生产者组
        DefaultMQProducer producer = new DefaultMQProducer("test_producer_A");

        //2.设置命名中心
        producer.setNamesrvAddr(Constants.NAMESRV_ADDR);

        //3.单台机器 关闭VIP通道
        producer.setVipChannelEnabled(false);

        try{
            //4. 启动生产者
            producer.start();

            for(int i = 0;i<10;i++){
                int orderId = i;
                Message message = new Message("test_order_topic","tagA","KEY"+i,("HELLO ORDER"+i).getBytes());

                //添加队列选择器，来保证正常消费
                producer.send(message, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {

                        System.out.println("queue selector mq nums:"+mqs.size());
                        System.out.println("msgInfo:"+msg.toString());

                        for(MessageQueue messageQueue : mqs){
                            System.out.println(messageQueue.toString());
                        }

                        //arg 就是send参数中的第三个参数
                        Integer id = (Integer) arg;
                        //按照ID进行路由到对应的队列
                        int index = id % mqs.size();

                        return mqs.get(index);
                    }
                },orderId);
            }

            producer.shutdown();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
