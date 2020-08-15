package com.longersec.blj.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CrontabScriptLog {

	private Integer id;

	private Integer crontab_script_config_id;

	private String exec_datetime;

	private String exec_cycle;

	private String end_datetime;

	private Integer user_id;

	private Integer status;

	private Integer su;

	private String result;

	@NotNull
	@Pattern(regexp = "([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]|[;%&'@!#$%*+,=_?$]){1,32}")
	private String name;

	private String exec_way;

	private String exec_detail;

	@Pattern(regexp = "(^$)|([A-Za-z]|[\\u4e00-\\u9fa5]|\\-|[0-9]|[;%&'@!#$%*+,=_?$]){0,64}")
	private String desc;

	private Integer type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCrontab_script_config_id() {
		return crontab_script_config_id;
	}

	public void setCrontab_script_config_id(Integer crontab_script_config_id) {
		this.crontab_script_config_id = crontab_script_config_id;
	}

	public String getExec_datetime() {
		return exec_datetime;
	}

	public void setExec_datetime(String exec_datetime) {
		this.exec_datetime = exec_datetime;
	}

	public String getExec_detail() {
		return exec_detail;
	}

	public String getExec_cycle() {
		return exec_cycle;
	}

	public void setExec_cycle(String exec_cycle) {
		this.exec_cycle = exec_cycle;
	}

	public String getEnd_datetime() {
		return end_datetime;
	}

	public void setEnd_datetime(String end_datetime) {
		this.end_datetime = end_datetime;
	}

	public void setExec_detail(String exec_detail) {
		this.exec_detail = exec_detail;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSu() {
		return su;
	}

	public void setSu(Integer su) {
		this.su = su;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExec_way() {
		return exec_way;
	}

	public void setExec_way(String exec_way) {
		this.exec_way = exec_way;
	}

	@Override
	public String toString() {
		return "CrontabScriptLog{" +
				"id=" + id +
				", crontab_script_config_id=" + crontab_script_config_id +
				", exec_datetime='" + exec_datetime + '\'' +
				", exec_cycle='" + exec_cycle + '\'' +
				", end_datetime='" + end_datetime + '\'' +
				", user_id=" + user_id +
				", status=" + status +
				", su=" + su +
				", result='" + result + '\'' +
				", name='" + name + '\'' +
				", exec_way='" + exec_way + '\'' +
				", exec_detail='" + exec_detail + '\'' +
				", desc='" + desc + '\'' +
				", type=" + type +
				'}';
	}
}
