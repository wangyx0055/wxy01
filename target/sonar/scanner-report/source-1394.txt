package com.longersec.blj.domain;

public class CmdPolicyCmdgroup {

	private Integer policy_id;

	private Integer cmd_id;



	public CmdPolicyCmdgroup() {
		super();
	}

	public Integer getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}

	public Integer getCmd_id() {
		return cmd_id;
	}

	public void setCmd_id(Integer cmd_id) {
		this.cmd_id = cmd_id;
	}

	@Override
	public String toString() {
		return "CmdPolicyCmd{ "+
			",policy_id=" + policy_id +
			",cmd_id=" + cmd_id +
			"";
	}
}
