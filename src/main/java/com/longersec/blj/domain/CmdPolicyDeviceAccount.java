package com.longersec.blj.domain;

public class CmdPolicyDeviceAccount {

	private Integer policy_id;

	private Integer device_account_id;

	public Integer getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}

	public Integer getDevice_account_id() {
		return device_account_id;
	}

	public void setDevice_account_id(Integer device_account_id) {
		this.device_account_id = device_account_id;
	}

	@Override
	public String toString() {
		return "CmdPolicyDeviceAccount [policy_id=" + policy_id + ", device_account_id=" + device_account_id + "]";
	}


}
