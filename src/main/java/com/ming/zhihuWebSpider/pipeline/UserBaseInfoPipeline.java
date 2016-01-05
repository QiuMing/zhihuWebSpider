package com.ming.zhihuWebSpider.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import com.ming.zhihuWebSpider.mapping.UserBaseInfoMapper;
import com.ming.zhihuWebSpider.model.UserBaseInfo;

@Component("UserBaseInfoPipeline")
public class UserBaseInfoPipeline implements PageModelPipeline<UserBaseInfo> {

	@Autowired
	private UserBaseInfoMapper userBaseInfoMapper;
 
	@Override
	public void process(UserBaseInfo t, Task task) {
		userBaseInfoMapper.insertSelective(t);
	}

}
