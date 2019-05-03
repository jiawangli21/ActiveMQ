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
 * ��Ϣ������
 * @author lijiawang_1
 *
 */
public class MsgConsumer {

	
	 public static void main(String[] args) {
	        // ���ӹ���
	        ConnectionFactory connectionFactory;
	        // ����ʵ��
	        Connection connection = null;
	        // �շ����߳�ʵ��
	        Session session;
	        MessageConsumer consumer = null;
	        // ��Ϣ����Ŀ���ַ
	        Destination destination;
	        Message message = null;
	        try {
	            // ʵ�������ӹ���
	            connectionFactory = new ActiveMQConnectionFactory(Constants.MQ_NAME, Constants.MQ_PASSWORD, Constants.MQ_BROKETURL);
	            // ��ȡ����ʵ��
	            connection = connectionFactory.createConnection();
	            // ��������
	            connection.start();
	            // �������ջ��͵��߳�ʵ���������߾Ͳ���Ҫ���������ˣ�
	            session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
	            // �������У�����һ����ϢĿ�ĵأ�
	            destination = session.createTopic("MsgTopic");
	            // ������Ϣ������
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
