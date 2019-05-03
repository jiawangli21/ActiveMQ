package spring;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TopicMessageListen implements MessageListener{

	 public void onMessage(Message message) {
	        System.out.println("鏀跺埌鎴戠殑绗竴涓猘ctivemq-spring娑堟伅锛�");
	        try {
	        	//娑堟伅纭
	        	message.acknowledge();
	            TextMessage tm = (TextMessage)(message);
	            System.out.println(tm.getText());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
