package com.longersec.blj.domain;

public class DeviceType {

	private Integer id;

	private String name;

	private String param;

	private Integer device_type;

	private String description;

	private String searchAll;

	public String getSearchAll() {
		return searchAll;
	}

	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
	}

	@Override
	public String toString() {
		return "DeviceType{" +
				"id=" + id +
				", name='" + name + '\'' +
				", param='" + param + '\'' +
				", device_type=" + device_type +
				", description='" + description + '\'' +
				", searchAll='" + searchAll + '\'' +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	public Integer getDevice_type() {
		return device_type;
	}

	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
