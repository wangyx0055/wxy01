package com.longersec.blj.domain;

public class Session implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3058782159500119274L;

	String id;
	
	String session;
	
	String refresh_time;
	
	String userid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRefresh_time() {
		return refresh_time;
	}

	public void setRefresh_time(String refresh_time) {
		this.refresh_time = refresh_time;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
