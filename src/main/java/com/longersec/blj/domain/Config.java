package com.longersec.blj.domain;

public class Config {

	private Integer id;

	private String name;

	private String value;



	public Config() {
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Config{ "+
			",id=" + id +
			",name=" + name +
			",value=" + value +
			"";
	}
}
