package com.longersec.blj.domain;

public class ResourceUser {

	private Integer userid;

	private Integer resource_id;



	public ResourceUser() {
		super();
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getResource_id() {
		return resource_id;
	}

	public void setResource_id(Integer resource_id) {
		this.resource_id = resource_id;
	}

	@Override
	public String toString() {
		return "ResourceUser{ "+
			",userid=" + userid +
			",resource_id=" + resource_id +
			"";
	}
}
