package com.longersec.blj.domain;

public class ConfigPort {

	private Integer id;

	private Integer ssh_sftp;

	private Integer rdp;

	private Integer web;

	private Integer control_port;

	private Integer ftp;



	public ConfigPort() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSsh_sftp() {
		return ssh_sftp;
	}

	public void setSsh_sftp(Integer ssh_sftp) {
		this.ssh_sftp = ssh_sftp;
	}

	public Integer getRdp() {
		return rdp;
	}

	public void setRdp(Integer rdp) {
		this.rdp = rdp;
	}

	public Integer getWeb() {
		return web;
	}

	public void setWeb(Integer web) {
		this.web = web;
	}

	public Integer getControl_port() {
		return control_port;
	}

	public void setControl_port(Integer control_port) {
		this.control_port = control_port;
	}

	public Integer getFtp() {
		return ftp;
	}

	public void setFtp(Integer ftp) {
		this.ftp = ftp;
	}

	@Override
	public String toString() {
		return "ConfigPort{ "+
			",id=" + id +
			",ssh_sftp=" + ssh_sftp +
			",rdp=" + rdp +
			",web=" + web +
			",control_port=" + control_port +
			",ftp=" + ftp +
			"";
	}
}
