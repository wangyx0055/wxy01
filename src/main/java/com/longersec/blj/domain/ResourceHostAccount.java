package com.longersec.blj.domain;

public class ResourceHostAccount {

	private Integer resource_id;

	private Integer device_username_id;

	private Integer host_type;



	public ResourceHostAccount() {
		super();
	}

	public Integer getResource_id() {
		return resource_id;
	}

	public void setResource_id(Integer resource_id) {
		this.resource_id = resource_id;
	}

	public Integer getDevice_username_id() {
		return device_username_id;
	}

	public void setDevice_username_id(Integer device_username_id) {
		this.device_username_id = device_username_id;
	}

	public Integer getHost_type() {
		return host_type;
	}

	public void setHost_type(Integer host_type) {
		this.host_type = host_type;
	}

	@Override
	public String toString() {
		return "ResourceHostAccount{ "+
			",resource_id=" + resource_id +
			",device_username_id=" + device_username_id +
			",host_type=" + host_type +
			"";
	}
}
