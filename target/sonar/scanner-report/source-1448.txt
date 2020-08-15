package com.longersec.blj.domain;

public class LiveSessions {

	private Integer id;

	private String device_name;

	private String device_ip;

	private String protocol;

	private String device_user;

	private String client_ip;

	private String username;

	private String realname;

	private String start;

	private String app_name;

	private String program;

	private String app_account;

	private String uuid;

	private String authtoken;

	private Integer record_id;

	private String monitorkey;
	
	private Integer connectid;

	private String searchAll;



	public LiveSessions() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_ip() {
		return device_ip;
	}

	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getDevice_user() {
		return device_user;
	}

	public void setDevice_user(String device_user) {
		this.device_user = device_user;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getApp_account() {
		return app_account;
	}

	public void setApp_account(String app_account) {
		this.app_account = app_account;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAuthtoken() {
		return authtoken;
	}

	public void setAuthtoken(String authtoken) {
		this.authtoken = authtoken;
	}

	public Integer getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}

	public String getMonitorkey() {
		return monitorkey;
	}

	public void setMonitorkey(String monitorkey) {
		this.monitorkey = monitorkey;
	}

	public Integer getConnectid() {
		return connectid;
	}

	public void setConnectid(Integer connectid) {
		this.connectid = connectid;
	}

	@Override
	public String toString() {
		return "LiveSessions{ "+
			",id=" + id +
			",device_name=" + device_name +
			",device_ip=" + device_ip +
			",protocol=" + protocol +
			",device_user=" + device_user +
			",client_ip=" + client_ip +
			",username=" + username +
			",realname=" + realname +
			",start=" + start +
			",app_name=" + app_name +
			",program=" + program +
			",app_account=" + app_account +
			",uuid=" + uuid +
			",authtoken=" + authtoken +
			",record_id=" + record_id +
			",monitorkey=" + monitorkey +
			"";
	}

	public String getSearchAll() {
		return searchAll;
	}

	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
	}
	
	
}
