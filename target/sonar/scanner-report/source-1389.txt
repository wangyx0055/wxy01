package com.longersec.blj.domain;

public class Cmdgroup {

	private Integer id;

	private String name;

	private String cmd;



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
		return "Cmdgroup{ "+
			",id=" + id +
			",name=" + name +
			",cmd=" + cmd +
			"";
	}
}
