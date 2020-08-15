package com.longersec.blj.domain;

public class SysSession {

	private String id;

	private Integer refresh_time;

	private String session;

	private Integer userid;

	private String searchAll;
	
	private String username;
	
	private String realname;
	
	private String client_ip;



	public SysSession() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getRefresh_time() {
		return refresh_time;
	}

	public void setRefresh_time(Integer refresh_time) {
		this.refresh_time = refresh_time;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "SysSession{ "+
			",id=" + id +
			",refresh_time=" + refresh_time +
			",session=" + session +
			",userid=" + userid +
			"";
	}

	public String getSearchAll() {
		return searchAll;
	}

	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
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

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	
	
}
