package com.longersec.blj.domain;

public class CmdgroupCmd {

	private Integer id;

	private Integer group_id;

	private String command;

	private Integer risk_rating;

	private Integer disposal_strategy;

	private Integer regular;

	private String risk_desc;

	public Integer getRisk_rating() {
		return risk_rating;
	}

	public void setRisk_rating(Integer risk_rating) {
		this.risk_rating = risk_rating;
	}

	public Integer getDisposal_strategy() {
		return disposal_strategy;
	}

	public void setDisposal_strategy(Integer disposal_strategy) {
		this.disposal_strategy = disposal_strategy;
	}

	public Integer getRegular() {
		return regular;
	}

	public void setRegular(Integer regular) {
		this.regular = regular;
	}

	public String getRisk_desc() {
		return risk_desc;
	}

	public void setRisk_desc(String risk_desc) {
		this.risk_desc = risk_desc;
	}

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
		return "CmdgroupCmd{" +
				"id=" + id +
				", group_id=" + group_id +
				", command='" + command + '\'' +
				", risk_rating=" + risk_rating +
				", disposal_strategy=" + disposal_strategy +
				", regular=" + regular +
				", risk_desc='" + risk_desc + '\'' +
				'}';
	}
}
