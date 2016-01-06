package com.ming.zhihuWebSpider.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

import com.ming.zhihuWebSpider.extend.QueueNameConstant;
import com.ming.zhihuWebSpider.extend.RedisSchedulerExtend2;
import com.ming.zhihuWebSpider.model.UserDetailInfo;
import com.ming.zhihuWebSpider.pipeline.UserDetailInfoPipeline2;

@Component
public class UserDetailInfoSpider2 implements Crawl {

	private static final String START_URL = "http://www.zhihu.com/people/excited-vczh";

	@Autowired
	private UserDetailInfoPipeline2 userDetailInfoPipeline2;
	
	private Site site = Site.me()
			.setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(1000).setTimeOut(3 * 60 * 1000)
			.setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
                    .setCharset("UTF-8")
		            .addCookie("_xsrf", "6e73d7f68dae280f27655eabe0ab7c9c")
			        .addCookie("_za", "f46dbfc6-85d4-4851-852c-2539f9e7e7c2")
			        .addCookie("z_c0", "QUFDQVBrOGlBQUFYQUFBQVlRSlZUZXBpamxhbEI3VjEzMnFIYXR3U2o0Y0puaVU4amhRVGp3PT0=|1449580010|22d9dbb0761d2d713ee40aa33fb1f7f062dd5036")
			        .addCookie("__utmc", "51854390") ;

	public void crawl() {
		OOSpider.create(site, userDetailInfoPipeline2, UserDetailInfo.class)
		//.setDownloader(new HttpClientDownloaderExtend("/about"))
		.scheduler(new RedisSchedulerExtend2(pool,1,QueueNameConstant.QUEUE_USER_DETAIL_INFO))
		.addUrl(START_URL)
		.thread(4).run();
		
		
	}

	public static void main(String[] args) {
		applicationContext.getBean(UserDetailInfoSpider2.class).crawl();
	}

}
