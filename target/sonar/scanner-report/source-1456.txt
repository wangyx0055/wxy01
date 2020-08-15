package com.longersec.blj.domain;

public class OperatorLog {

	private Integer id;

	private String source_ip;

	private Integer user_id;

	private String username;

	private String realname;

	private Integer department_id;

	private Integer department;

	private String content;

	private String operate_datetime;

	private Integer status;

	private String result;

	private String module;

	private String details;

	private String searchAll;



	public OperatorLog() {
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

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperate_datetime() {
		return operate_datetime;
	}

	public void setOperate_datetime(String operate_datetime) {
		this.operate_datetime = operate_datetime;
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

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "OperatorLog{ "+
			",id=" + id +
			",source_ip=" + source_ip +
			",user_id=" + user_id +
			",username=" + username +
			",realname=" + realname +
			",department_id=" + department_id +
			",department=" + department +
			",content=" + content +
			",operate_datetime=" + operate_datetime +
			",status=" + status +
			",result=" + result +
			",module=" + module +
			",details=" + details +
			"";
	}

	public String getSearchAll() {
		return searchAll;
	}

	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
	}
	
	
}
