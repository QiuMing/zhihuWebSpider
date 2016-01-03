package com.ming.zhihuWebSpider.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.ming.zhihuWebSpider.mapping.UserDetailInfoMapper;
import com.ming.zhihuWebSpider.model.UserDetailInfo;

public class UserDetailInfoServiceImpl {

	@Autowired
	private UserDetailInfoMapper userDetailInfoMapper;

	public int getBaseUsersAccount(){
		UserDetailInfo record = new UserDetailInfo();
		record.setDetailUserId(1);
		System.out.println(JSON.toJSONString(record));
		return userDetailInfoMapper.selectCount(record);
	}

}
