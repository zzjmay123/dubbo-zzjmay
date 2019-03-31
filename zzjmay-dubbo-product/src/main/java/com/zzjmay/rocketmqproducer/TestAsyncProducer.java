package com.zzjmay.rocketmqproducer;


import com.zzjmay.config.Constants;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;

/**
 * Created by zzjmay on 2019/3/25.
 */
public class TestAsyncProducer {

    /**
     * 需要注意一点，用JUINT框架在进行测试的时候，异步流程会不成功
     * @param args
     */
    public static void main(String[] args) {
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
                        ("test_zzjmay_topic","Tagasync1","key"+i,("1sdd434"+i).getBytes());

                //同步发送消息
//              SendResult sendResult = producer.send(message);
//              System.out.println("消息异步发送成功了 "+sendResult);

                //异步发送消息
                producer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        //发送消息成功回调
                        System.out.println("消息异步发送成功了 "+sendResult);
                    }

                    @Override
                    public void onException(Throwable e) {
                        //发送消息异常
                        System.out.println("消息异步发送异常了");

                    }
                });

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
