package com.cc.activemq.mq.producer.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * ActiveMQ的topic/sub模式 Description: <br/>
 * Topic生产者发送消息到Topic
 * 
 * @author chenchao
 * @date 2017年6月9日 12:19:05
 * 
 */
@Component("topicSender")
public class TopicSender1 {
	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate jmsTemplate;

	/**
	 * 发送一条消息到指定的队列（目标）
	 * 
	 * @param queueName
	 *            队列名称
	 * @param message
	 *            消息内容
	 */
	public void send(String topicName, final String message) {
		jmsTemplate.send(topicName, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}
}
