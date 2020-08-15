package com.longersec.blj.domain;

public class ChangePasswordPolicyDevice {

	private Integer policy_id;

	private String device_id;



	public ChangePasswordPolicyDevice() {
		super();
	}

	public Integer getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	@Override
	public String toString() {
		return "ChangePasswordPolicyDevice{ "+
			",policy_id=" + policy_id +
			",device_id=" + device_id +
			"";
	}
}
