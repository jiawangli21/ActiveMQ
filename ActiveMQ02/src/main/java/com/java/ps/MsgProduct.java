package com.java.ps;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.java.ptp.Constants;

/**
 *  消息生产者 massage productor
 * @author lijiawang_1
 *
 */
public class MsgProduct {

	public static void main(String[] args) {
        // 连接工厂
        ConnectionFactory factory;
        // 连接实例
        Connection connection = null;
        // 收发的线程实例
        Session session;
        // 消息发送目标地址
        Destination destination;
        // 消息创建者
        MessageProducer messageProducer;
        try {
            factory = new ActiveMQConnectionFactory(Constants.MQ_NAME,Constants.MQ_PASSWORD,Constants.MQ_BROKETURL);
            // 获取连接实例
            connection = factory.createConnection();
            // 启动连接
            connection.start();
            // 创建接收或发送的线程实例（创建session的时候定义是否要启用事务，且事务类型是Auto_ACKNOWLEDGE也就是消费者成功在Listern中获得消息返回时，会话自动确定用户收到消息）
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建队列（返回一个消息目的地）
            destination = session.createTopic("MsgTopic");
            // 创建消息生产者
            messageProducer = session.createProducer(destination);
            // 创建TextMessage消息实体
            TextMessage message = session.createTextMessage("我是jiawangli,这是我的第一个topic消息！");
            messageProducer.send(message);
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
