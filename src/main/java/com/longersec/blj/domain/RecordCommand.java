package com.longersec.blj.domain;

public class RecordCommand {

	private Integer id;

	private Integer record_id;

	private Long start;

	private String command;

	private Integer cmddanger_id;

	private String _time;

	private Integer relatime;

	private Integer is_apppub;
	
	private String filepath;



	public RecordCommand() {
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
	

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Integer getIs_apppub() {
		return is_apppub;
	}

	public void setIs_apppub(Integer is_apppub) {
		this.is_apppub = is_apppub;
	}


	public Integer getRecord_id() {
		return record_id;
	}


	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}


	public Integer getCmddanger_id() {
		return cmddanger_id;
	}


	public void setCmddanger_id(Integer cmddanger_id) {
		this.cmddanger_id = cmddanger_id;
	}
	


	public String getFilepath() {
		return filepath;
	}


	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


	@Override
	public String toString() {
		return "RecordCommand [id=" + id + ", record_id=" + record_id + ", start=" + start + ", command=" + command
				+ ", cmddanger_id=" + cmddanger_id + ", _time=" + _time + ", relatime=" + relatime + ", is_apppub="
				+ is_apppub + "]";
	}
	
	
}
