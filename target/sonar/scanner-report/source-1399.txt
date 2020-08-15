package com.longersec.blj.domain;

public class CommandGroupCommand {

	private Integer id;

	private String command;

	private Integer command_group;



	public CommandGroupCommand() {
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

	public Integer getCommand_group() {
		return command_group;
	}

	public void setCommand_group(Integer command_group) {
		this.command_group = command_group;
	}

	@Override
	public String toString() {
		return "CommandGroupCommand{ "+
			",id=" + id +
			",command=" + command +
			",command_group=" + command_group +
			"";
	}
}
