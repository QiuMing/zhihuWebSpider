package com.ming.zhihuWebSpider.model.extend;

public  class BarInfoData{
	
	Integer[] yAxis;
	
	String[]  sXAxis;

	public BarInfoData(Integer[] yAxis, String[] sXAxis) {
		super();
		this.yAxis = yAxis;
		this.sXAxis = sXAxis;
	}

	public Integer[] getyAxis() {
		return yAxis;
	}
	public void setyAxis(Integer[] yAxis) {
		this.yAxis = yAxis;
	}
	public String[] getsXAxis() {
		return sXAxis;
	}
	public void setsXAxis(String[] sXAxis) {
		this.sXAxis = sXAxis;
	}
	
}