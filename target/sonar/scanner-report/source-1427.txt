package com.longersec.blj.domain;

public class DeviceRecord {

	private Integer id;

	private String client_ip;

	private String server_ip;

	private String device_ip;

	private String device_name;

	private Integer port;

	private String start;

	private String end;

	private Integer protocol_id;

	private String protocol_name;

	private Integer user_id;

	private String username;

	private String realname;

	private String device_username;

	private Integer time_length;

	private String type;

	private Integer status;

	private String log_file;

	private Integer log_file_size;

	private String video_file;

	private Integer video_file_size;

	private String client_mac;

	private Integer auth_type;

	private String protocol;

	private String command;
	
	private String searchAll;



	public DeviceRecord() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
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

	public String getDevice_ip() {
		return device_ip;
	}

	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
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

	public String getDevice_username() {
		return device_username;
	}

	public void setDevice_username(String device_username) {
		this.device_username = device_username;
	}

	public Integer getTime_length() {
		return time_length;
	}

	public void setTime_length(Integer time_length) {
		this.time_length = time_length;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLog_file() {
		return log_file;
	}

	public void setLog_file(String log_file) {
		this.log_file = log_file;
	}

	public Integer getLog_file_size() {
		return log_file_size;
	}

	public void setLog_file_size(Integer log_file_size) {
		this.log_file_size = log_file_size;
	}

	public String getVideo_file() {
		return video_file;
	}

	public void setVideo_file(String video_file) {
		this.video_file = video_file;
	}

	public Integer getVideo_file_size() {
		return video_file_size;
	}

	public void setVideo_file_size(Integer video_file_size) {
		this.video_file_size = video_file_size;
	}

	public String getClient_mac() {
		return client_mac;
	}

	public void setClient_mac(String client_mac) {
		this.client_mac = client_mac;
	}

	public Integer getAuth_type() {
		return auth_type;
	}

	public void setAuth_type(Integer auth_type) {
		this.auth_type = auth_type;
	}


	@Override
	public String toString() {
		return "DeviceRecord{" +
				"id=" + id +
				", client_ip='" + client_ip + '\'' +
				", server_ip='" + server_ip + '\'' +
				", device_ip='" + device_ip + '\'' +
				", device_name='" + device_name + '\'' +
				", port=" + port +
				", start='" + start + '\'' +
				", end='" + end + '\'' +
				", protocol_id=" + protocol_id +
				", protocol_name='" + protocol_name + '\'' +
				", user_id=" + user_id +
				", username='" + username + '\'' +
				", realname='" + realname + '\'' +
				", device_username='" + device_username + '\'' +
				", time_length=" + time_length +
				", type='" + type + '\'' +
				", status=" + status +
				", log_file='" + log_file + '\'' +
				", log_file_size=" + log_file_size +
				", video_file='" + video_file + '\'' +
				", video_file_size=" + video_file_size +
				", client_mac='" + client_mac + '\'' +
				", auth_type=" + auth_type +
				'}';
	}

	public String getProtocol_name() {
		return protocol_name;
	}

	public void setProtocol_name(String protocol_name) {
		this.protocol_name = protocol_name;
	}

	public String getSearchAll() {
		return searchAll;
	}

	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
	}
	
	
}
