package com.longersec.blj.domain;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ApppubServer {

	private Integer id;

	@NotNull()
	@Pattern(regexp = "((?:(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d)\\\\.){3}(?:25[0-5]|2[0-4]\\\\d|[01]?\\\\d?\\\\d))|([a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?)")
	private String ip;

	@NotNull()
	private String name;
	
	private String domain;

	@NotNull()
	private String desc;

	@Range(min = 1,max = 65535)
	private Integer port;
	
	private Integer adport;

	private Integer type;

	private Integer department;

	private String depart_name;

	private String account;
	
	private String password;

	private String topName;

	public String getTopName() {
		return topName;
	}

	public void setTopName(String topName) {
		this.topName = topName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public ApppubServer() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
	public Integer getAdport() {
		return adport;
	}

	public void setAdport(Integer adport) {
		this.adport = adport;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ApppubServer{" +
				"id=" + id +
				", ip='" + ip + '\'' +
				", name='" + name + '\'' +
				", domain='" + domain + '\'' +
				", desc='" + desc + '\'' +
				", port=" + port +
				", adport=" + adport +
				", type=" + type +
				", department=" + department +
				", depart_name='" + depart_name + '\'' +
				", account='" + account + '\'' +
				", password='" + password + '\'' +
				", topName='" + topName + '\'' +
				'}';
	}
}
