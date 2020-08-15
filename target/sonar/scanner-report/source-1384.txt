package com.longersec.blj.domain;

public class ChangePasswordPolicy {

	private Integer id;

	private String name;

	private String status;

	private Integer file_id;

	private String changetype;

	private Integer type;
	
	private Integer exec_method;

	private String exec_datetime;

	private Integer exec_cycle;

	private Integer internal;

	private String end_datetime;

	private String description;

	private Integer allow_root;

	private Integer allow_change_root;



	public ChangePasswordPolicy() {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getFile_id() {
		return file_id;
	}

	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}

	public String getChangetype() {
		return changetype;
	}

	public void setChangetype(String changetype) {
		this.changetype = changetype;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getExec_datetime() {
		return exec_datetime;
	}

	public void setExec_datetime(String exec_datetime) {
		this.exec_datetime = exec_datetime;
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

	public Integer getExec_method() {
		return exec_method;
	}


	public void setExec_method(Integer exec_method) {
		this.exec_method = exec_method;
	}

	public String getEnd_datetime() {
		return end_datetime;
	}

	public void setEnd_datetime(String end_datetime) {
		this.end_datetime = end_datetime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAllow_root() {
		return allow_root;
	}

	public void setAllow_root(Integer allow_root) {
		this.allow_root = allow_root;
	}

	public Integer getAllow_change_root() {
		return allow_change_root;
	}

	public void setAllow_change_root(Integer allow_change_root) {
		this.allow_change_root = allow_change_root;
	}


	@Override
	public String toString() {
		return "ChangePasswordPolicy [id=" + id + ", name=" + name + ", status=" + status + ", file_id=" + file_id
				+ ", changetype=" + changetype + ", type=" + type + ", exec_method=" + exec_method + ", exec_datetime="
				+ exec_datetime + ", exec_cycle=" + exec_cycle + ", internal=" + internal + ", end_datetime="
				+ end_datetime + ", description=" + description + ", allow_root=" + allow_root + ", allow_change_root="
				+ allow_change_root + "]";
	}

}
