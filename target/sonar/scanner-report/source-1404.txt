package com.longersec.blj.domain;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class ConfigEmail {
	
	private String ip;

	private Integer id;

	private Integer ssl;

	@Max(value = 65535)
	@Min(value = 1)
	private Integer port;

	@NotEmpty(message = "请输入账号")
	private String username;

	@NotEmpty(message = "请输入密码")
	private String password;


	public ConfigEmail() {
		super();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSsl() {
		return ssl;
	}

	public void setSsl(Integer ssl) {
		this.ssl = ssl;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
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

	@Override
	public String toString() {
		return "ConfigEmail{ "+
			",id=" + id +
			",ssl=" + ssl +
			",port=" + port +
			",username=" + username +
			",password=" + password +
			"";
	}
}
