package com.ming.zhihuWebSpider.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import com.ming.zhihuWebSpider.mapping.UserDetailInfoMapper;
import com.ming.zhihuWebSpider.model.UserDetailInfo;


/**
 * @Description TODO
 * @author Ming
 * @date: 2016年1月27日--上午9:42:23
 * 采用了注解方式进行数据爬取，因此结合mybatis这一ORM 映射框架 可以快速的插入
 */
@Component("UserDetailInfoPipeline2")
public class UserDetailInfoPipeline2 implements PageModelPipeline<UserDetailInfo> {
	
	@Autowired
	private UserDetailInfoMapper userDetailInfoMapper;
	@Override
	public void process(UserDetailInfo t, Task task) {
		//System.out.println(JSON.toJSONString(t));
		userDetailInfoMapper.insertSelective(t);
	}

}