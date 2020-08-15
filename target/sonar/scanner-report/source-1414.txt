package com.longersec.blj.domain;

public class ConfigSnmp {

	private Integer id;

	private String community;

	private Integer status;



	public ConfigSnmp() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ConfigSnmp{ "+
			",id=" + id +
			",community=" + community +
			",status=" + status +
			"";
	}
}
