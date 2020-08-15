package com.longersec.blj.domain;

import javax.validation.constraints.NotNull;

public class Device {

	private Integer id;

	private String searchAll;
	
	@NotNull(message = "协议类型不能为空")
	private Integer os_type;

	private String os_ver;

	@NotNull(message = "设备名不能为空")
	private String name;
	
	@NotNull(message = "IP不能为空")
	private String ip;
	
	@NotNull(message = "端口不能为空")
	private Integer port;
	
	private String ipv6;

	private String super_account;

	private String super_password;

	private Integer status;

	private Integer account_count;

	private Integer groupid;

	private String description;

	private Integer creator_id;

	private Integer create_time;
	
	@NotNull(message = "登录方式不能为空")
	private Integer login_method;

	private Integer protocol_id;


	private Integer department;

	private String depart_name;

	private String topName;

	private Integer ssh_key;
	
	private String groupname;

	private String protocolname;
	private String typename;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSearchAll() {
		return searchAll;
	}

	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
	}

	public Integer getOs_type() {
		return os_type;
	}

	public void setOs_type(Integer os_type) {
		this.os_type = os_type;
	}

	public String getOs_ver() {
		return os_ver;
	}

	public void setOs_ver(String os_ver) {
		this.os_ver = os_ver;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getIpv6() {
		return ipv6;
	}

	public void setIpv6(String ipv6) {
		this.ipv6 = ipv6;
	}

	public String getSuper_account() {
		return super_account;
	}

	public void setSuper_account(String super_account) {
		this.super_account = super_account;
	}

	public String getSuper_password() {
		return super_password;
	}

	public void setSuper_password(String super_password) {
		this.super_password = super_password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAccount_count() {
		return account_count;
	}

	public void setAccount_count(Integer account_count) {
		this.account_count = account_count;
	}

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(Integer creator_id) {
		this.creator_id = creator_id;
	}

	public Integer getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Integer create_time) {
		this.create_time = create_time;
	}

	public Integer getLogin_method() {
		return login_method;
	}

	public void setLogin_method(Integer login_method) {
		this.login_method = login_method;
	}

	public Integer getProtocol_id() {
		return protocol_id;
	}

	public void setProtocol_id(Integer protocol_id) {
		this.protocol_id = protocol_id;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	public Integer getSsh_key() {
		return ssh_key;
	}

	public void setSsh_key(Integer ssh_key) {
		this.ssh_key = ssh_key;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getProtocolname() {
		return protocolname;
	}

	public void setProtocolname(String protocolname) {
		this.protocolname = protocolname;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTopName() {
		return topName;
	}

	public void setTopName(String topName) {
		this.topName = topName;
	}

	@Override
	public String toString() {
		return "Device{" +
				"id=" + id +
				", searchAll='" + searchAll + '\'' +
				", os_type=" + os_type +
				", os_ver='" + os_ver + '\'' +
				", name='" + name + '\'' +
				", ip='" + ip + '\'' +
				", port=" + port +
				", ipv6='" + ipv6 + '\'' +
				", super_account='" + super_account + '\'' +
				", super_password='" + super_password + '\'' +
				", status=" + status +
				", account_count=" + account_count +
				", groupid=" + groupid +
				", description='" + description + '\'' +
				", creator_id=" + creator_id +
				", create_time=" + create_time +
				", login_method=" + login_method +
				", protocol_id=" + protocol_id +
				", department=" + department +
				", depart_name='" + depart_name + '\'' +
				", ssh_key=" + ssh_key +
				", groupname='" + groupname + '\'' +
				", protocolname='" + protocolname + '\'' +
				", typename='" + typename + '\'' +
				", topName='" + topName + '\'' +
				'}';
	}
}