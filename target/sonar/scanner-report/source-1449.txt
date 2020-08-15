package com.longersec.blj.domain;

public class LogDeletePolicy {

	private Integer id;

	private String name;

	private String status;

	private Integer file_id;

	private Integer deletetype;

	private Integer type;

	private String exec_datetime;

	private Integer exec_cycle;

	private Integer internal;

	private String end_datetime;

	private String session_start_datetime;

	private String session_end_datetime;

	private String description;

	private String change_root;



	public LogDeletePolicy() {
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

	public Integer getDeletetype() {
		return deletetype;
	}

	public void setDeletetype(Integer deletetype) {
		this.deletetype = deletetype;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String  getExec_datetime() {
		return exec_datetime;
	}

	public void setExec_datetime(String  exec_datetime) {
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

	public String  getEnd_datetime() {
		return end_datetime;
	}

	public void setEnd_datetime(String  end_datetime) {
		this.end_datetime = end_datetime;
	}

	public String getSession_start_datetime() {
		return session_start_datetime;
	}

	public void setSession_start_datetime(String session_start_datetime) {
		this.session_start_datetime = session_start_datetime;
	}

	public String getSession_end_datetime() {
		return session_end_datetime;
	}

	public void setSession_end_datetime(String session_end_datetime) {
		this.session_end_datetime = session_end_datetime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChange_root() {
		return change_root;
	}

	public void setChange_root(String change_root) {
		this.change_root = change_root;
	}

	@Override
	public String toString() {
		return "LogDeletePolicy{ "+
			",id=" + id +
			",name=" + name +
			",status=" + status +
			",file_id=" + file_id +
			",deletetype=" + deletetype +
			",type=" + type +
			",exec_datetime=" + exec_datetime +
			",exec_cycle=" + exec_cycle +
			",internal=" + internal +
			",end_datetime=" + end_datetime +
			",session_start_datetime=" + session_start_datetime +
			",session_end_datetime=" + session_end_datetime +
			",description=" + description +
			",change_root=" + change_root +
			"";
	}
}
