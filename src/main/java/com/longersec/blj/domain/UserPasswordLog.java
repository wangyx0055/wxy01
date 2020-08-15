package com.longersec.blj.domain;

public class UserPasswordLog {

	private Integer lsblj_user_id;
	private Integer create_time;
	String password;
	
	public Integer getLsblj_user_id() {
		return lsblj_user_id;
	}
	public void setLsblj_user_id(Integer lsblj_user_id) {
		this.lsblj_user_id = lsblj_user_id;
	}
	public Integer getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Integer create_time) {
		this.create_time = create_time;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
