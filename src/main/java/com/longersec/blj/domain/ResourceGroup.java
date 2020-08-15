package com.longersec.blj.domain;

public class ResourceGroup {

	private Integer group;

	private Integer resource_id;



	public ResourceGroup() {
		super();
	}

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	public Integer getResource_id() {
		return resource_id;
	}

	public void setResource_id(Integer resource_id) {
		this.resource_id = resource_id;
	}

	@Override
	public String toString() {
		return "ResourceGroup{ "+
			",group=" + group +
			",resource_id=" + resource_id +
			"";
	}
}
