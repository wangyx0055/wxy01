package com.longersec.blj.domain;

public class AccessPolicyApppub {

	private Integer policy_id;

	private Integer apppub_account_id;



	public AccessPolicyApppub() {
		super();
	}

	public Integer getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}
	
	public Integer getApppub_account_id() {
		return apppub_account_id;
	}

	public void setApppub_account_id(Integer apppub_account_id) {
		this.apppub_account_id = apppub_account_id;
	}

	@Override
	public String toString() {
		return "AccessPolicyApppub{ "+
			",policy_id=" + policy_id +
			",apppub_account_id=" + apppub_account_id +
			"";
	}
}
