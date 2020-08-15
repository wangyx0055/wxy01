package com.longersec.blj.domain;

public class ConfigDisksession {

	private Integer id;

	private Integer disk_config_enable;

	private Integer disk_max_persent;

	private Integer disk_write_type;

	private Integer session_backup_enable;

	private String session_backup_time;

	private Integer session_backup_type;

	private String session_backup_host;

	private String session_backup_port;

	private String session_backup_username;

	private String session_backup_password;

	private String session_backup_path;

	private Integer session_backup_iscompress;



	public ConfigDisksession() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDisk_config_enable() {
		return disk_config_enable;
	}

	public void setDisk_config_enable(Integer disk_config_enable) {
		this.disk_config_enable = disk_config_enable;
	}

	public Integer getDisk_max_persent() {
		return disk_max_persent;
	}

	public void setDisk_max_persent(Integer disk_max_persent) {
		this.disk_max_persent = disk_max_persent;
	}

	public Integer getDisk_write_type() {
		return disk_write_type;
	}

	public void setDisk_write_type(Integer disk_write_type) {
		this.disk_write_type = disk_write_type;
	}

	public Integer getSession_backup_enable() {
		return session_backup_enable;
	}

	public void setSession_backup_enable(Integer session_backup_enable) {
		this.session_backup_enable = session_backup_enable;
	}

	public String getSession_backup_time() {
		return session_backup_time;
	}

	public void setSession_backup_time(String session_backup_time) {
		this.session_backup_time = session_backup_time;
	}

	public Integer getSession_backup_type() {
		return session_backup_type;
	}

	public void setSession_backup_type(Integer session_backup_type) {
		this.session_backup_type = session_backup_type;
	}

	public String getSession_backup_host() {
		return session_backup_host;
	}

	public void setSession_backup_host(String session_backup_host) {
		this.session_backup_host = session_backup_host;
	}

	public String getSession_backup_username() {
		return session_backup_username;
	}

	public void setSession_backup_username(String session_backup_username) {
		this.session_backup_username = session_backup_username;
	}

	public String getSession_backup_password() {
		return session_backup_password;
	}

	public void setSession_backup_password(String session_backup_password) {
		this.session_backup_password = session_backup_password;
	}

	public String getSession_backup_path() {
		return session_backup_path;
	}

	public void setSession_backup_path(String session_backup_path) {
		this.session_backup_path = session_backup_path;
	}

	public String getSession_backup_port() {
		return session_backup_port;
	}

	public void setSession_backup_port(String session_backup_port) {
		this.session_backup_port = session_backup_port;
	}

	public Integer getSession_backup_iscompress() {
		return session_backup_iscompress;
	}

	public void setSession_backup_iscompress(Integer session_backup_iscompress) {
		this.session_backup_iscompress = session_backup_iscompress;
	}

	@Override
	public String toString() {
		return "ConfigDisksession [id=" + id + ", disk_config_enable=" + disk_config_enable + ", disk_max_persent="
				+ disk_max_persent + ", disk_write_type=" + disk_write_type + ", session_backup_enable="
				+ session_backup_enable + ", session_backup_time=" + session_backup_time + ", session_backup_type="
				+ session_backup_type + ", session_backup_host=" + session_backup_host + ", session_backup_port="
				+ session_backup_port + ", session_backup_username=" + session_backup_username
				+ ", session_backup_password=" + session_backup_password + ", session_backup_path="
				+ session_backup_path + ", session_backup_iscompress=" + session_backup_iscompress + "]";
	}
	
}
