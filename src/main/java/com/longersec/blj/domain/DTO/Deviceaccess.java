package com.longersec.blj.domain.DTO;

import java.util.Objects;

public class Deviceaccess {
     private String username;
     
     private Integer workorder_apply_id;
     
     private Integer device_account_id;
     
     private Integer protocol_id;
     
     private Integer port;

     private String device_name;
     
     private String protocol_name;
     
     private String ip;
     
     private Integer department;
     
     private String depart_name;
     
     private String topName;

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public Integer getWorkorder_apply_id() {
		return workorder_apply_id;
	}

	public void setWorkorder_apply_id(Integer workorder_apply_id) {
		this.workorder_apply_id = workorder_apply_id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getDevice_account_id() {
		return device_account_id;
	}
	public void setDevice_account_id(Integer device_account_id) {
		this.device_account_id = device_account_id;
	}

	public Integer getProtocol_id() {
		return protocol_id;
	}

	public void setProtocol_id(Integer protocol_id) {
		this.protocol_id = protocol_id;
	}
	
	public String getProtocol_name() {
		return protocol_name;
	}

	public void setProtocol_name(String protocol_name) {
		this.protocol_name = protocol_name;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
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
	

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	public String getTopName() {
		return topName;
	}

	public void setTopName(String topName) {
		this.topName = topName;
	}

	@Override
	public String toString() {
		return "Deviceaccess{" +
				"username='" + username + '\'' +
				", device_account_id='" + device_account_id + '\'' +
				", device_name='" + device_name + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Deviceaccess that = (Deviceaccess) o;
		return Objects.equals(username, that.username) &&
				Objects.equals(device_account_id, that.device_account_id) &&
				Objects.equals(device_name, that.device_name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, device_account_id, device_name);
	}

}
