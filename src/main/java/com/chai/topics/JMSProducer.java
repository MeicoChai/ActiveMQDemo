package com.chai.topics;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Author: minquan.chai@hand-china.com
 * @Modificd By:
 * @Date: Created in chai on 2018/9/28.
 */
public class JMSProducer {

    //默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    //默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    //默认连接地址
    private static final String BROKER_URL = "tcp://192.168.153.128:61616";
    
    public static void main(String[] args){
        //连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKER_URL);

        try {

            //连接
            Connection connection = connectionFactory.createConnection();

            //启动连接
            connection.start();

            //创建session
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            //消息目的地
            Destination destination = session.createTopic("hello");

            //消息生产者
            MessageProducer producer = session.createProducer(destination);

            //设置不持久化，此处学习，实际根据具体项目决定
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            //发送消息
            for (int i = 0; i < 10; i++) {
                //创建一条文本消息
                TextMessage message = session.createTextMessage("ActiveMQ: 这是第 "+ i +" 条消息");

                //生产者发送消息
                producer.send(message);
            }

            session.commit();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
