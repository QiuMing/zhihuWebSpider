package com.ming.zhihuWebSpider.model.extend;

public class GenderInfo{
	String name;
	Integer value;
	
	public GenderInfo(String name, Integer value) {
		super();
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
	
}