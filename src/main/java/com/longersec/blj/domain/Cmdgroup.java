package com.longersec.blj.domain;

public class Cmdgroup {

	private Integer id;

	private String name;

	private String cmd;

	private Integer department;

	private String depart_name;

	private String topName1;

	public String getTopName1() {
		return topName1;
	}

	public void setTopName1(String topName1) {
		this.topName1 = topName1;
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


	public Cmdgroup() {
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

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	@Override
	public String toString() {
		return "Cmdgroup{" +
				"id=" + id +
				", name='" + name + '\'' +
				", cmd='" + cmd + '\'' +
				", department=" + department +
				", depart_name='" + depart_name + '\'' +
				", topName1='" + topName1 + '\'' +
				'}';
	}
}
