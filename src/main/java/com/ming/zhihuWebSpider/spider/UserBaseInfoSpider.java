package com.ming.zhihuWebSpider.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import com.ming.zhihuWebSpider.extend.QueueNameConstant;
import com.ming.zhihuWebSpider.extend.RedisSchedulerExtend2;
import com.ming.zhihuWebSpider.model.UserBaseInfo;
import com.ming.zhihuWebSpider.pipeline.UserBaseInfoPipeline;


@Component
public class UserBaseInfoSpider implements Crawl {

	@Autowired
	private UserBaseInfoPipeline userBaseInfoPipeline;

	private static final String START_URL = "http://www.zhihu.com/people/excited-vczh";

	private Site site = Site
			.me()
			.setCycleRetryTimes(5)
			.setRetryTimes(5)
			.setSleepTime(1000)
			.setTimeOut(3 * 60 * 1000)
			.setUserAgent(
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
			.setCharset("UTF-8");

	public void crawl() {
		OOSpider.create(site, userBaseInfoPipeline, UserBaseInfo.class)
				.scheduler(new RedisSchedulerExtend2(pool,1,QueueNameConstant.QUEUE_USER_BASE_INFO))
				.addUrl(START_URL)
				.thread(4).run();
	}

	public static void main(String[] args) {
		applicationContext.getBean(UserBaseInfoSpider.class).crawl();
	}
}
