package com.cc.activemq.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cc.activemq.mq.producer.queue.QueueSender1;
import com.cc.activemq.mq.producer.topic.TopicSender1;
/**
 * Description: <br/>
 * 
 * @author chenchao
 * @date 2017年6月9日 14:03:03
 * 
 */
@Controller
@RequestMapping("activemq")
public class ActivemqController1 {
	@Resource
	private QueueSender1 queueSender;
	@Autowired
	private TopicSender1 topicSender;

	/**
	 * 点对点模式 主要建立在一个队列上面，当连接一个列队的时候，发送端不需要知道接收端是否正在接收，可以直接向ActiveMQ发送消息，发送的消息，
	 * 将会先进入队列中
	 * ，如果有接收端在监听，则会发向接收端，如果没有接收端接收，则会保存在activemq服务器，直到接收端接收消息，点对点的消息模式可以有多个发送端
	 * ，多个接收端，但是一条消息，只会被一个接收端给接收到，哪个接收端先连上ActiveMQ，则会先接收到，而后来的接收端则接收不到那条消息
	 * 发送消息到队列 Queue队列：仅有一个订阅者会收到消息，消息一旦被处理就不会存在队列中
	 * 
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="queueSender",method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	public String queueSender(@RequestParam("message")String message) {
		String opt = "";
		try {
			queueSender.send("test.queue", message);
			System.out.println(message);
			opt = message;
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}

	/**
	 * 订阅/发布模式，同样可以有着多个发送端与多个接收端，但是接收端与发送端存在时间上的依赖，就是如果发送端发送消息的时候，接收端并没有监听消息，
	 * 那么ActiveMQ将不会保存消息
	 * ，将会认为消息已经发送，换一种说法，就是发送端发送消息的时候，接收端不在线，是接收不到消息的，哪怕以后监听消息，同样也是接收不到的
	 * 。这个模式还有一个特点，那就是，发送端发送的消息，将会被所有的接收端给接收到，不类似点对点，一条消息只会被一个接收端给接收到。
	 * 
	 * @param message
	 * @return
	 */
	@ResponseBody
	@RequestMapping("topicSender")
	public String topoicSender(@RequestParam("message") String message) {
		String opt = "";
		try {
			topicSender.send("test.topic", message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}
}
