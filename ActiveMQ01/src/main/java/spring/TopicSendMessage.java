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
                    message.setText("杩欐槸鎴戠殑绗竴涓猘ctivemq-spring椤圭洰锛�");
					return message;
				}
			};
	    	jmsTemplate.send(mc);
			System.out.println("娑堟伅鍙戦�佹垚鍔燂紒");
		}

	    public static void main(String[] args) {
	        new TopicSendMessage().sendMessage();
	    }
}
