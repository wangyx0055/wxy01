package com.longersec.blj.domain;

public class LogDeletePolicyUser {

	private Integer policy_id;

	private String user_id;



	public LogDeletePolicyUser() {
		super();
	}

	public Integer getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "LogDeletePolicyUser{ "+
			",policy_id=" + policy_id +
			",user_id=" + user_id +
			"";
	}
}
