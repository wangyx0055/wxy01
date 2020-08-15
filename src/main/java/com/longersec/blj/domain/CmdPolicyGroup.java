package com.longersec.blj.domain;

public class CmdPolicyGroup {

	private Integer policy_id;

	private Integer group_id;

	private Integer type;


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public CmdPolicyGroup() {
		super();
	}

	public Integer getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	@Override
	public String toString() {
		return "CmdPolicyGroup [policy_id=" + policy_id + ", group_id=" + group_id + ", type=" + type + "]";
	}


}
