package com.ming.zhihuWebSpider.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

import com.ming.zhihuWebSpider.pipeline.UserDetailInfoPipeline;
import com.ming.zhihuWebSpider.process.UserDetailInfoProcessor;

@Component
public class UserDetailInfoSpider implements Crawl {

	private static final String START_URL = "http://www.zhihu.com/people/excited-vczh/about";

	@Autowired
	private UserDetailInfoPipeline userDetailInfoPipeline;

	public void crawl() {
		Spider.create(new UserDetailInfoProcessor())
				.addUrl(START_URL)
				.addPipeline(userDetailInfoPipeline)
				.setScheduler(new FileCacheQueueScheduler("/usr/zhihu/cache")).thread(1).run();
	}

	public static void main(String[] args) {
		applicationContext.getBean(UserDetailInfoSpider.class).crawl();
	}

}
