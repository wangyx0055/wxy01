package com.longersec.blj.domain;

public class ConfigDbbackup {

	private Integer id;

	private String backup_time;

	private Long filesize;

	private String desc;
	
	private String filepath;
	

	public Long getFilesize() {
		return filesize;
	}

	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}

	public ConfigDbbackup() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBackup_time() {
		return backup_time;
	}

	public void setBackup_time(String backup_time) {
		this.backup_time = backup_time;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public String toString() {
		return "ConfigDbbackup{ "+
			",id=" + id +
			",backup_time=" + backup_time +
			",filesize=" + filesize +
			",desc=" + desc +
			"";
	}
	
}
