package com.longersec.blj.domain;

public class DeviceRecordCommand {

	private Integer id;

	private Integer lsblj_device_record_id;

	private Integer start;

	private String command;

	private Integer lsblj_cmddanger_id;

	private String _time;

	private Integer relatime;



	public DeviceRecordCommand() {
		super();
	}
	

	public String get_time() {
		return _time;
	}


	public void set_time(String _time) {
		this._time = _time;
	}


	public Integer getRelatime() {
		return relatime;
	}


	public void setRelatime(Integer relatime) {
		this.relatime = relatime;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLsblj_device_record_id() {
		return lsblj_device_record_id;
	}

	public void setLsblj_device_record_id(Integer lsblj_device_record_id) {
		this.lsblj_device_record_id = lsblj_device_record_id;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Integer getLsblj_cmddanger_id() {
		return lsblj_cmddanger_id;
	}

	public void setLsblj_cmddanger_id(Integer lsblj_cmddanger_id) {
		this.lsblj_cmddanger_id = lsblj_cmddanger_id;
	}

	@Override
	public String toString() {
		return "DeviceRecordCommand{ "+
			",id=" + id +
			",lsblj_device_record_id=" + lsblj_device_record_id +
			",start=" + start +
			",command=" + command +
			",lsblj_cmddanger_id=" + lsblj_cmddanger_id +
			"";
	}
}
