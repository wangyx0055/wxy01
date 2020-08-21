package com.longersec.blj.domain;

public class DeviceAccount {

	private Integer id;

	private String username;

	private String password;

	private Integer device_id;

	private Integer port;

	private Integer protocol_id;

	private Integer is_super;

	private Integer ssh_key;

	private Integer status;

	private Integer login_method;

	private String ip;

	private String device_name;

	private String groupname;

	private String typename;
	
	private String protocolname;
	
	private String searchAll;
	
	private String device_username;

	private String script_name;

	private Integer file_id;

	private String ssh_password;
	public DeviceAccount() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDevice_id() {
		return device_id;
	}

	public void setDevice_id(Integer device_id) {
		this.device_id = device_id;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getProtocol_id() {
		return protocol_id;
	}

	public void setProtocol_id(Integer protocol_id) {
		this.protocol_id = protocol_id;
	}

	public Integer getIs_super() {
		return is_super;
	}

	public void setIs_super(Integer is_super) {
		this.is_super = is_super;
	}

	public Integer getSsh_key() {
		return ssh_key;
	}

	public void setSsh_key(Integer ssh_key) {
		this.ssh_key = ssh_key;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLogin_method() {
		return login_method;
	}

	public void setLogin_method(Integer login_method) {
		this.login_method = login_method;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getProtocolname() {
		return protocolname;
	}

	public void setProtocolname(String protocolname) {
		this.protocolname = protocolname;
	}

	public String getSearchAll() {
		return searchAll;
	}

	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
	}

	public String getDevice_username() {
		return device_username;
	}

	public void setDevice_username(String device_username) {
		this.device_username = device_username;
	}

	public String getScript_name() {
		return script_name;
	}

	public void setScript_name(String script_name) {
		this.script_name = script_name;
	}

	public Integer getFile_id() {
		return file_id;
	}

	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}

	public String getSsh_password() {
		return ssh_password;
	}

	public void setSsh_password(String ssh_password) {
		this.ssh_password = ssh_password;
	}

	@Override
	public String toString() {
		return "DeviceAccount{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", device_id=" + device_id +
				", port=" + port +
				", protocol_id=" + protocol_id +
				", is_super=" + is_super +
				", ssh_key=" + ssh_key +
				", status=" + status +
				", login_method=" + login_method +
				", ip='" + ip + '\'' +
				", device_name='" + device_name + '\'' +
				", groupname='" + groupname + '\'' +
				", typename='" + typename + '\'' +
				", protocolname='" + protocolname + '\'' +
				", searchAll='" + searchAll + '\'' +
				", device_username='" + device_username + '\'' +
				", script_name='" + script_name + '\'' +
				", file_id=" + file_id +
				", ssh_password='" + ssh_password + '\'' +
				'}';
	}
}
