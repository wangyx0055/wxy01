package com.longersec.blj.domain;

public class CmdPolicy {

	private Integer id;

	private String name;

	private String valid_datetime_start;

	private String valid_datetime_end;
	
	private Integer longtime;

	private Integer department_id;

	private Integer execute_action;

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

	private String cmd;

	private String desc;

	private String alert_methods;

	private String alert_level;

	private String depart_name;

	private String topName;

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}

	public String getTopName() {
		return topName;
	}

	public void setTopName(String topName) {
		this.topName = topName;
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

	public String getValid_datetime_end() {
		return valid_datetime_end;
	}

	public void setValid_datetime_end(String valid_datetime_end) {
		this.valid_datetime_end = valid_datetime_end;
	}

	public Integer getLongtime() {
		return longtime;
	}

	public void setLongtime(Integer longtime) {
		this.longtime = longtime;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public Integer getExecute_action() {
		return execute_action;
	}

	public void setExecute_action(Integer execute_action) {
		this.execute_action = execute_action;
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

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAlert_methods() {
		return alert_methods;
	}

	public void setAlert_methods(String alert_methods) {
		this.alert_methods = alert_methods;
	}

	public String getAlert_level() {
		return alert_level;
	}

	public void setAlert_level(String alert_level) {
		this.alert_level = alert_level;
	}

	@Override
	public String toString() {
		return "CmdPolicy{" +
				"id=" + id +
				", name='" + name + '\'' +
				", valid_datetime_start='" + valid_datetime_start + '\'' +
				", valid_datetime_end='" + valid_datetime_end + '\'' +
				", longtime=" + longtime +
				", department_id=" + department_id +
				", execute_action=" + execute_action +
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
				", cmd='" + cmd + '\'' +
				", desc='" + desc + '\'' +
				", alert_methods='" + alert_methods + '\'' +
				", alert_level='" + alert_level + '\'' +
				", depart_name='" + depart_name + '\'' +
				", topName='" + topName + '\'' +
				'}';
	}
}