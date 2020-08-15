package com.longersec.blj.domain;

public class BackupConfig {

	private Integer seq;

	private String ip;

	private Integer port;

	private Integer dbactive;

	private Integer fileactive;

	private String user;

	private String passwd;

	private String mysqluser;

	private String mysqlpasswd;

	private String path;

	private String dbname;

	private Integer enable;

	private Integer session_flag;

	private String protocol;

	private String desc;



	public BackupConfig() {
		super();
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getDbactive() {
		return dbactive;
	}

	public void setDbactive(Integer dbactive) {
		this.dbactive = dbactive;
	}

	public Integer getFileactive() {
		return fileactive;
	}

	public void setFileactive(Integer fileactive) {
		this.fileactive = fileactive;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getMysqluser() {
		return mysqluser;
	}

	public void setMysqluser(String mysqluser) {
		this.mysqluser = mysqluser;
	}

	public String getMysqlpasswd() {
		return mysqlpasswd;
	}

	public void setMysqlpasswd(String mysqlpasswd) {
		this.mysqlpasswd = mysqlpasswd;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDbname() {
		return dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getSession_flag() {
		return session_flag;
	}

	public void setSession_flag(Integer session_flag) {
		this.session_flag = session_flag;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "BackupConfig{ "+
			",seq=" + seq +
			",ip=" + ip +
			",port=" + port +
			",dbactive=" + dbactive +
			",fileactive=" + fileactive +
			",user=" + user +
			",passwd=" + passwd +
			",mysqluser=" + mysqluser +
			",mysqlpasswd=" + mysqlpasswd +
			",path=" + path +
			",dbname=" + dbname +
			",enable=" + enable +
			",session_flag=" + session_flag +
			",protocol=" + protocol +
			",desc=" + desc +
			"";
	}
}
