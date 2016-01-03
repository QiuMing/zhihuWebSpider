package com.ming.zhihuWebSpider.mapping;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.ming.zhihuWebSpider.model.UserDetailInfo;

public interface UserDetailInfoMapper extends Mapper<UserDetailInfo> {
	List<UserDetailInfo> getBusinessStatic(Integer selectLimitAmount);
	
	List<UserDetailInfo> getEmploymentStatic(Integer selectLimitAmount);
	
	List<UserDetailInfo> getEducationStatic(Integer selectLimitAmount);
}