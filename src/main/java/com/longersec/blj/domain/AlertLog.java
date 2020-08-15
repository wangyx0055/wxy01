package com.longersec.blj.domain;

public class AlertLog {

	private Integer id;

	private String source_ip;

	private Integer user_id;

	private String username;

	private String realname;

	private String device_ip;

	private String device_account;

	private String protocol;

	private String command;

	private String operate_datetime;

	private Integer isemail;

	private String operational_group;

	private String alert_times;
	
	private Integer policy_id;
	
	private Integer dangerlevel;

	private String policy;

	private String searchAll;

	public AlertLog() {
		super();
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public String getSearchAll() {
		return searchAll;
	}

	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
	}

	public Integer getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}

	public Integer getDangerlevel() {
		return dangerlevel;
	}

	public void setDangerlevel(Integer dangerlevel) {
		this.dangerlevel = dangerlevel;
	}

	public String getProtocol() {
		return protocol;
	}


	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Integer getIsemail() {
		return isemail;
	}

	public void setIsemail(Integer isemail) {
		this.isemail = isemail;
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

	public String getDevice_ip() {
		return device_ip;
	}

	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}

	public String getDevice_account() {
		return device_account;
	}

	public void setDevice_account(String device_acount) {
		this.device_account = device_acount;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getOperational_group() {
		return operational_group;
	}

	public void setOperational_group(String operational_group) {
		this.operational_group = operational_group;
	}

	public String getAlert_times() {
		return alert_times;
	}

	public void setAlert_times(String alert_times) {
		this.alert_times = alert_times;
	}

	public String getOperate_datetime() {
		return operate_datetime;
	}

	public void setOperate_datetime(String operate_datetime) {
		this.operate_datetime = operate_datetime;
	}

	@Override
	public String toString() {
		return "AlertLog [id=" + id + ", source_ip=" + source_ip + ", user_id=" + user_id + ", username=" + username
				+ ", realname=" + realname + ", device_ip=" + device_ip + ", device_account=" + device_account
				+ ", protocol=" + protocol + ", command=" + command + ", operate_datetime=" + operate_datetime
				+ ", isemail=" + isemail + ", operational_group=" + operational_group + ", alert_times=" + alert_times
				+ ", policy_id=" + policy_id + ", dangerlevel=" + dangerlevel + ", policy=" + policy + ", searchAll="
				+ searchAll + "]";
	}
}
