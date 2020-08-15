package com.longersec.blj.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ConfigLogin {

	private Integer id;

	private Integer lock_type;

	@Max(value = 999, message = "失败尝试最大次数999")
	@Min(value = 0, message = "失败尝试最小次数0")
	private Integer lock_try_count;

	@Max(value = 10080, message = "最大值10080")
	@Min(value = 0, message = "最小值0")
	private Integer lock_time_length;

	private Integer lock_count_reset;
	private Integer lock_time;
	private Integer fail_count;
	private String lock_ip;

	private Integer password_verify;

	private Integer password_newuser_forcechange;

	@Max(value = 30, message = "最大值30")
	@Min(value = 1, message = "最小值1")
	private Integer password_verification_times;
	
	private String new_user_default_password;

	@Max(value = 365, message = "最大值365")
	@Min(value = 0, message = "最小值0")
	private Integer password_cycle;

	@Max(value = 65535, message = "最大值65535")
	@Min(value = 1, message = "最小值1")
	private Integer web_timeout;
	private Integer same_user;
	private String admin_login_ip;
	
	
	public Integer getLock_time() {
		return lock_time;
	}
	
	public void setLock_time(Integer lock_time) {
		this.lock_time = lock_time;
	}
	
	public Integer getFail_count() {
		return fail_count;
	}
	
	public void setFail_count(Integer fail_count) {
		this.fail_count = fail_count;
	}



	public String getLock_ip() {
		return lock_ip;
	}



	public void setLock_ip(String lock_ip) {
		this.lock_ip = lock_ip;
	}



	public String getAdmin_login_ip() {
		return admin_login_ip;
	}



	public void setAdmin_login_ip(String admin_login_ip) {
		this.admin_login_ip = admin_login_ip;
	}



	public ConfigLogin() {
		super();
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getLock_type() {
		return lock_type;
	}



	public void setLock_type(Integer lock_type) {
		this.lock_type = lock_type;
	}



	public Integer getLock_try_count() {
		return lock_try_count;
	}



	public void setLock_try_count(Integer lock_try_count) {
		this.lock_try_count = lock_try_count;
	}



	public Integer getLock_time_length() {
		return lock_time_length;
	}



	public void setLock_time_length(Integer lock_time_length) {
		this.lock_time_length = lock_time_length;
	}



	public Integer getLock_count_reset() {
		return lock_count_reset;
	}



	public void setLock_count_reset(Integer lock_count_reset) {
		this.lock_count_reset = lock_count_reset;
	}



	public Integer getPassword_verify() {
		return password_verify;
	}



	public void setPassword_verify(Integer password_verify) {
		this.password_verify = password_verify;
	}



	public Integer getPassword_newuser_forcechange() {
		return password_newuser_forcechange;
	}



	public void setPassword_newuser_forcechange(Integer password_newuser_forcechange) {
		this.password_newuser_forcechange = password_newuser_forcechange;
	}



	public Integer getPassword_verification_times() {
		return password_verification_times;
	}



	public void setPassword_verification_times(Integer password_verification_times) {
		this.password_verification_times = password_verification_times;
	}
	
	
	public String getNew_user_default_password() {
		return new_user_default_password;
	}



	public void setNew_user_default_password(String new_user_default_password) {
		this.new_user_default_password = new_user_default_password;
	}



	public Integer getPassword_cycle() {
		return password_cycle;
	}



	public void setPassword_cycle(Integer password_cycle) {
		this.password_cycle = password_cycle;
	}



	public Integer getWeb_timeout() {
		return web_timeout;
	}



	public void setWeb_timeout(Integer web_timeout) {
		this.web_timeout = web_timeout;
	}

	public Integer getSame_user() {
		return same_user;
	}

	public void setSame_user(Integer same_user) {
		this.same_user = same_user;
	}

	@Override
	public String toString() {

		return "ConfigLogin [id=" + id
				+ ", lock_type=" + lock_type 
				+ ", lock_try_count=" + lock_try_count
				+ ", lock_time_length=" + lock_time_length 
				+ ", lock_count_reset=" + lock_count_reset
				+ ", password_verify=" + password_verify
				+ ", password_newuser_forcechange="+ password_newuser_forcechange
				+ ", password_verification_times=" + password_verification_times
				+ ", new_user_default_password=" + new_user_default_password
				+ ", password_cycle=" + password_cycle
				+ ", admin_login_ip=" + admin_login_ip 
				+ ", web_timeout=" + web_timeout
				+ ", same_user=" + same_user + "]";


	}


}
