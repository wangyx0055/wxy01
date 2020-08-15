package com.longersec.blj.domain;

public class Menu {

	private Integer id;

	private String name;

	private String function_name;

	private String module;



	public Menu() {
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

	public String getFunction_name() {
		return function_name;
	}

	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Override
	public String toString() {
		return "Menu{ "+
			",id=" + id +
			",name=" + name +
			",function_name=" + function_name +
			",module=" + module +
			"";
	}
}
