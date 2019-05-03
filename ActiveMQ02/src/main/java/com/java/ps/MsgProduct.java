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
 *  ��Ϣ������ massage productor
 * @author lijiawang_1
 *
 */
public class MsgProduct {

	public static void main(String[] args) {
        // ���ӹ���
        ConnectionFactory factory;
        // ����ʵ��
        Connection connection = null;
        // �շ����߳�ʵ��
        Session session;
        // ��Ϣ����Ŀ���ַ
        Destination destination;
        // ��Ϣ������
        MessageProducer messageProducer;
        try {
            factory = new ActiveMQConnectionFactory(Constants.MQ_NAME,Constants.MQ_PASSWORD,Constants.MQ_BROKETURL);
            // ��ȡ����ʵ��
            connection = factory.createConnection();
            // ��������
            connection.start();
            // �������ջ��͵��߳�ʵ��������session��ʱ�����Ƿ�Ҫ��������������������Auto_ACKNOWLEDGEҲ���������߳ɹ���Listern�л����Ϣ����ʱ���Ự�Զ�ȷ���û��յ���Ϣ��
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // �������У�����һ����ϢĿ�ĵأ�
            destination = session.createTopic("MsgTopic");
            // ������Ϣ������
            messageProducer = session.createProducer(destination);
            // ����TextMessage��Ϣʵ��
            TextMessage message = session.createTextMessage("����jiawangli,�����ҵĵ�һ��topic��Ϣ��");
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
