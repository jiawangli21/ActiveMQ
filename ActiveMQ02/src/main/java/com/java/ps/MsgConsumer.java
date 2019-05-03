package com.java.ps;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.java.ptp.Constants;

/**
 * 消息消费者
 * @author lijiawang_1
 *
 */
public class MsgConsumer {

	
	 public static void main(String[] args) {
	        // 连接工厂
	        ConnectionFactory connectionFactory;
	        // 连接实例
	        Connection connection = null;
	        // 收发的线程实例
	        Session session;
	        MessageConsumer consumer = null;
	        // 消息发送目标地址
	        Destination destination;
	        Message message = null;
	        try {
	            // 实例化连接工厂
	            connectionFactory = new ActiveMQConnectionFactory(Constants.MQ_NAME, Constants.MQ_PASSWORD, Constants.MQ_BROKETURL);
	            // 获取连接实例
	            connection = connectionFactory.createConnection();
	            // 启动连接
	            connection.start();
	            // 创建接收或发送的线程实例（消费者就不需要开启事务了）
	            session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
	            // 创建队列（返回一个消息目的地）
	            destination = session.createTopic("MsgTopic");
	            // 创建消息消费者
	            consumer = session.createConsumer(destination);
                
	            message = consumer.receive();
	            TextMessage msg = (TextMessage) message;
	            System.out.println(msg.getText());
	        } catch (JMSException e) {
	            e.printStackTrace();
	        }finally{
	        	try {
					if(consumer!=null)
					consumer.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}	
	        }
	    }
}
