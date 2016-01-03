package com.ming.zhihuWebSpider.mapping;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.ming.zhihuWebSpider.model.UserBaseInfo;

public interface UserBaseInfoMapper extends Mapper<UserBaseInfo> {
	List<UserBaseInfo> getLocationStatic(Integer selectLimitAmount);
	
	int getAmountByReocordItem(UserBaseInfo record);
}