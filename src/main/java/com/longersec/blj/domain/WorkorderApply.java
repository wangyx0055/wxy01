package com.longersec.blj.domain;

public class WorkorderApply {

	private Integer id;

	private Integer apply_user_id;

	private String name;

	private String apply_username;

	private String apply_realname;

	private Integer type;

	private String start;

	private String end;

	private Integer upload;

	private Integer download;

	private Integer filemanage;

	private String searchAll;

	private Integer up_clipboard;

	private Integer down_clipboard;

	private Integer watermark;

	private String description;

	private Integer result;

	private String reason;

	private String update_time;

	private String command;
	
	private Integer isAudit;
	
	private String Deadline;
	
	private Integer record_id;



	public WorkorderApply() {
		super();
	}

	public Integer getRecord_id() {
		return record_id;
	}


	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeadline() {
		return Deadline;
	}

	public void setDeadline(String deadline) {
		Deadline = deadline;
	}

	public Integer getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Integer isAudit) {
		this.isAudit = isAudit;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Integer getUpload() {
		return upload;
	}

	public void setUpload(Integer upload) {
		this.upload = upload;
	}

	public Integer getDownload() {
		return download;
	}

	public void setDownload(Integer download) {
		this.download = download;
	}

	public Integer getFilemanage() {
		return filemanage;
	}

	public void setFilemanage(Integer filemanage) {
		this.filemanage = filemanage;
	}

	public Integer getUp_clipboard() {
		return up_clipboard;
	}

	public void setUp_clipboard(Integer up_clipboard) {
		this.up_clipboard = up_clipboard;
	}

	public Integer getDown_clipboard() {
		return down_clipboard;
	}

	public void setDown_clipboard(Integer down_clipboard) {
		this.down_clipboard = down_clipboard;
	}

	public Integer getWatermark() {
		return watermark;
	}

	public void setWatermark(Integer watermark) {
		this.watermark = watermark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSearchAll() {
		return searchAll;
	}

	public void setSearchAll(String searchAll) {
		this.searchAll = searchAll;
	}

	public Integer getApply_user_id() {
		return apply_user_id;
	}

	public void setApply_user_id(Integer apply_user_id) {
		this.apply_user_id = apply_user_id;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getApply_username() {
		return apply_username;
	}

	public void setApply_username(String apply_username) {
		this.apply_username = apply_username;
	}

	public String getApply_realname() {
		return apply_realname;
	}

	public void setApply_realname(String apply_realname) {
		this.apply_realname = apply_realname;
	}
	
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@Override
	public String toString() {
		return "WorkorderApply{" +
				"id=" + id +
				", apply_user_id=" + apply_user_id +
				", name='" + name + '\'' +
				", apply_username='" + apply_username + '\'' +
				", apply_realname='" + apply_realname + '\'' +
				", type=" + type +
				", start='" + start + '\'' +
				", end='" + end + '\'' +
				", upload=" + upload +
				", download=" + download +
				", filemanage=" + filemanage +
				", searchAll='" + searchAll + '\'' +
				", up_clipboard=" + up_clipboard +
				", down_clipboard=" + down_clipboard +
				", watermark=" + watermark +
				", description='" + description + '\'' +
				", result=" + result +
				", reason='" + reason + '\'' +
				", update_time='" + update_time + '\'' +
				'}';
	}
}
