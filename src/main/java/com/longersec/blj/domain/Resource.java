package com.longersec.blj.domain;

public class Resource {

	private Integer id;

	private String name;



	public Resource() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Resource{ "+
			",id=" + id +
			",name=" + name +
			"";
	}
}
