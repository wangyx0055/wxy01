package com.longersec.blj.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class ConfigFinger {

	private Integer id;

	@NotEmpty(message = "接口地址不能为空")
	private String url;
	
	private Integer status;
	private String endpoint;
	private String pwd;


	public ConfigFinger() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "ConfigFinger{ "+
			",id=" + id +
			",url=" + url +
			"";
	}
}
