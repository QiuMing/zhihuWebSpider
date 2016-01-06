package com.ming.zhihuWebSpider.pipeline;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import com.ming.zhihuWebSpider.mapping.UserDetailInfoMapper;
import com.ming.zhihuWebSpider.model.UserDetailInfo;
@Component
public class UserDetailInfoPipeline implements Pipeline {

	@Autowired
	private UserDetailInfoMapper userDetailInfoMapper;

	@Override
	public void process(ResultItems resultItems, Task task) {
		UserDetailInfo record = new UserDetailInfo();
		for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
	        //System.out.println(entry.getKey() + ":\t" + entry.getValue());
			if (null != entry.getValue()) {
				switch (entry.getKey()) {
				case "pageUrl":
					record.setPageurl(entry.getValue().toString());
					break;
				case "nickname":
					record.setNickname(entry.getValue().toString());
					break;
				case "business":
					record.setBusiness(entry.getValue().toString());
					break;
				case "employment":
					record.setEmployment(entry.getValue().toString());
					break;
				case "position":
					record.setPosition(entry.getValue().toString());
					break;
				case "gender":
					record.setGender(entry.getValue().toString());
					break;
				case "shares":
					record.setShares(Integer.valueOf((String) entry.getValue()));
					break;
				case "collecters":
					record.setCollecters(Integer.valueOf((String) entry.getValue()));
					break;
				case "education":
					record.setEducation(entry.getValue().toString());
					break;
				case "educationExtra":
					record.setEducationextra(entry.getValue().toString());
					break;
				case "status":
					record.setStatus(entry.getValue().toString());
					break;
				default:
					break;
				}
			}
		}
		if(record!=null){
			userDetailInfoMapper.insertSelective(record);
		}
	}
}
