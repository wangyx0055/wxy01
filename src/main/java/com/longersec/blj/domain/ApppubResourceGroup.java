package com.longersec.blj.domain;

public class ApppubResourceGroup {

	private Integer group;

	private Integer apppub_resource_id;



	public ApppubResourceGroup() {
		super();
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public Integer getApppub_resource_id() {
		return apppub_resource_id;
	}

	public void setApppub_resource_id(Integer apppub_resource_id) {
		this.apppub_resource_id = apppub_resource_id;
	}

	@Override
	public String toString() {
		return "ApppubResourceGroup{ "+
			",group=" + group +
			",apppub_resource_id=" + apppub_resource_id +
			"";
	}
}
