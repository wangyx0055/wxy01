package com.longersec.blj.domain;

public class EmailLog {

	private Integer id;

	private String to_users;

	private String to_title;

	private String to_contents;

	private Integer result;

	private Integer fail_count;

	private String send_time;

	private Integer alert_id;



	public EmailLog() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTo_users() {
		return to_users;
	}

	public void setTo_users(String to_users) {
		this.to_users = to_users;
	}

	public String getTo_title() {
		return to_title;
	}

	public void setTo_title(String to_title) {
		this.to_title = to_title;
	}

	public String getTo_contents() {
		return to_contents;
	}

	public void setTo_contents(String to_contents) {
		this.to_contents = to_contents;
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
		return "EmailLog{ "+
			",id=" + id +
			",to_users=" + to_users +
			",to_title=" + to_title +
			",to_contents=" + to_contents +
			",result=" + result +
			",fail_count=" + fail_count +
			",send_time=" + send_time +
			",alert_id=" + alert_id +
			"";
	}
}
