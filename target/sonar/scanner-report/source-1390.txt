package com.longersec.blj.domain;

public class CmdgroupCmd {

	private Integer id;

	private Integer group_id;

	private String command;



	public CmdgroupCmd() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@Override
	public String toString() {
		return "CmdgroupCmd{ "+
			",id=" + id +
			",group_id=" + group_id +
			",command=" + command +
			"";
	}
}
