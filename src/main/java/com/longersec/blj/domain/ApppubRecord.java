package com.longersec.blj.domain;

public class ApppubRecord {

	private Integer id;

	private String client_ip;

	private String server_ip;

	private String start;

	private String end;

	private String log_file;

	private String video_file;

	private Integer protocol_id;

	private Integer port;

	private Integer user_id;

	private Integer apppub_account_id;

	private String name;

	private String program;

	private String programpath;

	private String username;

	private String realname;

	private String apppub_username;

	private Integer status;

	private Integer time_length;

	private Integer access_parameter;

	private Integer log_file_size;

	private Integer videofile_size;

	private String client_mac;

	private String searchAll;


	public String getSearchAll() {
		return searchAll;
	}

	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
	}

	public ApppubRecord() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public String getServer_ip() {
		return server_ip;
	}

	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getLog_file() {
		return log_file;
	}

	public void setLog_file(String log_file) {
		this.log_file = log_file;
	}
	
	public String getVideo_file() {
		return video_file;
	}

	public void setVideo_file(String video_file) {
		this.video_file = video_file;
	}

	public Integer getProtocol_id() {
		return protocol_id;
	}

	public void setProtocol_id(Integer protocol_id) {
		this.protocol_id = protocol_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getApppub_account_id() {
		return apppub_account_id;
	}

	public void setApppub_account_id(Integer apppub_account_id) {
		this.apppub_account_id = apppub_account_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
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

	public String getApppub_username() {
		return apppub_username;
	}

	public void setApppub_username(String apppub_username) {
		this.apppub_username = apppub_username;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTime_length() {
		return time_length;
	}

	public void setTime_length(Integer time_length) {
		this.time_length = time_length;
	}

	public Integer getAccess_parameter() {
		return access_parameter;
	}

	public void setAccess_parameter(Integer access_parameter) {
		this.access_parameter = access_parameter;
	}
	
	public Integer getLog_file_size() {
		return log_file_size;
	}

	public void setLog_file_size(Integer log_file_size) {
		this.log_file_size = log_file_size;
	}

	public Integer getVideofile_size() {
		return videofile_size;
	}

	public void setVideofile_size(Integer videofile_size) {
		this.videofile_size = videofile_size;
	}

	public String getClient_mac() {
		return client_mac;
	}

	public void setClient_mac(String client_mac) {
		this.client_mac = client_mac;
	}
	
	public String getProgrampath() {
		return programpath;
	}

	public void setProgrampath(String programpath) {
		this.programpath = programpath;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "ApppubRecord{" +
				"id=" + id +
				", client_ip='" + client_ip + '\'' +
				", server_ip='" + server_ip + '\'' +
				", start=" + start +
				", end=" + end +
				", log_file='" + log_file + '\'' +
				", protocol_id=" + protocol_id +
				", user_id=" + user_id +
				", apppub_account_id=" + apppub_account_id +
				", name='" + name + '\'' +
				", program='" + program + '\'' +
				", username='" + username + '\'' +
				", realname='" + realname + '\'' +
				", apppub_username='" + apppub_username + '\'' +
				", status=" + status +
				", time_length=" + time_length +
				", access_parameter=" + access_parameter +
				", log_file_size='" + log_file_size + '\'' +
				", client_mac='" + client_mac + '\'' +
				'}';
	}
}
