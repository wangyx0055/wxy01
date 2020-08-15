package com.longersec.blj.domain;

public class ConfigNetwork {

	private Integer id;

	private String name;

	private String device;

	private Integer status;
	
	private String bootprotocol;
	
	private String ipv4addr;
	
	private String ipv4netmask;
	
	private String ipv4gateway;
	
	private Integer ipv6enable;
	
	private Integer ipv6autoconf;
	
	private String ipv6addr;
	
	private String ipv6gateway;



	public ConfigNetwork() {
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

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getIpv4addr() {
		return ipv4addr;
	}

	public void setIpv4addr(String ipv4addr) {
		this.ipv4addr = ipv4addr;
	}

	public String getIpv4netmask() {
		return ipv4netmask;
	}

	public void setIpv4netmask(String ipv4netmask) {
		this.ipv4netmask = ipv4netmask;
	}

	public String getIpv4gateway() {
		return ipv4gateway;
	}

	public void setIpv4gateway(String ipv4gateway) {
		this.ipv4gateway = ipv4gateway;
	}

	public Integer getIpv6enable() {
		return ipv6enable;
	}

	public void setIpv6enable(Integer ipv6enable) {
		this.ipv6enable = ipv6enable;
	}
	

	public Integer getIpv6autoconf() {
		return ipv6autoconf;
	}

	public void setIpv6autoconf(Integer ipv6autoconf) {
		this.ipv6autoconf = ipv6autoconf;
	}

	public String getIpv6addr() {
		return ipv6addr;
	}

	public void setIpv6addr(String ipv6addr) {
		this.ipv6addr = ipv6addr;
	}

	public String getIpv6gateway() {
		return ipv6gateway;
	}

	public void setIpv6gateway(String ipv6gateway) {
		this.ipv6gateway = ipv6gateway;
	}

	public String getBootprotocol() {
		return bootprotocol;
	}

	public void setBootprotocol(String bootprotocol) {
		this.bootprotocol = bootprotocol;
	}

	@Override
	public String toString() {
		return "ConfigNetwork [id=" + id + ", name=" + name + ", device=" + device + ", status=" + status
				+ ", bootprotocol=" + bootprotocol + ", ipv4addr=" + ipv4addr + ", ipv4netmask=" + ipv4netmask
				+ ", ipv4gateway=" + ipv4gateway + ", ipv6enable=" + ipv6enable + ", ipv6autoconf=" + ipv6autoconf
				+ ", ipv6addr=" + ipv6addr + ", ipv6gateway=" + ipv6gateway + "]";
	}
	
	
}
