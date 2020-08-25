package com.longersec.blj.domain;

public class ConfigWorkorder {

	private Integer id;

	private Integer range;

	private Integer dead_hours;

	private Integer outdate_action;

	private Integer mode;

	private Integer level;

	private Integer endaudit;

	private String update_time;



	public ConfigWorkorder() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRange() {
		return range;
	}

	public void setRange(Integer range) {
		this.range = range;
	}

	public Integer getDead_hours() {
		return dead_hours;
	}

	public void setDead_hours(Integer dead_hours) {
		this.dead_hours = dead_hours;
	}

	public Integer getOutdate_action() {
		return outdate_action;
	}

	public void setOutdate_action(Integer outdate_action) {
		this.outdate_action = outdate_action;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getEndaudit() {
		return endaudit;
	}

	public void setEndaudit(Integer endaudit) {
		this.endaudit = endaudit;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	@Override
	public String toString() {
		return "ConfigWorkorder{ "+
			",id=" + id +
			",range=" + range +
			",dead_hours=" + dead_hours +
			",outdate_action=" + outdate_action +
			",mode=" + mode +
			",level=" + level +
			",endaudit=" + endaudit +
			",update_time=" + update_time +
			"";
	}
}
