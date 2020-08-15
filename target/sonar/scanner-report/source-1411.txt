package com.longersec.blj.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class ConfigRadius {

	private Integer id;

	@NotEmpty(message = "IP不能为空")
/*	@Pattern(regexp = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}",message = "IP或域名格式错误")*/
	private String ip;

	@NotNull(message = "端口号不能为空")
	private Integer port;

	@NotNull(message = "认证协议不能为空")
	private Integer protocol;

	@NotEmpty(message = "认证共享秘钥不能为空")
	private String secret_key;

	@NotNull(message = "认证超时时间不能为空")
	private Integer timeout;

	private Integer department_verify;


	private String username;


	private String password;

	private Integer status;



	public ConfigRadius() {
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

	public Integer getProtocol() {
		return protocol;
	}

	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
	}

	public String getSecret_key() {
		return secret_key;
	}

	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

/*	public Integer getDepartment_verify() {
		return department_verify;
	}

	public void setDepartment_verify(Integer department_verify) {
		this.department_verify = department_verify;
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
	}*/

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ConfigRadius{ "+
			",id=" + id +
			",ip=" + ip +
			",port=" + port +
			",protocol=" + protocol +
			",secret_key=" + secret_key +
			",timeout=" + timeout +
			",department_verify=" + department_verify +
			",username=" + username +
			",password=" + password +
			",status=" + status +
			"";
	}
}
