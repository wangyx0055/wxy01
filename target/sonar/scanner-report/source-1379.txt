package com.longersec.blj.domain;

public class ApppubResourceUser {

	private Integer userid;

	private Integer apppub_resource_id;



	public ApppubResourceUser() {
		super();
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getApppub_resource_id() {
		return apppub_resource_id;
	}

	public void setApppub_resource_id(Integer apppub_resource_id) {
		this.apppub_resource_id = apppub_resource_id;
	}

	@Override
	public String toString() {
		return "ApppubResourceUser{ "+
			",userid=" + userid +
			",apppub_resource_id=" + apppub_resource_id +
			"";
	}
}
