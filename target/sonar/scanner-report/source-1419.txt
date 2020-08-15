package com.longersec.blj.domain;

public class CrontabScriptConfigDevice {

	private Integer config_id;

	private Integer device_account_id;

	@Override
	public String toString() {
		return "CrontabScriptConfigDevice{" +
				"config_id='" + config_id + '\'' +
				", device_id='" + device_account_id + '\'' +
				'}';
	}

	public Integer getConfig_id() {
		return config_id;
	}

	public void setConfig_id(Integer config_id) {
		this.config_id = config_id;
	}

	public Integer getDevice_account_id() {
		return device_account_id;
	}

	public void setDevice_account_id(Integer device_id) {
		this.device_account_id = device_id;
	}
}
