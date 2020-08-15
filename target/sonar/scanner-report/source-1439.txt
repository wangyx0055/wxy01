package com.longersec.blj.domain;

public class DynamicToken {

	private Integer id;

	private String sign;

	private String private_key;

	private String user_id;

	private Integer creator_id;

	private Integer creator_time;

	private Integer status;



	public DynamicToken() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getPrivate_key() {
		return private_key;
	}

	public void setPrivate_key(String private_key) {
		this.private_key = private_key;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Integer getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(Integer creator_id) {
		this.creator_id = creator_id;
	}

	public Integer getCreator_time() {
		return creator_time;
	}

	public void setCreator_time(Integer creator_time) {
		this.creator_time = creator_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DynamicToken{ "+
			",id=" + id +
			",sign=" + sign +
			",private_key=" + private_key +
			",user_id=" + user_id +
			",creator_id=" + creator_id +
			",creator_time=" + creator_time +
			",status=" + status +
			"";
	}
}
