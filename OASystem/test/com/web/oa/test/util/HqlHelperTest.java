package com.web.oa.test.util;

import org.junit.Test;

import com.web.oa.model.Reply;
import com.web.oa.model.Topic;
import com.web.oa.utils.HqlHelper;

public class HqlHelperTest {
  
	//测试sql语句受否拼接成功
	@Test
	public void testHql(){
		HqlHelper hh=new HqlHelper(Topic.class,"t")
		.addWhereClause("forum=?", 1)
		.addOrderByClause(true, true, "t.lastUpdateTime");
		System.out.println(hh.getHqlList()+"*****");
		System.out.println(hh.getHqlCount()+"$$$$$");
	}
	
	@Test
	public void testReplyHql(){
		HqlHelper hh=new HqlHelper(Reply.class,"r")
		.addWhereClause("topic=?", 1)
		.addOrderByClause(true, false, "r.postTime");
		System.out.println(hh.getHqlList()+"*****");
		System.out.println(hh.getHqlCount()+"$$$$$");
	}
}
