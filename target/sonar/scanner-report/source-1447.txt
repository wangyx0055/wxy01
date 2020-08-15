package com.longersec.blj.domain;

public class IpGroupIp {

	private Integer id;

	private String ip;

	private Integer ip_group;



	public IpGroupIp() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getIp_group() {
		return ip_group;
	}

	public void setIp_group(Integer ip_group) {
		this.ip_group = ip_group;
	}

	@Override
	public String toString() {
		return "IpGroupIp{ "+
			",id=" + id +
			",ip=" + ip +
			",ip_group=" + ip_group +
			"";
	}
}
