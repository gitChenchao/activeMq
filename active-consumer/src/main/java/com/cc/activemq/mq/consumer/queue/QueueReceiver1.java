package com.cc.activemq.mq.consumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/** 
 * Description: <br/> 
 * 消息队列监听器
 * @author hetiewei 
 * @date 2017年6月9日 11:40:02
 * 
 */ 
@Component
public class QueueReceiver1 implements MessageListener{

	@Override
	public void onMessage(Message message) {
		 try {
	            System.out.println("QueueReceiver1接收到消息"+((TextMessage)message).getText());  
	        } catch (JMSException e) {  
	            e.printStackTrace();  
	        } 
	}

}
