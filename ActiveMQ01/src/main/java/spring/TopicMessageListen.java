package spring;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TopicMessageListen implements MessageListener{

	 public void onMessage(Message message) {
	        System.out.println("收到我的第一个activemq-spring消息！");
	        try {
	        	//消息确认
	        	message.acknowledge();
	            TextMessage tm = (TextMessage)(message);
	            System.out.println(tm.getText());
	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        }
	        
	    }
}
