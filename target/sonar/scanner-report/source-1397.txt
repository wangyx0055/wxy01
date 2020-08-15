package com.longersec.blj.domain;

public class CmdPolicyUser {

	private Integer policy_id;

	private Integer user_id;



	public CmdPolicyUser() {
		super();
	}

	public Integer getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "CmdPolicyUser{ "+
			",policy_id=" + policy_id +
			",user_id=" + user_id +
			"";
	}
}
