package com.longersec.blj.domain;

public class CmdPolicyApppub {

	private Integer policy_id;

	private Integer apppub_id;



	public CmdPolicyApppub() {
		super();
	}

	public Integer getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}

	public Integer getApppub_id() {
		return apppub_id;
	}

	public void setApppub_id(Integer apppub_id) {
		this.apppub_id = apppub_id;
	}

	@Override
	public String toString() {
		return "CmdPolicyApppub{ "+
			",policy_id=" + policy_id +
			",apppub_id=" + apppub_id +
			"";
	}
}
