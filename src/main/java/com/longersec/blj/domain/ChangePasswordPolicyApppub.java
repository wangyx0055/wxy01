package com.longersec.blj.domain;

public class ChangePasswordPolicyApppub {

	private Integer policy_id;

	private String apppub_id;



	public ChangePasswordPolicyApppub() {
		super();
	}

	public Integer getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}

	public String getApppub_id() {
		return apppub_id;
	}

	public void setApppub_id(String apppub_id) {
		this.apppub_id = apppub_id;
	}

	@Override
	public String toString() {
		return "ChangePasswordPolicyApppub{ "+
			",policy_id=" + policy_id +
			",apppub_id=" + apppub_id +
			"";
	}
}
