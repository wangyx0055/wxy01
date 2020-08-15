package com.longersec.blj.domain;

public class CrontabScriptConfigGroup {

	private Integer config_id;

	private Integer group_id;

	private Integer type;
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}



	public Integer getConfig_id() {
		return config_id;
	}

	public void setConfig_id(Integer config_id) {
		this.config_id = config_id;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	@Override
	public String toString() {
		return "CrontabScriptConfigGroup{" +
				"config_id='" + config_id + '\'' +
				", group_id='" + group_id + '\'' +
				", type=" + type +
				'}';
	}
}
