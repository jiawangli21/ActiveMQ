package spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class TopicSendMessage {
	    private  ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:activemq-springConfig.xml");
	    private  JmsTemplate jmsTemplate = (JmsTemplate) ac.getBean("jmsTemplate");

	    public void sendMessage(){
	    	MessageCreator mc = new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {

					TextMessage message = session.createTextMessage();
                    message.setText("这是我的第一个activemq-spring项目！");
					return message;
				}
			};
	    	jmsTemplate.send(mc);
			System.out.println("消息发送成功！");
		}

	    public static void main(String[] args) {
	        new TopicSendMessage().sendMessage();

	    }
}
