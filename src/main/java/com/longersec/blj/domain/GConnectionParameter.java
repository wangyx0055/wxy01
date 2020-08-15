package com.longersec.blj.domain;

public class GConnectionParameter {

	private Integer connection_id;

	private String parameter_name;

	private String parameter_value;



	public GConnectionParameter() {
		super();
	}

	public Integer getConnection_id() {
		return connection_id;
	}

	public void setConnection_id(Integer connection_id) {
		this.connection_id = connection_id;
	}

	public String getParameter_name() {
		return parameter_name;
	}

	public void setParameter_name(String parameter_name) {
		this.parameter_name = parameter_name;
	}

	public String getParameter_value() {
		return parameter_value;
	}

	public void setParameter_value(String parameter_value) {
		this.parameter_value = parameter_value;
	}

	@Override
	public String toString() {
		return "GConnectionParameter{ "+
			",connection_id=" + connection_id +
			",parameter_name=" + parameter_name +
			",parameter_value=" + parameter_value +
			"";
	}
}
