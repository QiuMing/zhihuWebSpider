package com.ming.zhihuWebSpider.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ming.zhihuWebSpider.mapping.UserBaseInfoMapper;
import com.ming.zhihuWebSpider.mapping.UserDetailInfoMapper;
import com.ming.zhihuWebSpider.model.UserBaseInfo;
import com.ming.zhihuWebSpider.model.UserDetailInfo;

@Controller
public class BaseController {

	@Autowired
	private UserBaseInfoMapper userBaseInfoMapper;
	
	@Autowired
	private UserDetailInfoMapper userDetailInfoMapper;
	
	@RequestMapping("zhihu")
	public String showPage(ModelMap model) {
		System.out.println("----------");
		model.put("test", "test");
		return "zhihu";
		
	}
	
	enum Gender{
		female,male,unknow
	}
	public int geAmountByGender(String gender){
		UserDetailInfo record = new UserDetailInfo();
		if(gender.equals(Gender.female.toString()))
			record.setGender(Gender.female.toString());
		else if(gender.equals(Gender.male.toString()))
			record.setGender(Gender.male.toString());
		else
			record.setGender(null);
		
		return userDetailInfoMapper.selectCount(record);	
	}
	
	public List<UserBaseInfo> getUserBaseInfo(){
		ArrayList<UserBaseInfo> results = new ArrayList<UserBaseInfo>();
		return results;
		
	}
	public int getDetailUsersAccount(){
		UserDetailInfo record = new UserDetailInfo();
		record.setDetailUserId(11);
		return userDetailInfoMapper.selectCount(record);
	}
	
}
