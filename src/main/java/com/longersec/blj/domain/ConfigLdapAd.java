package com.longersec.blj.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class ConfigLdapAd {

	private Integer id;

	@NotEmpty(message = "IP不能为空")
/*
	@Pattern(regexp = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}",message = "ip或域名格式错误")
*/
	private String ip;
	
	@NotNull(message = "端口不能为空")
	private Integer port;

	@NotEmpty(message = "域名不能为空")
	private String domain;

	@NotEmpty(message = "目录DN不能为空")
	private String basedn;

	@NotEmpty(message = "管理员不能为空")
	private String administrator;

	@NotEmpty(message = "密码不能为空")
	private String password;

	private Integer type;

	private Integer ssl;

	private Integer status;

	private String filter_department;

	private String filter_username;

	private String filter_loginname;

	private String username;

	private String realname;

	private String email;

	private String mobile;

	private Integer async;

	private Integer department_id;

	private Integer cover;

	private Integer add_department;



	public ConfigLdapAd() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getBasedn() {
		return basedn;
	}

	public void setBasedn(String basedn) {
		this.basedn = basedn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSsl() {
		return ssl;
	}

	public void setSsl(Integer ssl) {
		this.ssl = ssl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFilter_department() {
		return filter_department;
	}

	public void setFilter_department(String filter_department) {
		this.filter_department = filter_department;
	}

	public String getFilter_username() {
		return filter_username;
	}

	public void setFilter_username(String filter_username) {
		this.filter_username = filter_username;
	}

	public String getFilter_loginname() {
		return filter_loginname;
	}

	public void setFilter_loginname(String filter_loginname) {
		this.filter_loginname = filter_loginname;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getAsync() {
		return async;
	}

	public void setAsync(Integer async) {
		this.async = async;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public Integer getCover() {
		return cover;
	}

	public void setCover(Integer cover) {
		this.cover = cover;
	}

	public Integer getAdd_department() {
		return add_department;
	}

	public void setAdd_department(Integer add_department) {
		this.add_department = add_department;
	}

	public String getAdministrator() {
		return administrator;
	}

	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}

	@Override
	public String toString() {
		return "ConfigLdapAd [id=" + id + ", ip=" + ip + ", port=" + port + ", domain=" + domain + ", basedn=" + basedn
				+ ", administrator=" + administrator + ", password=" + password + ", type=" + type + ", ssl=" + ssl
				+ ", status=" + status + ", filter_department=" + filter_department + ", filter_username="
				+ filter_username + ", filter_loginname=" + filter_loginname + ", username=" + username + ", realname="
				+ realname + ", email=" + email + ", mobile=" + mobile + ", async=" + async + ", department_id="
				+ department_id + ", cover=" + cover + ", add_department=" + add_department + "]";
	}

	
}
