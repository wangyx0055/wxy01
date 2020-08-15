package com.longersec.blj.domain;

public class Apppub {

	private Integer id;

	private Integer device_id;

	private String app_name;

	private String server_ip;

	private Integer port;

	private String paramater;

	private Integer up_clipboard;

	private Integer down_clipboard;

	private String app_description;

	private Integer apppub_server_id;



	public Apppub() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDevice_id() {
		return device_id;
	}

	public void setDevice_id(Integer device_id) {
		this.device_id = device_id;
	}

	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	public String getServer_ip() {
		return server_ip;
	}

	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getParamater() {
		return paramater;
	}

	public void setParamater(String paramater) {
		this.paramater = paramater;
	}

	public Integer getUp_clipboard() {
		return up_clipboard;
	}

	public void setUp_clipboard(Integer up_clipboard) {
		this.up_clipboard = up_clipboard;
	}

	public Integer getDown_clipboard() {
		return down_clipboard;
	}

	public void setDown_clipboard(Integer down_clipboard) {
		this.down_clipboard = down_clipboard;
	}

	public String getApp_description() {
		return app_description;
	}

	public void setApp_description(String app_description) {
		this.app_description = app_description;
	}

	public Integer getApppub_server_id() {
		return apppub_server_id;
	}

	public void setApppub_server_id(Integer apppub_server_id) {
		this.apppub_server_id = apppub_server_id;
	}

	@Override
	public String toString() {
		return "Apppub{ "+
			",id=" + id +
			",device_id=" + device_id +
			",app_name=" + app_name +
			",server_ip=" + server_ip +
			",port=" + port +
			",paramater=" + paramater +
			",up_clipboard=" + up_clipboard +
			",down_clipboard=" + down_clipboard +
			",app_description=" + app_description +
			",apppub_server_id=" + apppub_server_id +
			"";
	}
}
