package com.ming.zhihuWebSpider.process;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Strings;
import org.joda.time.DateTime;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.RedisScheduler;


public class UserDetailInfoProcessor implements PageProcessor{

	private static final String START_URL  = "http://www.zhihu.com/people/excited-vczh/about";
		
	private static final String TARGET_USER_BASE_INFO = "http://www\\.zhihu\\.com/people/[\\w-]+";
		
	private Site site = Site.me()
			.setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(300).setTimeOut(3 * 60 * 1000)
			.setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
                    .setCharset("UTF-8")
		            .addCookie("_xsrf", "6e73d7f68dae280f27655eabe0ab7c9c")
			        .addCookie("_za", "f46dbfc6-85d4-4851-852c-2539f9e7e7c2")
			        .addCookie("z_c0", "QUFDQVBrOGlBQUFYQUFBQVlRSlZUZXBpamxhbEI3VjEzMnFIYXR3U2o0Y0puaVU4amhRVGp3PT0=|1449580010|22d9dbb0761d2d713ee40aa33fb1f7f062dd5036")
			        .addCookie("__utmc", "51854390") ;

	
	@Override
	public void process(Page page) {
		//进入详细页进行进行抓取
		List<String> urls = page.getHtml().links().regex(TARGET_USER_BASE_INFO).all();
		for(String s:urls){
			if (StringUtils.isBlank(s) || s.equals("#") || s.startsWith("javascript:")) {
                continue;
            }
			page.addTargetRequest(s+"/about");
		}
		
		page.putField("pageUrl",page.getHtml().xpath("//div[@class='profile-navbar clearfix']/a[1]/@href").toString());
		page.putField("nickname",page.getHtml().xpath("//div[@class='title-section ellipsis']/span[1]/text()").toString());
		page.putField("business",page.getHtml().xpath("//span[@class='business item']/a/text()").toString());
		page.putField("employment",page.getHtml().xpath("//span[@class='employment item']/@title").toString());
		page.putField("position",page.getHtml().xpath("//span[@class='position item']/@title").toString());
		page.putField("collecters",page.getHtml().xpath("//div[@class='zm-profile-module-desc']/span[4]/strong/text()").toString());
		page.putField("shares",page.getHtml().xpath("//div[@class='zm-profile-module-desc']/span[5]/strong/text()").toString());
		page.putField("education",page.getHtml().xpath("//span[@class='education item']/a/text()").toString());
		page.putField("educationExtra",page.getHtml().xpath("//span[@class='education-extra item']/a/text()").toString());
		
		//状态
		String status = page.getHtml().xpath("//div[@class='zh-profile-account-status']/text()").toString();
		if(Strings.isNullOrEmpty(status))
			page.putField("status","active");
		else
			page.putField("status","noActive");
		
		//性别
		String gender = page.getHtml().xpath("//span[@class='item gender']/i/@class").toString();
		if(StringUtils.isEmpty(gender))
			page.putField("gender", "unknow");
		else if(gender.equals("icon icon-profile-male"))
			page.putField("gender", "male");
		else if(gender.equals("icon icon-profile-female"))
			page.putField("gender", "female");
		
		String lastMessageTime =page.getHtml().xpath("//span[@class='zm-profile-setion-time zg-gray zg-right']/text()").toString();
		if(!StringUtils.isEmpty(lastMessageTime)){
			String[] time = lastMessageTime.split(" ");
			DateTime dateTime = new DateTime();
			String resultTime= null;
			switch(time[1]){
			case "分钟前":
				resultTime = dateTime.plusMinutes(-Integer.valueOf(time[0])).toString();
				break;
			case "秒前":
				resultTime = dateTime.plusSeconds(-Integer.valueOf(time[0])).toString();
				break;
			case "小时前":
				resultTime = dateTime.plusHours(-Integer.valueOf(time[0])).toString();
				break;
			case "月前":
				resultTime = dateTime.plusMonths(-Integer.valueOf(time[0])).toString();
				break;
			case "周前":
				resultTime = dateTime.plusWeeks(-Integer.valueOf(time[0])).toString();
				break;
			case "年前":
				resultTime = dateTime.plusYears(-Integer.valueOf(time[0])).toString();
				break;
			}
			page.putField("lastMessageTime", resultTime);
	}
		
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		JedisPool pool = new JedisPool(new JedisPoolConfig(), "127.0.0.1");
		Spider.create(new UserDetailInfoProcessor()).addUrl(START_URL)
		.scheduler(new RedisScheduler(pool))
        .thread(1).run();
	 }

}
