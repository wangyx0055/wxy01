package com.longersec.blj.domain;

public class CrontabScript {

	private Integer id;

	private String name;

	private String file_path;

	private Integer type;

	private String desc;

	private String upload_time;



	public CrontabScript() {
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

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(String upload_time) {
		this.upload_time = upload_time;
	}

	@Override
	public String toString() {
		return "CrontabScript [id=" + id + ", name=" + name + ", file_path=" + file_path + ", type=" + type + ", desc="
				+ desc + ", upload_time=" + upload_time + "]";
	}
	
	
}
