package com.longersec.blj.domain;

public class SessionHanddelete {

	private Integer id;

	private String name;

	private String table;

	private String department_id;

	private Integer status;

	private Integer start_datetime;

	private Integer end_datetime;



	public SessionHanddelete() {
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

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStart_datetime() {
		return start_datetime;
	}

	public void setStart_datetime(Integer start_datetime) {
		this.start_datetime = start_datetime;
	}

	public Integer getEnd_datetime() {
		return end_datetime;
	}

	public void setEnd_datetime(Integer end_datetime) {
		this.end_datetime = end_datetime;
	}

	@Override
	public String toString() {
		return "SessionHanddelete{ "+
			",id=" + id +
			",name=" + name +
			",table=" + table +
			",department_id=" + department_id +
			",status=" + status +
			",start_datetime=" + start_datetime +
			",end_datetime=" + end_datetime +
			"";
	}
}
