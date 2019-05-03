package com.java.ptp;

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
	        // ��Ϣ����Ŀ���ַ
	        Destination destination;
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
	            destination = session.createQueue("MsgQuene");
	            // ������Ϣ������
	            MessageConsumer consumer = session.createConsumer(destination);
	            //ע����Ϣ����
	            consumer.setMessageListener(new MessageListener() {
					
					public void onMessage(Message message) {
						TextMessage msg = (TextMessage) message;
					    try{
					    	System.out.println(msg.getText());
					    }catch(Exception e){
					    	e.printStackTrace();
					    }
						
					}
				});
	        } catch (JMSException e) {
	            e.printStackTrace();
	        }
	    }
}
