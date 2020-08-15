package com.longersec.blj.domain;

public class DeviceListForOperator {
	Integer id;
	String ip;
	String name;
	String username;
	String protocol;
	Integer port;
	Integer protocol_id;
	Integer favorite_id;

	public Integer getProtocol_id() {
		return protocol_id;
	}

	public void setProtocol_id(Integer protocol_id) {
		this.protocol_id = protocol_id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getFavorite_id() {
		return favorite_id;
	}

	public void setFavorite_id(Integer favorite_id) {
		this.favorite_id = favorite_id;
	}
	
	
}
