package com.cc.activemq.mq.consumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/** 
 * Description: <br/> 
 * 消息队列监听器
 * @author chenchao
 * @date 2017年6月9日 11:40:37
 * 
 */ 
@Component
public class QueueReceiver2 implements MessageListener{

	@Override
	public void onMessage(Message message) {
		 try {
			 	System.out.println("QueueReceiver2接收到消息"+((TextMessage)message).getText());
	        } catch (JMSException e) {  
	            e.printStackTrace();  
	        } 
	}

}
