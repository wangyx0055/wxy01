package com.longersec.blj.domain;

public class ChangePasswordLog {

	private Integer id;

	private String exec_datetime;

	private String device_ip;

	private Integer device_port;

	private String device_username;

	private String device_name;

	private String update_success_flag;

	private String password;

	private String old_password;

	private String end_datetime;

	private String change_pa;

	private Integer policy_id;

	private String description;

	private String policy_name;



	public ChangePasswordLog() {
		super();
	}
	

	public String getPolicy_name() {
		return policy_name;
	}


	public void setPolicy_name(String policy_name) {
		this.policy_name = policy_name;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExec_datetime() {
		return exec_datetime;
	}

	public void setExec_datetime(String exec_datetime) {
		this.exec_datetime = exec_datetime;
	}

	public String getDevice_ip() {
		return device_ip;
	}

	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}

	public Integer getDevice_port() {
		return device_port;
	}

	public void setDevice_port(Integer device_port) {
		this.device_port = device_port;
	}

	public String getDevice_username() {
		return device_username;
	}

	public void setDevice_username(String device_username) {
		this.device_username = device_username;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getUpdate_success_flag() {
		return update_success_flag;
	}

	public void setUpdate_success_flag(String update_success_flag) {
		this.update_success_flag = update_success_flag;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOld_password() {
		return old_password;
	}

	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}

	public String getEnd_datetime() {
		return end_datetime;
	}

	public void setEnd_datetime(String end_datetime) {
		this.end_datetime = end_datetime;
	}

	public String getChange_pa() {
		return change_pa;
	}

	public void setChange_pa(String change_pa) {
		this.change_pa = change_pa;
	}

	public Integer getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ChangePasswordLog{ "+
			",id=" + id +
			",exec_datetime=" + exec_datetime +
			",device_ip=" + device_ip +
			",device_port=" + device_port +
			",device_username=" + device_username +
			",device_name=" + device_name +
			",update_success_flag=" + update_success_flag +
			",password=" + password +
			",old_password=" + old_password +
			",end_datetime=" + end_datetime +
			",change_pa=" + change_pa +
			",policy_id=" + policy_id +
			",description=" + description +
			"";
	}
}
