package com.longersec.blj.domain;

public class WorkorderApplyDeviceAccount {

	private Integer workorder_apply_id;

	private Integer device_account_id;

	public Integer getWorkorder_apply_id() {
		return workorder_apply_id;
	}

	public void setWorkorder_apply_id(Integer workorder_apply_id) {
		this.workorder_apply_id = workorder_apply_id;
	}

	public Integer getDevice_account_id() {
		return device_account_id;
	}

	public void setDevice_account_id(Integer device_account_id) {
		this.device_account_id = device_account_id;
	}

	@Override
	public String toString() {
		return "WorkorderApplyDeviceAccount{ "+
			",workorder_apply_id=" + workorder_apply_id +
			",device_account_id=" + device_account_id +
			"";
	}
}
