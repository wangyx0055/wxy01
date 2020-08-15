package com.longersec.blj.domain;

public class Syslog {

	private Integer id;

	private String user_id;

	private String username;

	private String client_ip;

	private Integer code;

	private Integer log_time;

	private String message;

	private String detail;



	public Syslog() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getLog_time() {
		return log_time;
	}

	public void setLog_time(Integer log_time) {
		this.log_time = log_time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Syslog{ "+
			",id=" + id +
			",user_id=" + user_id +
			",username=" + username +
			",client_ip=" + client_ip +
			",code=" + code +
			",log_time=" + log_time +
			",message=" + message +
			",detail=" + detail +
			"";
	}
}
