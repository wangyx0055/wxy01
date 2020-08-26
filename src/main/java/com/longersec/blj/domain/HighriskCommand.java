package com.longersec.blj.domain;

public class HighriskCommand {

	private Integer id;

	private Integer object;

	private String command;

	private String description;

	private Integer level;

	private Integer action;

	private String update_time;



	public HighriskCommand() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getObject() {
		return object;
	}

	public void setObject(Integer object) {
		this.object = object;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	@Override
	public String toString() {
		return "HighriskCommand{ "+
			",id=" + id +
			",object=" + object +
			",command=" + command +
			",description=" + description +
			",level=" + level +
			",action=" + action +
			",update_time=" + update_time +
			"";
	}
}
