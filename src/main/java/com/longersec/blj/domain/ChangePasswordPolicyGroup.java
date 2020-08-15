package com.longersec.blj.domain;

public class ChangePasswordPolicyGroup {

	private Integer policy_id;

	private String group_id;



	public ChangePasswordPolicyGroup() {
		super();
	}

	public Integer getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}

	@Override
	public String toString() {
		return "ChangePasswordPolicyGroup{ "+
			",policy_id=" + policy_id +
			",group_id=" + group_id +
			"";
	}
}
