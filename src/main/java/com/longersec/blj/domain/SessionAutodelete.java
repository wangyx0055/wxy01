package com.longersec.blj.domain;

public class SessionAutodelete {

	private Integer id;

	private String name;

	private String table;

	private Integer days;

	private Integer status;



	public SessionAutodelete() {
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

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SessionAutodelete{ "+
			",id=" + id +
			",name=" + name +
			",table=" + table +
			",days=" + days +
			",status=" + status +
			"";
	}
}
