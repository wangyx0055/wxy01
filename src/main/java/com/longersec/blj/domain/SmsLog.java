package com.longersec.blj.domain;

public class SmsLog {

	private Integer id;

	private String mobile;

	private String contents;

	private Integer result;

	private Integer fail_count;

	private String send_time;

	private Integer alert_id;



	public SmsLog() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getFail_count() {
		return fail_count;
	}

	public void setFail_count(Integer fail_count) {
		this.fail_count = fail_count;
	}

	public String getSend_time() {
		return send_time;
	}

	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}

	public Integer getAlert_id() {
		return alert_id;
	}

	public void setAlert_id(Integer alert_id) {
		this.alert_id = alert_id;
	}

	@Override
	public String toString() {
		return "SmsLog{ "+
			",id=" + id +
			",mobile=" + mobile +
			",contents=" + contents +
			",result=" + result +
			",fail_count=" + fail_count +
			",send_time=" + send_time +
			",alert_id=" + alert_id +
			"";
	}
}
