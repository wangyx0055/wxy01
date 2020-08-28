package com.longersec.blj.domain;

public class WorkorderAuditLog {

	private Integer id;

	private Integer workorder_apply_id;

	private Integer audit_user_id;

	private Integer audit_result;

	private String audit_datetime;

	private Integer step;
	
	private Integer department;

	private String submit_datetime;



	public WorkorderAuditLog() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWorkorder_apply_id() {
		return workorder_apply_id;
	}

	public void setWorkorder_apply_id(Integer workorder_apply_id) {
		this.workorder_apply_id = workorder_apply_id;
	}

	public Integer getAudit_user_id() {
		return audit_user_id;
	}

	public void setAudit_user_id(Integer audit_user_id) {
		this.audit_user_id = audit_user_id;
	}

	public Integer getAudit_result() {
		return audit_result;
	}

	public void setAudit_result(Integer audit_result) {
		this.audit_result = audit_result;
	}

	public String getAudit_datetime() {
		return audit_datetime;
	}

	public void setAudit_datetime(String audit_datetime) {
		this.audit_datetime = audit_datetime;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public String getSubmit_datetime() {
		return submit_datetime;
	}

	public void setSubmit_datetime(String submit_datetime) {
		this.submit_datetime = submit_datetime;
	}

	@Override
	public String toString() {
		return "WorkorderAuditLog{ "+
			",id=" + id +
			",workorder_apply_id=" + workorder_apply_id +
			",audit_user_id=" + audit_user_id +
			",audit_result=" + audit_result +
			",audit_datetime=" + audit_datetime +
			",step=" + step +
			"";
	}
}
