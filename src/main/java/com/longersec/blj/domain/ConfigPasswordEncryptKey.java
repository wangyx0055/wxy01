package com.longersec.blj.domain;

public class ConfigPasswordEncryptKey {

	private String aeskey;



	public ConfigPasswordEncryptKey() {
		super();
	}

	public String getAeskey() {
		return aeskey;
	}

	public void setAeskey(String aeskey) {
		this.aeskey = aeskey;
	}

	@Override
	public String toString() {
		return "ConfigPasswordEncryptKey{ "+
			",aeskey=" + aeskey +
			"";
	}
}
