package com.zlp.entity;

/**
 * 商品实体
 * 
 * @author zouliping
 * 
 */
public class Production {

	private int picRes;
	private String name;
	private String desc;

	public Production(int picRes, String name, String desc) {
		this.picRes = picRes;
		this.name = name;
		this.desc = desc;
	}

	public int getPicRes() {
		return picRes;
	}

	public void setPicRes(int picRes) {
		this.picRes = picRes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
