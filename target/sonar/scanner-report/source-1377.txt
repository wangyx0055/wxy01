package com.longersec.blj.domain;

public class ApppubResourceAccount {

	private Integer apppub_resource_id;

	private Integer app_account_id;

	private Integer account_type;



	public ApppubResourceAccount() {
		super();
	}

	public Integer getApppub_resource_id() {
		return apppub_resource_id;
	}

	public void setApppub_resource_id(Integer apppub_resource_id) {
		this.apppub_resource_id = apppub_resource_id;
	}

	public Integer getApp_account_id() {
		return app_account_id;
	}

	public void setApp_account_id(Integer app_account_id) {
		this.app_account_id = app_account_id;
	}

	public Integer getAccount_type() {
		return account_type;
	}

	public void setAccount_type(Integer account_type) {
		this.account_type = account_type;
	}

	@Override
	public String toString() {
		return "ApppubResourceAccount{ "+
			",apppub_resource_id=" + apppub_resource_id +
			",app_account_id=" + app_account_id +
			",account_type=" + account_type +
			"";
	}
}
