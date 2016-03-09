package com.web.oa.test.util;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.web.oa.model.Forum;
import com.web.oa.model.Reply;
import com.web.oa.model.Topic;
import com.web.oa.service.ForumService;
import com.web.oa.service.ReplyService;
import com.web.oa.service.TopicService;
import com.web.oa.service.UserService;

public class TopicAddDemo {
	@Test
	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"application*.xml");
		TopicService topicService = (TopicService) ac.getBean("topicService");
		ForumService forumService = (ForumService) ac.getBean("forumService");
		UserService userService = (UserService) ac.getBean("userService");
		Forum forum = forumService.findById(11);
		System.out.println(forum.getTitle());
		for (int i = 0; i < 150; i++) {
			Topic topic = new Topic();
			topic.setForum(forum);
			topic.setTitle("植物大战僵尸" + i);
			topic.setContent("在四分之一世纪中，有很多事情可能出错！科技的迅猛发展让我们一边疲于追赶一边大声抱怨！" + i);
			topic.setIpAddress("127.0.0.1");
			topic.setPostTime(new Date());
			topic.setAuthor(userService.findById(1));
			topicService.add(topic);
		}
	}

	// -------
	@Test
	public void testReply() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"application*.xml");
		TopicService topicService = (TopicService) ac.getBean("topicService");
		ReplyService replyService = (ReplyService) ac.getBean("replyService");
		ForumService forumService = (ForumService) ac.getBean("forumService");
		UserService userService = (UserService) ac.getBean("userService");
		Forum forum = forumService.findById(11);
		Topic topic = topicService.findById(161);
		topic.setForum(forum);
		for (int i = 0; i < 150; i++) {
            Reply reply=new Reply();
            reply.setTitle("楼主好人！楼主加油！");
            reply.setContent("只身赴宴鸡毛装，都是同学装鸡毛！");
			reply.setIpAddress("127.0.0.1");
			reply.setPostTime(new Date());
			reply.setAuthor(userService.findById(2));
			reply.setTopic(topic);
			replyService.add(reply);
		}
	}

}
