package com.ming.zhihuWebSpider;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.entity.Example;

import com.alibaba.fastjson.JSON;
import com.ming.zhihuWebSpider.mapping.UserBaseInfoMapper;
import com.ming.zhihuWebSpider.mapping.UserDetailInfoMapper;
import com.ming.zhihuWebSpider.model.UserBaseInfo;
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
     public void test_selleAmountByProperty(){
    	 UserBaseInfo record = new UserBaseInfo();
    	 System.out.println("基本信息人数    "+userBaseInfoMapper.selectCount(record));
    	 
    	 UserDetailInfo record2 = new UserDetailInfo();
    	 System.out.println("详细信息人数     "+userDetailInfoMapper.selectCount(record2));
    	 
    	 UserDetailInfo record4 = new UserDetailInfo();
    	 record4.setGender("icon icon-profile-male");
    	 System.out.println("男      "+userDetailInfoMapper.selectCount(record4));
    	 
    	 UserDetailInfo record5 = new UserDetailInfo();
    	 record5.setGender("icon icon-profile-female");
    	 System.out.println("女      "+userDetailInfoMapper.selectCount(record5));
    	 
    	 Example example = new Example(UserDetailInfo.class);
    	 example.createCriteria().andIsNull("gender");
    	 System.out.println("为空的  "+userDetailInfoMapper.selectCountByExample(example));
    	 
    	 Example example1 = new Example(UserDetailInfo.class);
    	 example1.createCriteria().andIsNotNull("status");
    	 System.out.println("为空的  "+userDetailInfoMapper.selectCountByExample(example1));
     }
     
     @Test
     public void test_selectLike(){
    	 String searchName = "波波熊";
    	 Example example1 = new Example(UserBaseInfo.class);
    	 example1.selectProperties("nickname","location","weiboUrl","headline","description");
    	 example1.createCriteria().andLike("nickname", searchName);
    	 List<UserBaseInfo> result = (List<UserBaseInfo>) userBaseInfoMapper.selectByExample(example1);
    	 System.out.println("查找昵称为"+searchName+"结果为 "+JSON.toJSONString(result));
    	 
     }
     @Test
     public void test_selectFunction(){
    	 logger.info("人群地域分布"+JSON.toJSONString(userBaseInfoMapper.getLocationStatic(10)));
    	 logger.info("行业分布钱10"+JSON.toJSONString(userDetailInfoMapper.getBusinessStatic(10)));
    	 logger.info("教育背景分布前10"+JSON.toJSONString(userDetailInfoMapper.getEducationStatic(10)));
    	 logger.info("工作分布前10"+JSON.toJSONString(userDetailInfoMapper.getEmploymentStatic(10)));
     }
     
     @Test  
     public void test_insertUserDetailInfo(){
    	 UserDetailInfo record = new UserDetailInfo();
    	 record.setPageurl("aaaaaaaaaaa");
    	 record.setNickname("nickname");
    	 record.setBusiness("business");
    	 record.setShares(5);
    	 record.setStatus("active");
    	 userDetailInfoMapper.insertSelective(record);
     }
     
     private void updateLastMessageTime(UserBaseInfo userBaseInfo){
    	 //userDetailInfoMapper.se
    	 String lastMessageTime = userBaseInfo.getLastdynamic();
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
    	 }
     }
}