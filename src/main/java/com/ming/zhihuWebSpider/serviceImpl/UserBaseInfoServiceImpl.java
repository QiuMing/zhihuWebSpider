package com.ming.zhihuWebSpider.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ming.zhihuWebSpider.mapping.UserBaseInfoMapper;
import com.ming.zhihuWebSpider.model.UserBaseInfo;
import com.ming.zhihuWebSpider.serviceI.UserBaseInfoServiceI;
@Service
public class UserBaseInfoServiceImpl implements UserBaseInfoServiceI {

	@Autowired
	private UserBaseInfoMapper userBaseInfoMapper;

	public int getBaseUsersAccount(){
		UserBaseInfo record = new UserBaseInfo();
		record.setId(11);
		return userBaseInfoMapper.selectCount(record);
	}
	
	/*public List<UserBaseInfo> get(){
		
	}*/
	
}
