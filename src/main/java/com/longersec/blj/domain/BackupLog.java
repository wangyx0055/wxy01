package com.longersec.blj.domain;

public class BackupLog {

	private Integer id;

	private String name;

	private Integer filesize;

	private String filepath;

	private Integer backup_datetime;



	public BackupLog() {
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

	public Integer getFilesize() {
		return filesize;
	}

	public void setFilesize(Integer filesize) {
		this.filesize = filesize;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Integer getBackup_datetime() {
		return backup_datetime;
	}

	public void setBackup_datetime(Integer backup_datetime) {
		this.backup_datetime = backup_datetime;
	}

	@Override
	public String toString() {
		return "BackupLog{ "+
			",id=" + id +
			",name=" + name +
			",filesize=" + filesize +
			",filepath=" + filepath +
			",backup_datetime=" + backup_datetime +
			"";
	}
}
