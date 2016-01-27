package com.ming.zhihuWebSpider.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import us.codecraft.webmagic.Spider;

import com.ming.zhihuWebSpider.extend.QueueNameConstant;
import com.ming.zhihuWebSpider.extend.RedisSchedulerExtend2;
import com.ming.zhihuWebSpider.pipeline.UserDetailInfoPipeline;
import com.ming.zhihuWebSpider.process.UserDetailInfoProcessor;

/**
 * 用户详细信息抓取， 非注解方式
 * @Description TODO
 * @author Ming
 * @date: 2016年1月27日--下午12:11:17
 */
@Component
public class UserDetailInfoSpider implements Crawl {

	private static final String START_URL = "http://www.zhihu.com/people/excited-vczh";

	@Autowired
	private UserDetailInfoPipeline userDetailInfoPipeline;

	public void crawl() {
		
		JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
		pool.getResource().select(2);
		Spider.create(new UserDetailInfoProcessor())
				.addUrl(START_URL)
				.addPipeline(userDetailInfoPipeline)
				//.setScheduler(new FileCacheQueueScheduler("/usr/zhihu/cache"))
				//.setDownloader(new HttpClientDownloaderExtend("/about"))
				.scheduler(new RedisSchedulerExtend2(pool,1,QueueNameConstant.QUEUE_USER_DETAIL_INFO))
				.thread(1).run();
	}

	public static void main(String[] args) {
		applicationContext.getBean(UserDetailInfoSpider.class).crawl();
	}

}
