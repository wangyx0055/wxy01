package com.longersec.blj.domain;

public class Record {

	private Integer id;

	private String core_sn;

	private Integer flag;

	private String reason;

	private String sid;

	private Integer user_id;

	private Integer host_id;

	private Integer acc_id;

	private Integer status;

	private String user_username;

	private String user_surname;

	private String host_ip;

	private String conn_ip;

	private Integer conn_port;

	private String client_ip;

	private String acc_username;

	private Integer auth_type;

	private Integer protocol;

	private Integer protocol_sub_type;

	private Integer time_begin;

	private Integer time_end;



	public Record() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCore_sn() {
		return core_sn;
	}

	public void setCore_sn(String core_sn) {
		this.core_sn = core_sn;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getHost_id() {
		return host_id;
	}

	public void setHost_id(Integer host_id) {
		this.host_id = host_id;
	}

	public Integer getAcc_id() {
		return acc_id;
	}

	public void setAcc_id(Integer acc_id) {
		this.acc_id = acc_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUser_username() {
		return user_username;
	}

	public void setUser_username(String user_username) {
		this.user_username = user_username;
	}

	public String getUser_surname() {
		return user_surname;
	}

	public void setUser_surname(String user_surname) {
		this.user_surname = user_surname;
	}

	public String getHost_ip() {
		return host_ip;
	}

	public void setHost_ip(String host_ip) {
		this.host_ip = host_ip;
	}

	public String getConn_ip() {
		return conn_ip;
	}

	public void setConn_ip(String conn_ip) {
		this.conn_ip = conn_ip;
	}

	public Integer getConn_port() {
		return conn_port;
	}

	public void setConn_port(Integer conn_port) {
		this.conn_port = conn_port;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public String getAcc_username() {
		return acc_username;
	}

	public void setAcc_username(String acc_username) {
		this.acc_username = acc_username;
	}

	public Integer getAuth_type() {
		return auth_type;
	}

	public void setAuth_type(Integer auth_type) {
		this.auth_type = auth_type;
	}

	public Integer getProtocol() {
		return protocol;
	}

	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
	}

	public Integer getProtocol_sub_type() {
		return protocol_sub_type;
	}

	public void setProtocol_sub_type(Integer protocol_sub_type) {
		this.protocol_sub_type = protocol_sub_type;
	}

	public Integer getTime_begin() {
		return time_begin;
	}

	public void setTime_begin(Integer time_begin) {
		this.time_begin = time_begin;
	}

	public Integer getTime_end() {
		return time_end;
	}

	public void setTime_end(Integer time_end) {
		this.time_end = time_end;
	}

	@Override
	public String toString() {
		return "Record{ "+
			",id=" + id +
			",core_sn=" + core_sn +
			",flag=" + flag +
			",reason=" + reason +
			",sid=" + sid +
			",user_id=" + user_id +
			",host_id=" + host_id +
			",acc_id=" + acc_id +
			",status=" + status +
			",user_username=" + user_username +
			",user_surname=" + user_surname +
			",host_ip=" + host_ip +
			",conn_ip=" + conn_ip +
			",conn_port=" + conn_port +
			",client_ip=" + client_ip +
			",acc_username=" + acc_username +
			",auth_type=" + auth_type +
			",protocol=" + protocol +
			",protocol_sub_type=" + protocol_sub_type +
			",time_begin=" + time_begin +
			",time_end=" + time_end +
			"";
	}
}
