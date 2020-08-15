package com.longersec.blj.domain;

public class GConnectionHistory {

	private Integer history_id;

	private Integer user_id;

	private String username;

	private String remote_host;

	private Integer connection_id;

	private String connection_name;

	private Integer sharing_profile_id;

	private String sharing_profile_name;

	private String start_date;

	private String end_date;



	public GConnectionHistory() {
		super();
	}

	public Integer getHistory_id() {
		return history_id;
	}

	public void setHistory_id(Integer history_id) {
		this.history_id = history_id;
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

	public String getRemote_host() {
		return remote_host;
	}

	public void setRemote_host(String remote_host) {
		this.remote_host = remote_host;
	}

	public Integer getConnection_id() {
		return connection_id;
	}

	public void setConnection_id(Integer connection_id) {
		this.connection_id = connection_id;
	}

	public String getConnection_name() {
		return connection_name;
	}

	public void setConnection_name(String connection_name) {
		this.connection_name = connection_name;
	}

	public Integer getSharing_profile_id() {
		return sharing_profile_id;
	}

	public void setSharing_profile_id(Integer sharing_profile_id) {
		this.sharing_profile_id = sharing_profile_id;
	}

	public String getSharing_profile_name() {
		return sharing_profile_name;
	}

	public void setSharing_profile_name(String sharing_profile_name) {
		this.sharing_profile_name = sharing_profile_name;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	@Override
	public String toString() {
		return "GConnectionHistory{ "+
			",history_id=" + history_id +
			",user_id=" + user_id +
			",username=" + username +
			",remote_host=" + remote_host +
			",connection_id=" + connection_id +
			",connection_name=" + connection_name +
			",sharing_profile_id=" + sharing_profile_id +
			",sharing_profile_name=" + sharing_profile_name +
			",start_date=" + start_date +
			",end_date=" + end_date +
			"";
	}
}
