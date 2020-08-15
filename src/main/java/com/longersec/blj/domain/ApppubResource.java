package com.longersec.blj.domain;

public class ApppubResource {

	private Integer id;

	private String name;



	public ApppubResource() {
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
		return "ApppubResource{ "+
			",id=" + id +
			",name=" + name +
			"";
	}
}
