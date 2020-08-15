package com.longersec.blj.domain;

public class AccessPolicy  implements java.io.Serializable{
	private static final long serialVersionUID = 3058782159500119274L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private Integer id;

	private String name;

	private String valid_datetime_start;

	private Integer valid_long;

	private String valid_datetime_end;

	private Integer file_upload;

	private Integer file_download;

	private String timelimit_ban_monday;

	private String timelimit_ban_tuesday;

	private String timelimit_ban_wednesday;

	private String timelimit_ban_thursday;

	private String timelimit_ban_friday;

	private String timelimit_ban_saturday;

	private String timelimit_ban_sunday;

	private String allow_ip;

	private String ban_ip;

	private Integer status;

	private String desc;

	private Integer department;

	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}

	public AccessPolicy() {
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

	public String getValid_datetime_start() {
		return valid_datetime_start;
	}

	public void setValid_datetime_start(String valid_datetime_start) {
		this.valid_datetime_start = valid_datetime_start;
	}

	public Integer getValid_long() {
		return valid_long;
	}

	public void setValid_long(Integer valid_long) {
		this.valid_long = valid_long;
	}

	public String getValid_datetime_end() {
		return valid_datetime_end;
	}

	public void setValid_datetime_end(String valid_datetime_end) {
		this.valid_datetime_end = valid_datetime_end;
	}

	public Integer getFile_upload() {
		return file_upload;
	}

	public void setFile_upload(Integer file_upload) {
		this.file_upload = file_upload;
	}

	public Integer getFile_download() {
		return file_download;
	}

	public void setFile_download(Integer file_download) {
		this.file_download = file_download;
	}

	public String getTimelimit_ban_monday() {
		return timelimit_ban_monday;
	}

	public void setTimelimit_ban_monday(String timelimit_ban_monday) {
		this.timelimit_ban_monday = timelimit_ban_monday;
	}

	public String getTimelimit_ban_tuesday() {
		return timelimit_ban_tuesday;
	}

	public void setTimelimit_ban_tuesday(String timelimit_ban_tuesday) {
		this.timelimit_ban_tuesday = timelimit_ban_tuesday;
	}

	public String getTimelimit_ban_wednesday() {
		return timelimit_ban_wednesday;
	}

	public void setTimelimit_ban_wednesday(String timelimit_ban_wednesday) {
		this.timelimit_ban_wednesday = timelimit_ban_wednesday;
	}

	public String getTimelimit_ban_thursday() {
		return timelimit_ban_thursday;
	}

	public void setTimelimit_ban_thursday(String timelimit_ban_thursday) {
		this.timelimit_ban_thursday = timelimit_ban_thursday;
	}

	public String getTimelimit_ban_friday() {
		return timelimit_ban_friday;
	}

	public void setTimelimit_ban_friday(String timelimit_ban_friday) {
		this.timelimit_ban_friday = timelimit_ban_friday;
	}

	public String getTimelimit_ban_saturday() {
		return timelimit_ban_saturday;
	}

	public void setTimelimit_ban_saturday(String timelimit_ban_saturday) {
		this.timelimit_ban_saturday = timelimit_ban_saturday;
	}

	public String getTimelimit_ban_sunday() {
		return timelimit_ban_sunday;
	}

	public void setTimelimit_ban_sunday(String timelimit_ban_sunday) {
		this.timelimit_ban_sunday = timelimit_ban_sunday;
	}

	public String getAllow_ip() {
		return allow_ip;
	}

	public void setAllow_ip(String allow_ip) {
		this.allow_ip = allow_ip;
	}

	public String getBan_ip() {
		return ban_ip;
	}

	public void setBan_ip(String ban_ip) {
		this.ban_ip = ban_ip;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "AccessPolicy{" +
				"id=" + id +
				", name='" + name + '\'' +
				", valid_datetime_start='" + valid_datetime_start + '\'' +
				", valid_long=" + valid_long +
				", valid_datetime_end='" + valid_datetime_end + '\'' +
				", file_upload=" + file_upload +
				", file_download=" + file_download +
				", timelimit_ban_monday='" + timelimit_ban_monday + '\'' +
				", timelimit_ban_tuesday='" + timelimit_ban_tuesday + '\'' +
				", timelimit_ban_wednesday='" + timelimit_ban_wednesday + '\'' +
				", timelimit_ban_thursday='" + timelimit_ban_thursday + '\'' +
				", timelimit_ban_friday='" + timelimit_ban_friday + '\'' +
				", timelimit_ban_saturday='" + timelimit_ban_saturday + '\'' +
				", timelimit_ban_sunday='" + timelimit_ban_sunday + '\'' +
				", allow_ip='" + allow_ip + '\'' +
				", ban_ip='" + ban_ip + '\'' +
				", status=" + status +
				", desc='" + desc + '\'' +
				", department=" + department +
				'}';
	}
}
