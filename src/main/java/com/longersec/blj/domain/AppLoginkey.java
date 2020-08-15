package com.longersec.blj.domain;

public class AppLoginkey {

	private Integer id;

	private String loginkey;

	private Integer userid;

	private Integer logintime;



	public AppLoginkey() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginkey() {
		return loginkey;
	}

	public void setLoginkey(String loginkey) {
		this.loginkey = loginkey;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getLogintime() {
		return logintime;
	}

	public void setLogintime(Integer logintime) {
		this.logintime = logintime;
	}

	@Override
	public String toString() {
		return "AppLoginkey{ "+
			",id=" + id +
			",loginkey=" + loginkey +
			",userid=" + userid +
			",logintime=" + logintime +
			"";
	}
}
