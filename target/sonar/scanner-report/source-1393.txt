package com.longersec.blj.domain;

public class CmdPolicyCmd {
	
	private Integer policy_id;

	private static String command;



	public CmdPolicyCmd() {
		super();
	}
	
	

	public Integer getPolicy_id() {
		return policy_id;
	}


	public void setPolicy_id(Integer policy_id) {
		this.policy_id = policy_id;
	}

	public static  String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}



	@Override
	public String toString() {
		return "CmdPolicyCmd [policy_id=" + policy_id + ", command=" + command + "]";
	}

	
}
