package com.longersec.blj.domain;

public class CrontabCommandLog {

	private Integer id;

	private String command;

	private String exec_datetime;

	private String result;

	private String device_name;

	private String device_ip;

	private Integer device_port;

	private String device_username;

	private Integer exec_status;

	private String end_datetime;

	private Integer config_id;

	private String config_name;

	private String searchAll;

	private Integer config_method;

	private Integer config_cycle;

	private Integer department;

	private String depart_name;

	private String topName;

	public Integer getConfig_cycle() {
		return config_cycle;
	}

	public void setConfig_cycle(Integer config_cycle) {
		this.config_cycle = config_cycle;
	}

	public Integer getConfig_method() {
		return config_method;
	}

	public void setConfig_method(Integer config_method) {
		this.config_method = config_method;
	}

	public String getSearchAll() {
		return searchAll;
	}

	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
	}

	public CrontabCommandLog() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getExec_datetime() {
		return exec_datetime;
	}

	public void setExec_datetime(String exec_datetime) {
		this.exec_datetime = exec_datetime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_ip() {
		return device_ip;
	}

	public void setDevice_ip(String device_ip) {
		this.device_ip = device_ip;
	}

	public Integer getDevice_port() {
		return device_port;
	}

	public void setDevice_port(Integer device_port) {
		this.device_port = device_port;
	}

	public String getDevice_username() {
		return device_username;
	}

	public void setDevice_username(String device_username) {
		this.device_username = device_username;
	}

	public Integer getExec_status() {
		return exec_status;
	}

	public void setExec_status(Integer exec_status) {
		this.exec_status = exec_status;
	}

	public String getEnd_datetime() {
		return end_datetime;
	}

	public void setEnd_datetime(String end_datetime) {
		this.end_datetime = end_datetime;
	}

	public Integer getConfig_id() {
		return config_id;
	}

	public void setConfig_id(Integer config_id) {
		this.config_id = config_id;
	}
	

	public String getConfig_name() {
		return config_name;
	}

	public void setConfig_name(String config_name) {
		this.config_name = config_name;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
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
		return "CrontabCommandLog{" +
				"id=" + id +
				", command='" + command + '\'' +
				", exec_datetime='" + exec_datetime + '\'' +
				", result='" + result + '\'' +
				", device_name='" + device_name + '\'' +
				", device_ip='" + device_ip + '\'' +
				", device_port=" + device_port +
				", device_username='" + device_username + '\'' +
				", exec_status=" + exec_status +
				", end_datetime='" + end_datetime + '\'' +
				", config_id=" + config_id +
				", config_name='" + config_name + '\'' +
				", searchAll='" + searchAll + '\'' +
				", config_method=" + config_method +
				", config_cycle=" + config_cycle +
				", department=" + department +
				", depart_name='" + depart_name + '\'' +
				", topName='" + topName + '\'' +
				'}';
	}
}
