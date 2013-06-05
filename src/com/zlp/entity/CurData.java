package com.zlp.entity;

public class CurData {

	private String location;
	private String pname;
	private String count;

	public CurData(String location, String pname, String count) {
		this.location = location;
		this.pname = pname;
		this.count = count;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
