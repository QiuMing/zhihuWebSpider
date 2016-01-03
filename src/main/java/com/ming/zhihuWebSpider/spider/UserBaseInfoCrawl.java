package com.ming.zhihuWebSpider.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import com.ming.zhihuWebSpider.mapping.UserBaseInfoMapper;
import com.ming.zhihuWebSpider.model.UserBaseInfo;
import com.ming.zhihuWebSpider.pipeline.UserBaseInfoPipeline;

@Component
public class UserBaseInfoCrawl {

	@Autowired
	private UserBaseInfoPipeline userBaseInfoPipeline;

	private static final String START_URL = "http://www.zhihu.com/people/excited-vczh";

	@Autowired
	private UserBaseInfoMapper userBaseInfoMapper;

	private Site site = Site
			.me()
			.setCycleRetryTimes(5)
			.setRetryTimes(5)
			.setSleepTime(300)
			.setTimeOut(3 * 60 * 1000)
			.setUserAgent(
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
			.setCharset("UTF-8");

	public void crawl() {
		JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
		OOSpider.create(site, userBaseInfoPipeline, UserBaseInfo.class)
				.scheduler(new RedisScheduler(pool)).addUrl(START_URL)
				.thread(4).run();
	}

}
