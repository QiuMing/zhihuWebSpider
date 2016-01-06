package com.ming.zhihuWebSpider.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import com.ming.zhihuWebSpider.mapping.UserDetailInfoMapper;
import com.ming.zhihuWebSpider.model.UserDetailInfo;
@Component("UserDetailInfoPipeline2")
public class UserDetailInfoPipeline2 implements PageModelPipeline<UserDetailInfo> {
	
	@Autowired
	private UserDetailInfoMapper userDetailInfoMapper;
	@Override
	public void process(UserDetailInfo t, Task task) {
		// TODO Auto-generated method stub
		userDetailInfoMapper.insertSelective(t);
	}

}