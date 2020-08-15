package com.longersec.blj.domain;

public class ConfigSyslog {

	private Integer id;

	private String ip;

	private Integer port;

	private Integer status;



	public ConfigSyslog() {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ConfigSyslog{ "+
			",id=" + id +
			",ip=" + ip +
			",port=" + port +
			",status=" + status +
			"";
	}
}
