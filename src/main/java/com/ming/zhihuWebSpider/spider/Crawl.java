package com.ming.zhihuWebSpider.spider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public interface Crawl {
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:/config/spring-*.xml");
	JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
	public void crawl();

}
