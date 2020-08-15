package com.longersec.blj.domain;

import java.util.Date;

public class CrontabScriptConfig {

	private Integer id;

	private String name;

	private Integer file_id;

	private Integer exec_method;

	private String exec_datetime;

	private Integer exec_cycle;

	private Integer internal;

	private String end_datetime;

	private Integer su;

	private String description;

	private String command;

	private String searchAll;

	public String getSearchAll() {
		return searchAll;
	}

	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
	}

	public CrontabScriptConfig() {
		super();
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

	public Integer getFile_id() {
		return file_id;
	}

	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}

	public Integer getExec_cycle() {
		return exec_cycle;
	}

	public void setExec_cycle(Integer exec_cycle) {
		this.exec_cycle = exec_cycle;
	}

	public Integer getInternal() {
		return internal;
	}

	public void setInternal(Integer internal) {
		this.internal = internal;
	}

	public Integer getSu() {
		return su;
	}

	public void setSu(Integer su) {
		this.su = su;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getExec_method() {
		return exec_method;
	}

	public void setExec_method(Integer exec_method) {
		this.exec_method = exec_method;
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

	public String getEnd_datetime() {
		return end_datetime;
	}

	public void setEnd_datetime(String end_datetime) {
		this.end_datetime = end_datetime;
	}

	@Override
	public String toString() {
		return "CrontabScriptConfig{" +
				"id=" + id +
				", name='" + name + '\'' +
				", file_id=" + file_id +
				", exec_method=" + exec_method +
				", exec_datetime='" + exec_datetime + '\'' +
				", exec_cycle=" + exec_cycle +
				", internal=" + internal +
				", end_datetime='" + end_datetime + '\'' +
				", su=" + su +
				", description='" + description + '\'' +
				", command='" + command + '\'' +
				", searchAll='" + searchAll + '\'' +
				'}';
	}
}
