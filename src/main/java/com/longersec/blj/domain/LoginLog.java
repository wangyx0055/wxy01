package com.longersec.blj.domain;

public class LoginLog {

	private Integer id;

	private String source_ip;

	private Integer user_id;

	private String username;

	private String realname;

	private Integer department;

	private String protocol;

	private String device_ip;

	private String os;

	private String browser;

	private String login_datetime;

	private Integer status;

	private String result;

	private String details;

	private String searchAll;
	
	private String login_type;
	

	public String getLogin_type() {
		return login_type;
	}

	public void setLogin_type(String login_type) {
		this.login_type = login_type;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public LoginLog() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSource_ip() {
		return source_ip;
	}

	public void setSource_ip(String source_ip) {
		this.source_ip = source_ip;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}


	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getDevice_ip() {
		return device_ip;
	}

	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getLogin_datetime() {
		return login_datetime;
	}

	public void setLogin_datetime(String login_datetime) {
		this.login_datetime = login_datetime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getSearchAll() {
		return searchAll;
	}

	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
	}

	@Override
	public String toString() {
		return "LoginLog [id=" + id + ", source_ip=" + source_ip + ", user_id=" + user_id + ", username=" + username
				+ ", realname=" + realname + ", department=" + department + ", protocol=" + protocol + ", device_ip="
				+ device_ip + ", os=" + os + ", browser=" + browser + ", login_datetime=" + login_datetime + ", status="
				+ status + ", result=" + result + ", details=" + details + ", searchAll=" + searchAll + ", login_type="
				+ login_type + "]";
	}
	
}
