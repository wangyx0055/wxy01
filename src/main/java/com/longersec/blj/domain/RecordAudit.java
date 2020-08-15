package com.longersec.blj.domain;

public class RecordAudit {

	private Integer id;

	private Integer record_id;

	private Integer user_id;

	private String user_username;

	private String user_surname;

	private Integer ts;

	private String ret_code;

	private String ret_desc;



	public RecordAudit() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_username() {
		return user_username;
	}

	public void setUser_username(String user_username) {
		this.user_username = user_username;
	}

	public String getUser_surname() {
		return user_surname;
	}

	public void setUser_surname(String user_surname) {
		this.user_surname = user_surname;
	}

	public Integer getTs() {
		return ts;
	}

	public void setTs(Integer ts) {
		this.ts = ts;
	}

	public String getRet_code() {
		return ret_code;
	}

	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}

	public String getRet_desc() {
		return ret_desc;
	}

	public void setRet_desc(String ret_desc) {
		this.ret_desc = ret_desc;
	}

	@Override
	public String toString() {
		return "RecordAudit{ "+
			",id=" + id +
			",record_id=" + record_id +
			",user_id=" + user_id +
			",user_username=" + user_username +
			",user_surname=" + user_surname +
			",ts=" + ts +
			",ret_code=" + ret_code +
			",ret_desc=" + ret_desc +
			"";
	}
}
