package com.ming.zhihuWebSpider;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.alibaba.fastjson.JSON;
import com.ming.zhihuWebSpider.mapping.UserBaseInfoMapper;
import com.ming.zhihuWebSpider.mapping.UserDetailInfoMapper;
import com.ming.zhihuWebSpider.model.UserDetailInfo;
import com.ming.zhihuWebSpider.serviceI.UserBaseInfoServiceI;


/**
 * Unit test for simple App.
 */
public class AppTest    extends AbstractServiceTests
{
	
	@Autowired
	private UserBaseInfoMapper userBaseInfoMapper;
	
	@Autowired
	private UserDetailInfoMapper userDetailInfoMapper;

	@Autowired
	private UserBaseInfoServiceI userBaseInfoServiceI;
	
     @Test
     public void test_sellectAll(){
    	 logger.info(JSON.toJSONString(userBaseInfoMapper.selectAll()));
     }
     
     @Test
     public void test_selectFunction(){
    	 logger.info(JSON.toJSONString(userBaseInfoServiceI.getBaseUsersAccount()));
    	 logger.info(JSON.toJSONString(userBaseInfoMapper.getLocationStatic(10)));
    	 
    	 logger.info(JSON.toJSONString(userDetailInfoMapper.getBusinessStatic(10)));
    	 logger.info(JSON.toJSONString(userDetailInfoMapper.getEducationStatic(10)));
    	 logger.info(JSON.toJSONString(userDetailInfoMapper.getEmploymentStatic(10)));
     }
     @Test @Rollback(false)
     public void test_insertUserDetailInfo(){
    	 UserDetailInfo record = new UserDetailInfo();
    	 record.setPageurl("pageUrl");
    	 record.setNickname("nickname");
    	 record.setBusiness("business");
    	 record.setShares(5);
    	 record.setStatus("active");
    	 userDetailInfoMapper.insertSelective(record);
     }
     
}
