package com.longersec.blj.domain;

public class Protocol {

	private Integer id;

	private String name;



	public Protocol() {
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
		return "protocol_id{ "+
			",id=" + id +
			",name=" + name +
			"";
	}
}
