package com.zzjmay.rocketmqproducer;


import com.zzjmay.config.Constants;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;

/**
 * Created by zzjmay on 2019/3/25.
 */
public class TestProducer {


   /**
    * 测试默认的发送者
    */
    @Test
   public void testDefaultProducer(){
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
          for(int i=0;i<5;i++){
             Message message = new Message
                     ("test_zzjmay_topic","tagW","key"+i,("1sdd434"+i).getBytes());

             SendResult sendResult = producer.send(message);

             System.out.println("消息发出去了:"+sendResult);
          }

       }catch (Exception e){
          e.printStackTrace();
       }finally {
           //6. 关闭生产者
          producer.shutdown();
       }
   }
}
