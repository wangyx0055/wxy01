package com.longersec.blj.domain;

public class ConfigRoute {

	private Integer id;

	private Integer device_type;

	private String device;

	private String destion_ip;

	private String netmask;

	private Integer prefix;

	private String nextaddress;

	private String desc;



	public ConfigRoute() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDevice_type() {
		return device_type;
	}

	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getDestion_ip() {
		return destion_ip;
	}

	public void setDestion_ip(String destion_ip) {
		this.destion_ip = destion_ip;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public Integer getPrefix() {
		return prefix;
	}

	public void setPrefix(Integer prefix) {
		this.prefix = prefix;
	}

	public String getNextaddress() {
		return nextaddress;
	}

	public void setNextaddress(String nextaddress) {
		this.nextaddress = nextaddress;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "ConfigRoute{ "+
			",id=" + id +
			",device_type=" + device_type +
			",device=" + device +
			",destion_ip=" + destion_ip +
			",netmask=" + netmask +
			",prefix=" + prefix +
			",nextaddress=" + nextaddress +
			",desc=" + desc +
			"";
	}
}
